import javax.microedition.lcdui.Image;

/**
 *
 * @author Dimas Aryo
 */
public class negationProcess {
    
    public Image negation(Image sourceImage){
        int sourceWidth = sourceImage.getWidth();
        int sourceHeight = sourceImage.getHeight();
        
        int[] sourceData = new int[sourceWidth*sourceHeight];
        int[] resultData = new int[sourceWidth*sourceHeight];
        
        sourceImage.getRGB(sourceData, 0, sourceWidth, 0, 0, sourceWidth, sourceHeight);
        for(int i=0;i<(sourceWidth*sourceHeight); i++){
            int alpha = (sourceData[i]>>24) & 0xFF;
            int red = (sourceData[i]>>16) & 0xFF;
            int green = (sourceData[i]>>8) & 0xFF;
            int blue = sourceData[i] & 0xFF;
            
            int grey = (red/3)+(green/3)+(blue/3);
            resultData[i] = (255 << 24)|(grey << 16)|(grey << 8) | grey;
        }
        return Image.createRGBImage(resultData, sourceWidth, sourceHeight, true);
    }
}
