import javax.microedition.lcdui.Image;

/**
 *
 * @author Dimas Aryo
 */
public class inversionProcess {
    
    public Image inversion(Image sourceImage){
        int sourceWidth = sourceImage.getWidth();
        int sourceHeight = sourceImage.getHeight();
        
        int[] sourceData = new int[sourceWidth*sourceHeight];
        int[] resultData = new int[sourceWidth*sourceHeight];
        
        sourceImage.getRGB(sourceData, 0, sourceWidth, 0, 0, sourceWidth, sourceHeight);
        for(int i=0;i<(sourceWidth*sourceHeight); i++){
            
            //invert the value of each pixel
            int alpha = sourceData[i]>>24;
            int red = 255 - (sourceData[i]>>16);
            int green = 255 - (sourceData[i]>>8);
            int blue = 255 - (sourceData[i]);
            resultData[i] = (255 << 24 )|(red << 16)|(green << 8) | blue;
        }
        return Image.createRGBImage(resultData, sourceWidth, sourceHeight, true);
    }
}
