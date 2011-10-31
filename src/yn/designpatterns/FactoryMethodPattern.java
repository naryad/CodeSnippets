package yn.designpatterns;

import java.io.InputStream;

public class FactoryMethodPattern {
	public static ImageReader getImageReader(InputStream is)
    {
        int imageType = determineImageType(is);
 
        switch(imageType)
        {
            case ImageReaderFactory.GIF:
                return new GifReader(is);
            case ImageReaderFactory.JPEG:
                return new JpegReader(is);
            // etc.
        }
        //if none of the above
        return new JpegReader(is);
    }

	private static int determineImageType(InputStream is) {
		return 0;
	}
}

class DecodedImage {

}

interface ImageReaderFactory {

	int GIF = 0;
	int JPEG = 1;

}

interface ImageReader
{
    public DecodedImage getDecodedImage();
}
 
class GifReader implements ImageReader
{
	DecodedImage decodedImage; 
    public GifReader(InputStream is) {
	}
	public DecodedImage getDecodedImage()
    {
        // ...
        return decodedImage;
    }
}
 
class JpegReader implements ImageReader
{
	DecodedImage decodedImage;
    public JpegReader(InputStream is) {
	}
	public DecodedImage getDecodedImage()
    {
        // ...
        return decodedImage;
    }
}