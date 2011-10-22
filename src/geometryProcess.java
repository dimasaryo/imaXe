
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 *
 * @author Dimas Aryo
 */
public class geometryProcess {
    public static String HORIZONTAL = "horizontal";
    public static String VERTICAL = "vertical";
    public geometryProcess(){
    
    }
    
    public Image rotate(Image sourceImage,int angle) throws Exception{
        if(angle != 90 && angle !=180 && angle !=270)
            throw new Exception("invalid angle");
        
        int sourceWidth = sourceImage.getWidth();
        int sourceHeight = sourceImage.getHeight();
        
        int[] sourceData = new int[sourceWidth];
        int[] resultData = new int[sourceWidth*sourceHeight];
        
        
        int rotateIndex=0;
        for(int i=0;i<sourceHeight;i++){
            sourceImage.getRGB(sourceData, 0, sourceWidth, 0, i, sourceWidth, 1);
            for(int j=0;j<sourceWidth;j++){
                if(angle==90){
                    rotateIndex = (sourceHeight-i-1)+(j*sourceHeight);
                } else if(angle==180){
                    rotateIndex = (sourceHeight*sourceWidth-1)-(i*sourceWidth+j);
                } else if(angle==270){
                    rotateIndex = i+sourceHeight * (sourceWidth-j-1);
                }
                resultData[rotateIndex] = sourceData[j];
            }
        }
        
        if(angle==90 || angle==270)
            return Image.createRGBImage(resultData, sourceHeight, sourceWidth, true);
        else
            return Image.createRGBImage(resultData,sourceWidth,sourceHeight,true);
    }
    
    public Image flip(Image sourceImage, String orientation) throws Exception{
        if(!orientation.equals(geometryProcess.HORIZONTAL) && !orientation.equals(geometryProcess.VERTICAL))
            throw new Exception("invalid orientation");
        
        int sourceWidth = sourceImage.getWidth();
        int sourceHeight = sourceImage.getHeight();
        
        int[] sourceData = new int[sourceWidth];
        int[] resultData = new int[sourceWidth*sourceHeight];
        
        int flipIndex=0;
        for(int i=0;i<sourceHeight;i++){
            sourceImage.getRGB(sourceData, 0, sourceWidth, 0, i, sourceWidth, 1);
            for(int j=0;j<sourceWidth;j++){
                if(orientation.equals(geometryProcess.HORIZONTAL))
                    flipIndex=(i*sourceWidth)+(sourceWidth-j-1);
                else if(orientation.equals(geometryProcess.VERTICAL))
                    flipIndex = (sourceHeight-1-i)*sourceWidth+j;
                resultData[flipIndex] = sourceData[j]; 
            }
        }
        return Image.createRGBImage(resultData, sourceWidth, sourceHeight, true);
    }
    
    public Image rescale(Image sourceImage, int width, int height){
        int sourceWidth = sourceImage.getWidth();
        int sourceHeight = sourceImage.getHeight();
        
        Image resultImage = Image.createImage(width, height);
        Graphics g = resultImage.getGraphics();
        
        for(int i=0; i<height; i++){
            for(int j=0; j<width;j++){
                g.setClip(j, i, 1, 1);
                int dx = j * sourceWidth / width;
                int dy = i * sourceHeight / height;
                g.drawImage(sourceImage, j-dx, i-dy, Graphics.LEFT | Graphics.TOP);
            }
        }
        return Image.createImage(resultImage);
    }
    
}
