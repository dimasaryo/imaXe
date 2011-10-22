import javax.microedition.lcdui.Image;

/**
 *
 * @author dimas aryo
 * 
 */
public class brightnessProcess {
    public Image getBrightness(Image sourceImage, int brightness){
        int sourceWidth = sourceImage.getWidth();
        int sourceHeight = sourceImage.getHeight();
        
        int[] sourceData = new int[sourceWidth*sourceHeight];
        int[] resultData = new int[sourceWidth*sourceHeight];
        
        int max=0;
        int min=255;
        sourceImage.getRGB(sourceData, 0, sourceWidth, 0, 0, sourceWidth, sourceHeight);
        
        int adjustBrightness=0;
        if(brightness < 0){
            adjustBrightness = (int) (brightness*20*-1);
        } else if(brightness > 0) {
            adjustBrightness = (int)(brightness* 20);
        }
        
        for(int i=0;i<(sourceWidth*sourceHeight);i++){
            int alpha = (sourceData[i]>>24) & 0xFF;
            int red = ((sourceData[i]>>16) & 0xFF);
            int green = ((sourceData[i]>>8) & 0xFF);
            int blue = (sourceData[i] & 0xFF);
            
            float Y = (0.299f*(float)red)+(0.587f*(float)green)+(0.114f*(float)blue);
            float Cb = (-0.1687f*red)-(0.3313f*green)+(0.5f*blue)+128f;
            float Cr = (0.5f*red)-(0.4187f*green)-(0.0813f*blue)+128f;
                  
            Y += adjustBrightness;
            
            red = (int) (Y + (1.402f*(Cr-128)));
            red = red < 0 ? 0 : red;
            red = red > 255 ? 255 : red;
            green = (int) (Y - (0.34414f*(Cb-128)) - (0.71414*(Cr-128)));
            green = green < 0 ? 0 : green;
            green = green > 255 ? 255 : green;
            blue = (int) (Y + (1.772f*(Cb-128)));
            blue = blue < 0 ? 0 : blue;
            blue = blue > 255? 255 : blue;
            
            resultData[i] = (255 << 24)|(red << 16)|(green << 8) | blue;
        }
        return Image.createRGBImage(resultData, sourceWidth, sourceHeight, true);        
    }
    
}
