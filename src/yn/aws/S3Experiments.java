package yn.aws;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.StringInputStream;

public class S3Experiments {

	private static String imageURLStr = "http://hpblogs.i.lithium.com/html/images/HPCL_jbl_rgb_lg.png";
	private static final AmazonS3 s3 = new AmazonS3Client(new ClasspathPropertiesFileCredentialsProvider());
	
	public static void main(String[] args) throws AmazonServiceException, AmazonClientException, UnsupportedEncodingException {
		s3.createBucket("nytestbucket");
		s3.putObject(new PutObjectRequest("nytestbucket", "test/" + UUID.randomUUID().toString(), new StringInputStream(imageURLStr), null));
	}
}
