import java.io.IOException;
import java.io.InputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import org.netbeans.microedition.lcdui.SplashScreen;
import org.netbeans.microedition.lcdui.pda.FileBrowser;

/**
 * @author dimas aryo
 */
public class imaxeApp extends MIDlet implements CommandListener {
    
    private boolean midletPaused = false;
    private Image originalImage;
    private Image tempImage; 
    private String filename;
    public cHome chome;
    

//<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
private SplashScreen splashScreen;
private Form FHome;
private FileBrowser fileBrowser;
private Alert alertBigFile;
private Command bOpenFile;
private Command bExit;
private Image image1;
//</editor-fold>//GEN-END:|fields|0|
    /**
     * The imaxeApp constructor.
     */
    public imaxeApp() {
    }
    
    public Image getTempImage(){
        return this.tempImage;
    }
    
    public void setTempImage(Image tempImage){
        this.tempImage = tempImage;
    }

//<editor-fold defaultstate="collapsed" desc=" Generated Methods ">//GEN-BEGIN:|methods|0|
//</editor-fold>//GEN-END:|methods|0|
//<editor-fold defaultstate="collapsed" desc=" Generated Method: initialize ">//GEN-BEGIN:|0-initialize|0|0-preInitialize
/**
 * Initializes the application.
 * It is called only once when the MIDlet is started. The method is called before the <code>startMIDlet</code> method.
 */
private void initialize () {//GEN-END:|0-initialize|0|0-preInitialize
        // write pre-initialize user code here
//GEN-LINE:|0-initialize|1|0-postInitialize
        // write post-initialize user code here
}//GEN-BEGIN:|0-initialize|2|
//</editor-fold>//GEN-END:|0-initialize|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: startMIDlet ">//GEN-BEGIN:|3-startMIDlet|0|3-preAction
/**
 * Performs an action assigned to the Mobile Device - MIDlet Started point.
 */
public void startMIDlet () {//GEN-END:|3-startMIDlet|0|3-preAction
        // write pre-action user code here
switchDisplayable (null, getSplashScreen ());//GEN-LINE:|3-startMIDlet|1|3-postAction
        // write post-action user code here
}//GEN-BEGIN:|3-startMIDlet|2|
//</editor-fold>//GEN-END:|3-startMIDlet|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: resumeMIDlet ">//GEN-BEGIN:|4-resumeMIDlet|0|4-preAction
/**
 * Performs an action assigned to the Mobile Device - MIDlet Resumed point.
 */
public void resumeMIDlet () {//GEN-END:|4-resumeMIDlet|0|4-preAction
        // write pre-action user code here
//GEN-LINE:|4-resumeMIDlet|1|4-postAction
        // write post-action user code here
}//GEN-BEGIN:|4-resumeMIDlet|2|
//</editor-fold>//GEN-END:|4-resumeMIDlet|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: switchDisplayable ">//GEN-BEGIN:|5-switchDisplayable|0|5-preSwitch
/**
 * Switches a current displayable in a display. The <code>display</code> instance is taken from <code>getDisplay</code> method. This method is used by all actions in the design for switching displayable.
 * @param alert the Alert which is temporarily set to the display; if <code>null</code>, then <code>nextDisplayable</code> is set immediately
 * @param nextDisplayable the Displayable to be set
 */
public void switchDisplayable (Alert alert, Displayable nextDisplayable) {//GEN-END:|5-switchDisplayable|0|5-preSwitch
        // write pre-switch user code here
Display display = getDisplay ();//GEN-BEGIN:|5-switchDisplayable|1|5-postSwitch
if (alert == null) {
display.setCurrent (nextDisplayable);
} else {
display.setCurrent (alert, nextDisplayable);
}//GEN-END:|5-switchDisplayable|1|5-postSwitch
        // write post-switch user code here
}//GEN-BEGIN:|5-switchDisplayable|2|
//</editor-fold>//GEN-END:|5-switchDisplayable|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Displayables ">//GEN-BEGIN:|7-commandAction|0|7-preCommandAction
/**
 * Called by a system to indicated that a command has been invoked on a particular displayable.
 * @param command the Command that was invoked
 * @param displayable the Displayable where the command was invoked
 */
public void commandAction (Command command, Displayable displayable) {//GEN-END:|7-commandAction|0|7-preCommandAction
        // write pre-action user code here
if (displayable == FHome) {//GEN-BEGIN:|7-commandAction|1|47-preAction
if (command == bExit) {//GEN-END:|7-commandAction|1|47-preAction
                // write pre-action user code here
exitMIDlet ();//GEN-LINE:|7-commandAction|2|47-postAction
                // write post-action user code here
} else if (command == bOpenFile) {//GEN-LINE:|7-commandAction|3|29-preAction
                // write pre-action user code here
switchDisplayable (null, getFileBrowser ());//GEN-LINE:|7-commandAction|4|29-postAction
                // write post-action user code here
}//GEN-BEGIN:|7-commandAction|5|50-preAction
} else if (displayable == fileBrowser) {
if (command == FileBrowser.SELECT_FILE_COMMAND) {//GEN-END:|7-commandAction|5|50-preAction
                try{
                    filename = fileBrowser.getSelectedFileURL();
                    FileConnection inputConn = (FileConnection) Connector.open(filename);
                    if(!inputConn.exists()){
                        throw new IOException("File doesn't exist");
                    }
                    InputStream inputStream = inputConn.openInputStream();
                    originalImage = Image.createImage(inputStream);
                    tempImage = originalImage;
                    inputConn.close();
                    
                }catch(Exception e){
                    FHome.setTitle("error processing the image");
                }
                chome = new cHome(this);
                chome.setOriginalImage(originalImage);
                if(originalImage.getHeight()*originalImage.getWidth() > 240000 ){
                    switchDisplayable(this.getAlertBigFile(),chome);     
                } else {
                    switchDisplayable(null,chome);
                }
//GEN-LINE:|7-commandAction|6|50-postAction
                
}//GEN-BEGIN:|7-commandAction|7|16-preAction
} else if (displayable == splashScreen) {
if (command == SplashScreen.DISMISS_COMMAND) {//GEN-END:|7-commandAction|7|16-preAction
                // write pre-action user code here
switchDisplayable (null, getFHome ());//GEN-LINE:|7-commandAction|8|16-postAction
                // write post-action user code here
}//GEN-BEGIN:|7-commandAction|9|7-postCommandAction
}//GEN-END:|7-commandAction|9|7-postCommandAction
        // write post-action user code here
}//GEN-BEGIN:|7-commandAction|10|
//</editor-fold>//GEN-END:|7-commandAction|10|






//<editor-fold defaultstate="collapsed" desc=" Generated Getter: splashScreen ">//GEN-BEGIN:|14-getter|0|14-preInit
/**
 * Returns an initiliazed instance of splashScreen component.
 * @return the initialized component instance
 */
public SplashScreen getSplashScreen () {
if (splashScreen == null) {//GEN-END:|14-getter|0|14-preInit
            // write pre-init user code here
splashScreen = new SplashScreen (getDisplay ());//GEN-BEGIN:|14-getter|1|14-postInit
splashScreen.setTitle ("");
splashScreen.setCommandListener (this);
splashScreen.setFullScreenMode (true);
splashScreen.setImage (getImage1 ());
splashScreen.setTimeout (3000);//GEN-END:|14-getter|1|14-postInit

}//GEN-BEGIN:|14-getter|2|
return splashScreen;
}
//</editor-fold>//GEN-END:|14-getter|2|





//<editor-fold defaultstate="collapsed" desc=" Generated Getter: FHome ">//GEN-BEGIN:|25-getter|0|25-preInit
/**
 * Returns an initiliazed instance of FHome component.
 * @return the initialized component instance
 */
public Form getFHome () {
if (FHome == null) {//GEN-END:|25-getter|0|25-preInit
            // write pre-init user code here
FHome = new Form ("imaXe v.0.1");//GEN-BEGIN:|25-getter|1|25-postInit
FHome.addCommand (getBOpenFile ());
FHome.addCommand (getBExit ());
FHome.setCommandListener (this);//GEN-END:|25-getter|1|25-postInit

            
}//GEN-BEGIN:|25-getter|2|
return FHome;
}
//</editor-fold>//GEN-END:|25-getter|2|



//<editor-fold defaultstate="collapsed" desc=" Generated Getter: bOpenFile ">//GEN-BEGIN:|28-getter|0|28-preInit
/**
 * Returns an initiliazed instance of bOpenFile component.
 * @return the initialized component instance
 */
public Command getBOpenFile () {
if (bOpenFile == null) {//GEN-END:|28-getter|0|28-preInit
            // write pre-init user code here
bOpenFile = new Command ("Open File", Command.OK, 0);//GEN-LINE:|28-getter|1|28-postInit

}//GEN-BEGIN:|28-getter|2|
return bOpenFile;
}
//</editor-fold>//GEN-END:|28-getter|2|

















//<editor-fold defaultstate="collapsed" desc=" Generated Getter: bExit ">//GEN-BEGIN:|46-getter|0|46-preInit
/**
 * Returns an initiliazed instance of bExit component.
 * @return the initialized component instance
 */
public Command getBExit () {
if (bExit == null) {//GEN-END:|46-getter|0|46-preInit
            // write pre-init user code here
bExit = new Command ("Exit", Command.EXIT, 0);//GEN-LINE:|46-getter|1|46-postInit
            // write post-init user code here
}//GEN-BEGIN:|46-getter|2|
return bExit;
}
//</editor-fold>//GEN-END:|46-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: fileBrowser ">//GEN-BEGIN:|49-getter|0|49-preInit
/**
 * Returns an initiliazed instance of fileBrowser component.
 * @return the initialized component instance
 */
public FileBrowser getFileBrowser () {
if (fileBrowser == null) {//GEN-END:|49-getter|0|49-preInit
            // write pre-init user code here
fileBrowser = new FileBrowser (getDisplay ());//GEN-BEGIN:|49-getter|1|49-postInit
fileBrowser.setTitle ("Open File");
fileBrowser.setCommandListener (this);
fileBrowser.addCommand (FileBrowser.SELECT_FILE_COMMAND);//GEN-END:|49-getter|1|49-postInit
            // write post-init user code here
}//GEN-BEGIN:|49-getter|2|
return fileBrowser;
}
//</editor-fold>//GEN-END:|49-getter|2|



//<editor-fold defaultstate="collapsed" desc=" Generated Getter: alertBigFile ">//GEN-BEGIN:|53-getter|0|53-preInit
/**
 * Returns an initiliazed instance of alertBigFile component.
 * @return the initialized component instance
 */
public Alert getAlertBigFile () {
if (alertBigFile == null) {//GEN-END:|53-getter|0|53-preInit
 // write pre-init user code here
alertBigFile = new Alert ("warning", "Image size is too big!\nYou can\'t process the image, but you still can save the image.", null, AlertType.WARNING);//GEN-BEGIN:|53-getter|1|53-postInit
alertBigFile.setTimeout (Alert.FOREVER);//GEN-END:|53-getter|1|53-postInit
 // write post-init user code here
}//GEN-BEGIN:|53-getter|2|
return alertBigFile;
}
//</editor-fold>//GEN-END:|53-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: image1 ">//GEN-BEGIN:|54-getter|0|54-preInit
/**
 * Returns an initiliazed instance of image1 component.
 * @return the initialized component instance
 */
public Image getImage1 () {
if (image1 == null) {//GEN-END:|54-getter|0|54-preInit
 // write pre-init user code here
try {//GEN-BEGIN:|54-getter|1|54-@java.io.IOException
image1 = Image.createImage ("/imaxe.png");
} catch (java.io.IOException e) {//GEN-END:|54-getter|1|54-@java.io.IOException
e.printStackTrace ();
}//GEN-LINE:|54-getter|2|54-postInit
 // write post-init user code here
}//GEN-BEGIN:|54-getter|3|
return image1;
}
//</editor-fold>//GEN-END:|54-getter|3|















    /**
     * Returns a display instance.
     * @return the display instance.
     */
    public Display getDisplay() {
        return Display.getDisplay(this);
    }

    /**
     * Exits MIDlet.
     */
    public void exitMIDlet() {
        switchDisplayable(null, null);
        destroyApp(true);
        notifyDestroyed();
    }

    /**
     * Called when MIDlet is started.
     * Checks whether the MIDlet have been already started and initialize/starts or resumes the MIDlet.
     */
    public void startApp() {
        if (midletPaused) {
            resumeMIDlet();
        } else {
            initialize();
            startMIDlet();
        }
        midletPaused = false;
    }

    /**
     * Called when MIDlet is paused.
     */
    public void pauseApp() {
        midletPaused = true;
    }

    /**
     * Called to signal the MIDlet to terminate.
     * @param unconditional if true, then the MIDlet has to be unconditionally terminated and all resources has to be released.
     */
    public void destroyApp(boolean unconditional) {
    }
    
}
