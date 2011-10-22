import javax.microedition.lcdui.*;

/**
 * @author Dimas Aryo
 */
public class cHome extends Canvas implements CommandListener {
    
    private imaxeApp imaxeapp;
    
    /*
     * Command at Home
     */
    private Command cmdOpenFile;
    private Command cmdSaveFile;
    private Command cmdRotate;
    private Command cmdFlip;
    private Command cmdScale;
    private Command cmdContrast;
    private Command cmdBrightness;
    private Command cmdInversion;
    private Command cmdNegation;
    private Command cmdExit;
    
    /*
     * Commad at Image Processing
     */
    private Command cmdSave;
    private Command cmdCancel;
    
    /*
     * Command at Rotate
     */
    private Command cmdRotate90;
    private Command cmdRotate180;
    private Command cmdRotate270;
    
    /*
     * Command at Flip
     */
    private Command cmdFlipHorizontal;
    private Command cmdFlipVertical;
    
    
    private Image originalImage;
    private Image tempImage;
    
    private String ImageOperation;
    
    public static String ROTATE = "rotate";
    public static String FLIP = "flip";
    public static String SCALE = "scale";
    public static String CONTRAST = "contrast";
    public static String BRIGHTNESS = "brightness";
    public static String Inversion = "Inversion";
    public static String Negation = "Negation";
    
    private String keyValue;
    
    private int contrast;
    private int brightness;
    
    
    
    /**
     * constructor
     */
    public cHome(imaxeApp imaxeapp) {
        try {
            this.imaxeapp = imaxeapp;
            
            // Set up this canvas to listen to command events
            setCommandListener(this);
            
            cmdOpenFile = new Command("Open File",Command.OK,1);
            cmdSaveFile = new Command("Save File",Command.OK,2);
            cmdRotate = new Command("Rotate",Command.OK,3);
            cmdFlip = new Command("Flip",Command.OK,4);
            cmdScale = new Command("Resize",Command.OK,5);
            cmdContrast = new Command("Contrast",Command.OK,6);
            cmdBrightness = new Command("Brightness",Command.OK,7);
            cmdInversion = new Command("Invert",Command.OK,8);
            cmdNegation = new Command("Negation",Command.OK,9);
            cmdExit = new Command("Exit",Command.EXIT,1);
            
            cmdRotate90 = new Command("Rotate 90",Command.OK,1);
            cmdRotate180 = new Command("Rotate 180", Command.OK,2);
            cmdRotate270 = new Command("Rotate 270",Command.OK,3);
            
            cmdFlipHorizontal = new Command("Flip Horisontal",Command.OK,1);
            cmdFlipVertical = new Command("Flip Vertikal", Command.OK,2);
            
            cmdSave = new Command("Save", Command.OK, 10);
            cmdCancel = new Command("Cancel", Command.CANCEL, 1);
            
            addCommand(cmdOpenFile);
            addCommand(cmdSaveFile);
            addCommand(cmdRotate);
            addCommand(cmdFlip);
            addCommand(cmdScale);
            addCommand(cmdContrast);
            addCommand(cmdBrightness);
            addCommand(cmdInversion);
            addCommand(cmdNegation);
            addCommand(cmdExit);
            
            this.setTitle("imaxeapp v.0.1");
        } catch (Exception e) {
           
        }
    }
    
    public Image getOriginalImage(){
        return this.originalImage;
    }
    
    public void setOriginalImage(Image originalImage){
        this.originalImage = originalImage;
        this.tempImage = this.originalImage;
    }
    
    public Image setTempImage(Image tempImage){
        return this.tempImage = tempImage;
    }
    
    private Image createThumbnail(){
        int sourceWidth = tempImage.getWidth();
        int sourceHeight = tempImage.getHeight();
        int thumbWidth = 0;
        int thumbHeight = 0;
        
        int screenWidth = getWidth();
        int screenHeight = getHeight();
        
        if(sourceWidth >= sourceHeight){
            thumbWidth = screenWidth;
            thumbHeight = -1;

            if(thumbHeight == -1)
                thumbHeight = thumbWidth * sourceHeight/sourceWidth;
        } else {
            thumbWidth = -1;
            thumbHeight = getHeight();
            
            if(thumbWidth == -1)
                thumbWidth = thumbHeight * sourceWidth/sourceHeight;
        }
                
        Image thumb = Image.createImage(thumbWidth,thumbHeight);
        Graphics g = thumb.getGraphics();
        
        for(int y=0;y < thumbHeight; y++){
            for(int x=0;x < thumbWidth; x++){
                g.setClip(x,y,1,1);
                int dx = x*sourceWidth/thumbWidth;
                int dy = y*sourceHeight/thumbHeight;
                g.drawImage(tempImage, x-dx, y-dy, Graphics.LEFT | Graphics.TOP);
            }
        }
        
        Image immutableThumb = Image.createImage(thumb);
        return immutableThumb;
    }

    /**
     * paint
     */
    public void paint(Graphics g) {
        g.setColor(255,255,255);
        g.fillRect(0, 0, getWidth(), getHeight());
        Image thumbImage = createThumbnail();
        g.drawImage(thumbImage, getWidth()/2, getHeight()/2, Graphics.VCENTER | Graphics.HCENTER);
    }

