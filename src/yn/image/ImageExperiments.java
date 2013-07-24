package yn.image;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;

import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class ImageExperiments {
	private static String imageURLStr = "http://hpblogs.i.lithium.com/html/images/HPCL_jbl_rgb_lg.png";
	private static final AmazonS3 s3 = new AmazonS3Client(new ClasspathPropertiesFileCredentialsProvider());
	
	public static void main(String[] args) throws MalformedURLException, IOException {
		BufferedImage image = ImageIO.read(new URL(imageURLStr));
		BufferedImage thumbnail = Thumbnails.of(image).size(100, 100).asBufferedImage();
		// create a blank, RGB, same width and height, and a white background
		BufferedImage newBufferedImage = new BufferedImage(thumbnail.getWidth(), thumbnail.getHeight(), BufferedImage.TYPE_INT_RGB);
		newBufferedImage.createGraphics().drawImage(thumbnail, 0, 0, Color.WHITE, null);

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(newBufferedImage, "jpg", os);
		byte[] buffer = os.toByteArray();
		System.out.println(buffer.length);
		InputStream is = new ByteArrayInputStream(buffer);
		ObjectMetadata meta = new ObjectMetadata();
		meta.setContentLength(buffer.length);
		meta.setContentType("image/jpeg");
		PutObjectRequest putObject = new PutObjectRequest("nyimage", 
				UUID.randomUUID().toString() + ".jpg", is, meta)
				.withCannedAcl(CannedAccessControlList.PublicRead);
		s3.createBucket(putObject.getBucketName());
		s3.putObject(putObject);
		
		/*ObjectMetadata meta = new ObjectMetadata();
		meta.setContentType("image/jpeg");
		s3.putObject(new PutObjectRequest("nyimage", 
				UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(imageURLStr), 
				new URL(imageURLStr).openStream(), meta)
		  .withCannedAcl(CannedAccessControlList.PublicRead));*/
	}
}
