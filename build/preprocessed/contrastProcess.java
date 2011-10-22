import javax.microedition.lcdui.Image;

/**
 *
 * @author Dimas Aryo
 * 
 */
public class contrastProcess {
    public Image getContrast(Image sourceImage,int contrast){

        int sourceWidth = sourceImage.getWidth();
        int sourceHeight = sourceImage.getHeight();
        
        int[] sourceData = new int[sourceWidth*sourceHeight];
        int[] resultData = new int[sourceWidth*sourceHeight];
        
        sourceImage.getRGB(resultData, 0, sourceWidth, 0, 0, sourceWidth, sourceHeight);
        
        int min = 255;
        int max = 0;

        for(int i=0;i<(sourceWidth*sourceHeight);i++){
            int red = (resultData[i]>>16) & 0xff;
            int green = (resultData[i]>>8) & 0xff;
            int blue = (resultData[i]) & 0xff;
            int mean = (red+green+blue)/3;
            min = min<mean? min : mean;
            max = max>mean? max : mean;              
        }
        
        int outmin = min;
        int outmax = max;
        
        if(contrast < 0){
            int level = contrast*-1;
            outmin = min + (level*((max-min)/10));
            outmax = max - (level*((max-min)/10));
        } else if(contrast > 0){
            int level = contrast;
            outmin = min - (level*((max-min)/10));
            outmax = max + (level*((max-min)/10));
        }
        
        for(int i=0; i<(sourceWidth*sourceHeight);i++){
            int alpha =  (resultData[i] >> 24) & 0xff;
            int red = (resultData[i] >> 16) & 0xff;
            int green = (resultData[i] >> 8) & 0xff;
            int blue = (resultData[i] ) & 0xff;
            
            red = (int) ((red-min)*((float)(outmax-outmin)/(float)(max-min)))+outmin;
            red = red < 0 ? 0 : red;
            red = red > 255 ? 255 : red;
            green = (int) ((green-min)*((float)(outmax-outmin)/(float)(max-min)))+outmin;
            green = green < 0 ? 0 : green;
            green = green > 255 ? 255 : green;
            blue = (int) ((blue-min)*((float)(outmax-outmin)/(float)(max-min)))+outmin;
            blue = blue < 0 ? 0 : blue;
            blue = blue > 255? 255 : blue;
            resultData[i] = (255 << 24 | red << 16 | green << 8 | blue );
        }
        
        return Image.createRGBImage(resultData, sourceWidth, sourceHeight, true);
    }
    
    public Image decreaseContrast(Image sourceImage,int contrast){
        return sourceImage;
    }
}