    /**
     * Called when a key is pressed.
     */
    protected void keyPressed(int keyCode) {

    }

    /**
     * Called when a key is released.
     */
    protected void keyReleased(int keyCode) {
        if(this.ImageOperation.equals(cHome.CONTRAST)){
            if(keyCode==-1){
                this.setTitle("processing the image");
                contrast = contrast + 1;
                if(contrast>5){
                    contrast = 5;
                }
                contrastProcess cp = new contrastProcess();
                tempImage = cp.getContrast(originalImage, contrast);
                repaint();
                this.setTitle("contrast level : "+contrast);
            } else if(keyCode == -2){
                this.setTitle("processing the image");
                contrast = contrast - 1;
                if(contrast < -5){
                    contrast = -5;
                }                
                contrastProcess cp = new contrastProcess();
                tempImage = cp.getContrast(originalImage, contrast);
                repaint();
                this.setTitle("contrast level : "+contrast);
            }        
        } else if(this.ImageOperation.equals(cHome.BRIGHTNESS)){
            if(keyCode == -1){
                this.setTitle("processing the image");
                brightness += 1;
                if(brightness>5){
                    brightness = 5;
                }
                brightnessProcess cp = new brightnessProcess();
                tempImage = cp.getBrightness(originalImage, brightness);
                repaint();
                this.setTitle("brightness level : "+brightness);
            } else if(keyCode == -2){
                this.setTitle("processing the image");
                brightness -= 1;
                if(brightness < -5){
                    brightness = -5;
                }                
                brightnessProcess cp = new brightnessProcess();
                tempImage = cp.getBrightness(originalImage, brightness);
                repaint();
                this.setTitle("brightness level : "+brightness);
            }        
        }

    }

    /**
     * Called when a key is repeated (held down).
     */
    protected void keyRepeated(int keyCode) {
    }

    /**
     * Called when the pointer is dragged.
     */
    protected void pointerDragged(int x, int y) {
    }

    /**
     * Called when the pointer is pressed.
     */
    protected void pointerPressed(int x, int y) {
    }

    /**
     * Called when the pointer is released.
     */
    protected void pointerReleased(int x, int y) {
    }

