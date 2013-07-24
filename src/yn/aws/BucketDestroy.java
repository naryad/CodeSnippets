package yn.aws;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteVersionRequest;
import com.amazonaws.services.s3.model.S3VersionSummary;
import com.amazonaws.services.s3.model.VersionListing;


/**
 * Efficient delete of version enabled bucket.
 * 
 * This code will delete all keys from gives bucket and all previous versions of given key.
 * This is done in an efficient multi-threaded ExecutorService environment
 * 
 * Launching:
 * 
 * $java -Daws.key=AK7895IH1234X2GW12IQ -Daws.secret=1234567890123456789012345678901234456789 BucketDestroy "bucketName"

 * @author Maxim Veksler <maxim@vekslers.org>
 * @author Mark S. Kolich http://mark.koli.ch/2010/09/recursively-deleting-large-amazon-s3-buckets.html
 * 
 * @version 0.1 - First pseudo code version, Written by Mark S. Kolich. 
 * @version 0.2 - Maxim Veksler - Fix paging. Implement deletion of versioned keys.
 * 
 * For the latest version see http://code.google.com/p/bucket-destroy/
 */
public class BucketDestroy {
    private static final String AWS_ACCESS_KEY_PROPERTY = "aws.key";
    private static final String AWS_SECRET_PROPERTY = "aws.secret";
    private static AmazonS3Client s3;
    
    public static void main(String[] args) {

        final String accessKey = System.getProperty(AWS_ACCESS_KEY_PROPERTY);
        final String secret = System.getProperty(AWS_SECRET_PROPERTY);
        if (accessKey == null || secret == null) {
            System.err.println("You're missing the -Daws.key and -Daws.secret required VM properties.");
            System.exit(100);
        }

        if (args.length < 1) {
            System.err.println("Missing required program argument: bucketName.");
            System.exit(100);
        }
        
        // S3 Initialization
        s3 = new AmazonS3Client(new BasicAWSCredentials(accessKey, secret));


        // Instantiate BucketDestroy and call destroyBucket() - Bucket deleting beast!
        new BucketDestroy().destroyBucket(args[0]);
    }

    /**
     * Do everything it takes to delete amazon S3 bucket. 
     * 
     * Currently it means delete all versions of bucket keys.
     * 
     * TODO: 
     *  - Support distributed operation by doing shuffle on first level of bucketName/../
     *  - Seek a method to obtain MFA to delete MFA enabled bucket
     *  - Implement starvation prevention for deleting threads (do not wait until next s3.listVersions finishes)
     */
    public void destroyBucket(final String bucketName) {
        // Set up a new thread pool to delete 20 objects at a time.
        ExecutorService _pool = Executors.newFixedThreadPool(20);

        // Get counter, just for status
        final AtomicInteger ATOMIC_INTEGER = new AtomicInteger();
    
        // List all key in the bucket
        VersionListing versionListing = s3.listVersions(bucketName, "");
        List<S3VersionSummary> versionSummaries = versionListing.getVersionSummaries();
        while(versionSummaries != null && versionSummaries.size() > 0) {
            final CountDownLatch latch = new CountDownLatch(versionSummaries.size());
            for(final S3VersionSummary objectSummary : versionSummaries) {
                _pool.execute(new Runnable() {
                    @Override
                    public void run() {
                        String keyName = null;
                        String versionId = null;
                        try {
                            keyName = objectSummary.getKey();
                            versionId = objectSummary.getVersionId();
                            
                            String info = ">>>>   INFO: " + ATOMIC_INTEGER.incrementAndGet() + " deleting: (" + bucketName + "/" + keyName + "@" + versionId + ")";
                            System.out.println(info);
                            
                            s3.deleteVersion(new DeleteVersionRequest(bucketName, keyName, versionId));
                        } catch (Exception e) {
                            String err = ">>>> FAILED delete: (" + bucketName + "/" + keyName + "@" + versionId + ")";
                            System.err.println(err);
                        } finally {
                            latch.countDown();
                        }
                    }
                });
            }
            
            // After sending current batch of delete tasks we block until Latch reaches zero, this 
            // allows to not over populate ExecutorService tasks queue. 
            try {
                latch.await();
            } catch (Exception exception) {
            }

            // Paging over all S3 keys...
            versionListing = s3.listNextBatchOfVersions(versionListing);
            versionSummaries = versionListing.getVersionSummaries();
            
        }
        
        _pool.shutdown();

        try {
            // This code fails with HTTP 400 from aws API 
//            SetBucketVersioningConfigurationRequest setBucketVersioningConfigurationRequest = new SetBucketVersioningConfigurationRequest(bucketName, new BucketVersioningConfiguration().withStatus(BucketVersioningConfiguration.OFF));
//            s3.setBucketVersioningConfiguration(setBucketVersioningConfigurationRequest);
            // So we do this:
            s3.deleteBucket(bucketName);
            System.out.println(">>>>   INFO: bucket " + bucketName + " deleted!");
        } catch (Exception e) { 
            System.err.println("Failed to ultimately delete bucket: " + bucketName);
            e.printStackTrace();
        }

    }
    
}