    /**
     * Called when action should be handled
     */
    public void commandAction(Command command, Displayable displayable) {
        if(command == cmdOpenFile){
            imaxeapp.switchDisplayable(null,imaxeapp.getFileBrowser());
        } else if(command == cmdSaveFile){
            vdSaveFile vdsavefile = new vdSaveFile(imaxeapp);
            imaxeapp.switchDisplayable(null,vdsavefile.getFSaveFile());
        } else if(command == cmdRotate){
            ImageOperation = cHome.ROTATE;
            this.setTitle("Image Rotation");
            
            removeCommand(cmdOpenFile);
            removeCommand(cmdSaveFile);
            removeCommand(cmdRotate);
            removeCommand(cmdFlip);
            removeCommand(cmdScale);
            removeCommand(cmdContrast);
            removeCommand(cmdBrightness);
            removeCommand(cmdInversion);
            removeCommand(cmdNegation);
            removeCommand(cmdExit);
            
            addCommand(cmdRotate90);
            addCommand(cmdRotate180);
            addCommand(cmdRotate270);
            addCommand(cmdSave);
            addCommand(cmdCancel);
                       
        } else if(command == cmdRotate90){
            geometryProcess gp = new geometryProcess();
            try {
                tempImage = gp.rotate(tempImage,90);
            } catch (Exception ex) {
                
            }
            repaint();
        } else if(command == cmdRotate180){
            geometryProcess gp = new geometryProcess();
            try{
                tempImage = gp.rotate(tempImage, 180);
            } catch(Exception ex){
                
            }
            repaint();
        } else if(command == cmdRotate270){
            geometryProcess gp = new geometryProcess();
            try{
                tempImage = gp.rotate(tempImage, 270);
            } catch(Exception ex){
                
            }
            repaint();        
        } else if(command == cmdFlip){
            ImageOperation = cHome.FLIP;
            this.setTitle("Image Flip");
            
            removeCommand(cmdOpenFile);
            removeCommand(cmdSaveFile);
            removeCommand(cmdRotate);
            removeCommand(cmdFlip);
            removeCommand(cmdScale);
            removeCommand(cmdContrast);
            removeCommand(cmdBrightness);
            removeCommand(cmdInversion);
            removeCommand(cmdNegation);
            removeCommand(cmdExit);
            
            addCommand(cmdFlipHorizontal);
            addCommand(cmdFlipVertical);
            addCommand(cmdSave);
            addCommand(cmdCancel);
            
        } else if(command == cmdFlipHorizontal){
            geometryProcess gp = new geometryProcess();
            try{
                tempImage = gp.flip(tempImage,geometryProcess.HORIZONTAL);
            } catch(Exception ex){
                
            }
            repaint();        
        } else if(command == cmdFlipVertical){
            geometryProcess gp = new geometryProcess();
            try{
                tempImage = gp.flip(tempImage,geometryProcess.VERTICAL);
            } catch(Exception ex){
                
            }
            repaint();          
        } else if(command == cmdScale){
            ImageOperation = cHome.SCALE;
            this.setTitle("Resize Image");
            vdScale vdscale = new vdScale(imaxeapp);
            imaxeapp.switchDisplayable(null,vdscale.getForm());
        } else if(command == cmdContrast){
            ImageOperation = cHome.CONTRAST;
            removeCommand(cmdOpenFile);
            removeCommand(cmdSaveFile);
            removeCommand(cmdRotate);
            removeCommand(cmdFlip);
            removeCommand(cmdScale);
            removeCommand(cmdContrast);
            removeCommand(cmdBrightness);
            removeCommand(cmdInversion);
            removeCommand(cmdNegation);
            removeCommand(cmdExit);
            
            addCommand(cmdSave);
            addCommand(cmdCancel);
            this.setTitle("Contrast Stretching");
            this.contrast = 0;
        } else if(command == cmdBrightness){
            ImageOperation = cHome.BRIGHTNESS;
            this.setTitle("Brightness Adjusment");
            removeCommand(cmdOpenFile);
            removeCommand(cmdSaveFile);
            removeCommand(cmdRotate);
            removeCommand(cmdFlip);
            removeCommand(cmdScale);
            removeCommand(cmdContrast);
            removeCommand(cmdBrightness);
            removeCommand(cmdInversion);
            removeCommand(cmdNegation);
            removeCommand(cmdExit);
            
            addCommand(cmdSave);
            addCommand(cmdCancel);
            this.brightness = 0;
        } else if(command == cmdInversion){
            ImageOperation = cHome.Inversion;
            this.setTitle("Image Invertion");
            removeCommand(cmdOpenFile);
            removeCommand(cmdSaveFile);
            removeCommand(cmdRotate);
            removeCommand(cmdFlip);
            removeCommand(cmdScale);
            removeCommand(cmdContrast);
            removeCommand(cmdBrightness);
            removeCommand(cmdInversion);
            removeCommand(cmdNegation);
            removeCommand(cmdExit);
                  
            inversionProcess np = new inversionProcess();
            tempImage = np.inversion(tempImage);
            repaint();
            
            addCommand(cmdSave);
            addCommand(cmdCancel);
            
        } else if(command == cmdNegation){
            ImageOperation = cHome.Negation;
            this.setTitle("Image Negation");
            removeCommand(cmdOpenFile);
            removeCommand(cmdSaveFile);
            removeCommand(cmdRotate);
            removeCommand(cmdFlip);
            removeCommand(cmdScale);
            removeCommand(cmdContrast);
            removeCommand(cmdBrightness);
            removeCommand(cmdInversion);
            removeCommand(cmdNegation);
            removeCommand(cmdExit);
                       
            negationProcess np = new negationProcess();
            tempImage = np.negation(tempImage);
            repaint();
            
            addCommand(cmdSave);
            addCommand(cmdCancel);
            
        } else if(command == cmdSave){
            this.setTitle("imaxeapp v.0.1");
            originalImage = tempImage;
            imaxeapp.setTempImage(originalImage);
            if(this.ImageOperation.equals(cHome.ROTATE)){
                removeCommand(cmdRotate90);
                removeCommand(cmdRotate180);
                removeCommand(cmdRotate270);
            } else if(this.ImageOperation.equals(cHome.FLIP)){
                removeCommand(cmdFlipHorizontal);
                removeCommand(cmdFlipVertical);
            }
            removeCommand(cmdSave);
            removeCommand(cmdCancel);
            
            addCommand(cmdOpenFile);
            addCommand(cmdSaveFile);
            addCommand(cmdRotate);
            addCommand(cmdFlip);
            addCommand(cmdScale);
            addCommand(cmdContrast);
            addCommand(cmdBrightness);
            addCommand(cmdInversion);
            addCommand(cmdNegation);
            addCommand(cmdExit);
            this.ImageOperation = "";
            repaint();
        } else if(command == cmdCancel){
            tempImage = originalImage;
            if(this.ImageOperation.equals(cHome.ROTATE)){
                removeCommand(cmdRotate90);
                removeCommand(cmdRotate180);
                removeCommand(cmdRotate270);
            } else if(this.ImageOperation.equals(cHome.FLIP)){
                removeCommand(cmdFlipHorizontal);
                removeCommand(cmdFlipVertical);
            }
            removeCommand(cmdSave);
            removeCommand(cmdCancel);
            
            addCommand(cmdOpenFile);
            addCommand(cmdSaveFile);
            addCommand(cmdRotate);
            addCommand(cmdFlip);
            addCommand(cmdScale);
            addCommand(cmdContrast);
            addCommand(cmdBrightness);
            addCommand(cmdInversion);
            addCommand(cmdNegation);
            addCommand(cmdExit);
            this.ImageOperation = "";
            this.setTitle("imaxeapp v.0.1");
            repaint();
        } else if(command == cmdExit){
            imaxeapp.exitMIDlet();
        }
    }
}
