import com.encoder.bmp.BMPGenerator;
import java.io.OutputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.microedition.lcdui.*;
import com.encoder.jpg.*;
import com.encoder.png.PNG;

/**
 * @author Dimas Aryo
 */
public class vdSaveFile implements CommandListener, Runnable {
    
    private imaxeApp imaxeapp;
    private byte[] encodedImage;
    private Thread saveProccess;
    private String fileformat;

//<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
private Form fSaveFile;
private TextField filename;
private ChoiceGroup fileFormat;
private Command cmdSave;
private Command cmdCancel;
private Command testCommand;
//</editor-fold>//GEN-END:|fields|0|
    /**
     * The vdSaveFile constructor.
     * @param midlet the midlet used for getting
     */
    public vdSaveFile(imaxeApp imaxeapp) {
        this.imaxeapp = imaxeapp;

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

//<editor-fold defaultstate="collapsed" desc=" Generated Method: switchDisplayable ">//GEN-BEGIN:|2-switchDisplayable|0|2-preSwitch
/**
 * Switches a current displayable in a display. The <code>display</code> instance is taken from <code>getDisplay</code> method. This method is used by all actions in the design for switching displayable.
 * @param alert the Alert which is temporarily set to the display; if <code>null</code>, then <code>nextDisplayable</code> is set immediately
 * @param nextDisplayable the Displayable to be set
 */
public void switchDisplayable (Alert alert, Displayable nextDisplayable) {//GEN-END:|2-switchDisplayable|0|2-preSwitch
        // write pre-switch user code here
Display display = getDisplay ();//GEN-BEGIN:|2-switchDisplayable|1|2-postSwitch
if (alert == null) {
display.setCurrent (nextDisplayable);
} else {
display.setCurrent (alert, nextDisplayable);
}//GEN-END:|2-switchDisplayable|1|2-postSwitch
        // write post-switch user code here
}//GEN-BEGIN:|2-switchDisplayable|2|
//</editor-fold>//GEN-END:|2-switchDisplayable|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: fSaveFile ">//GEN-BEGIN:|11-getter|0|11-preInit
/**
 * Returns an initiliazed instance of fSaveFile component.
 * @return the initialized component instance
 */
public Form getFSaveFile () {
if (fSaveFile == null) {//GEN-END:|11-getter|0|11-preInit
            // write pre-init user code here
fSaveFile = new Form ("Save File", new Item[] { getFilename (), getFileFormat () });//GEN-BEGIN:|11-getter|1|11-postInit
fSaveFile.addCommand (getCmdSave ());
fSaveFile.addCommand (getCmdCancel ());
fSaveFile.setCommandListener (this);//GEN-END:|11-getter|1|11-postInit
            // write post-init user code here
}//GEN-BEGIN:|11-getter|2|
return fSaveFile;
}
//</editor-fold>//GEN-END:|11-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Displayables ">//GEN-BEGIN:|4-commandAction|0|4-preCommandAction
/**
 * Called by a system to indicated that a command has been invoked on a particular displayable.
 * @param command the Command that was invoked
 * @param displayable the Displayable where the command was invoked
 */
public void commandAction (Command command, Displayable displayable) {//GEN-END:|4-commandAction|0|4-preCommandAction
        // write pre-action user code here
if (displayable == fSaveFile) {//GEN-BEGIN:|4-commandAction|1|17-preAction
if (command == cmdCancel) {//GEN-END:|4-commandAction|1|17-preAction
                // write pre-action user code here
//GEN-LINE:|4-commandAction|2|17-postAction
                switchDisplayable(null,imaxeapp.getFHome());
} else if (command == cmdSave) {//GEN-LINE:|4-commandAction|3|15-preAction
                try{
                    saveProccess = new Thread(this);
                    saveProccess.start();
                }catch(Exception e){
                    Alert alert = new Alert("Saving Failed","Image not saved",null,AlertType.ERROR);
                    alert.setTimeout(Alert.FOREVER);
                    switchDisplayable(alert,this.getFSaveFile());
                }
//GEN-LINE:|4-commandAction|4|15-postAction
                // write post-action user code here
}//GEN-BEGIN:|4-commandAction|5|4-postCommandAction
}//GEN-END:|4-commandAction|5|4-postCommandAction
        // write post-action user code here
}//GEN-BEGIN:|4-commandAction|6|
//</editor-fold>//GEN-END:|4-commandAction|6|


//<editor-fold defaultstate="collapsed" desc=" Generated Getter: filename ">//GEN-BEGIN:|13-getter|0|13-preInit
/**
 * Returns an initiliazed instance of filename component.
 * @return the initialized component instance
 */
public TextField getFilename () {
if (filename == null) {//GEN-END:|13-getter|0|13-preInit
            // write pre-init user code here
filename = new TextField ("Nama Berkas", "image", 32, TextField.ANY);//GEN-LINE:|13-getter|1|13-postInit
            // write post-init user code here
}//GEN-BEGIN:|13-getter|2|
return filename;
}
//</editor-fold>//GEN-END:|13-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: cmdSave ">//GEN-BEGIN:|14-getter|0|14-preInit
/**
 * Returns an initiliazed instance of cmdSave component.
 * @return the initialized component instance
 */
public Command getCmdSave () {
if (cmdSave == null) {//GEN-END:|14-getter|0|14-preInit
            // write pre-init user code here
cmdSave = new Command ("Save", Command.OK, 0);//GEN-LINE:|14-getter|1|14-postInit
            // write post-init user code here
}//GEN-BEGIN:|14-getter|2|
return cmdSave;
}
//</editor-fold>//GEN-END:|14-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: cmdCancel ">//GEN-BEGIN:|16-getter|0|16-preInit
/**
 * Returns an initiliazed instance of cmdCancel component.
 * @return the initialized component instance
 */
public Command getCmdCancel () {
if (cmdCancel == null) {//GEN-END:|16-getter|0|16-preInit
            // write pre-init user code here
cmdCancel = new Command ("Cancel", Command.CANCEL, 0);//GEN-LINE:|16-getter|1|16-postInit
            // write post-init user code here
}//GEN-BEGIN:|16-getter|2|
return cmdCancel;
}
//</editor-fold>//GEN-END:|16-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: fileFormat ">//GEN-BEGIN:|18-getter|0|18-preInit
/**
 * Returns an initiliazed instance of fileFormat component.
 * @return the initialized component instance
 */
public ChoiceGroup getFileFormat () {
if (fileFormat == null) {//GEN-END:|18-getter|0|18-preInit
 // write pre-init user code here
fileFormat = new ChoiceGroup ("Format Berkas", Choice.EXCLUSIVE);//GEN-BEGIN:|18-getter|1|18-postInit
fileFormat.append ("JPEG", null);
fileFormat.append ("BMP", null);
fileFormat.append ("PNG", null);
fileFormat.setSelectedFlags (new boolean[] { false, false, false });//GEN-END:|18-getter|1|18-postInit
 // write post-init user code here
}//GEN-BEGIN:|18-getter|2|
return fileFormat;
}
//</editor-fold>//GEN-END:|18-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: testCommand ">//GEN-BEGIN:|23-getter|0|23-preInit
/**
 * Returns an initiliazed instance of testCommand component.
 * @return the initialized component instance
 */
public Command getTestCommand () {
if (testCommand == null) {//GEN-END:|23-getter|0|23-preInit
 // write pre-init user code here
testCommand = new Command ("Ok", Command.OK, 0);//GEN-LINE:|23-getter|1|23-postInit
 // write post-init user code here
}//GEN-BEGIN:|23-getter|2|
return testCommand;
}
//</editor-fold>//GEN-END:|23-getter|2|



    /**
     * Returns a display instance.
     * @return the display instance.
     */
    public Display getDisplay() {
        return Display.getDisplay(imaxeapp);
    }

    /**
     * Exits MIDlet.
     * Note you have to implement proper MIDlet destroying.
     */
    public void exitMIDlet() {
        switchDisplayable(null, null);
        // midlet.destroyApp(true);
        imaxeapp.notifyDestroyed();
    }

    public void run() {
        try{
            
            int indexFileFormat = fileFormat.getSelectedIndex();
            if(indexFileFormat==0){
                JPGEncoder encoder = new JPGEncoder();
                int quality = 80;
                this.encodedImage = encoder.encode(imaxeapp.getTempImage(), quality);
                this.fileformat="jpeg";
                
            } else if(indexFileFormat == 1){
                this.encodedImage = BMPGenerator.encodeBMP(imaxeapp.getTempImage());
                this.fileformat="bmp";
                
            } else if(indexFileFormat == 2){
                Image temp = imaxeapp.getTempImage();
                int[] resultData = new int[temp.getWidth()*temp.getHeight()];
                temp.getRGB(resultData, 0, temp.getWidth(), 0, 0, temp.getWidth(), temp.getHeight());
                byte[] alpha = new byte[temp.getWidth()*temp.getHeight()];
                byte[] red = new byte[temp.getWidth()*temp.getHeight()];
                byte[] green = new byte[temp.getWidth()*temp.getHeight()];
                byte[] blue = new byte[temp.getWidth()*temp.getHeight()];
                for(int i=0;i<temp.getWidth()*temp.getHeight();i++){
                    alpha[i] =(byte) ((resultData[i]>>24) & 0xff);
                    red[i] = (byte) ((resultData[i]>>16) & 0xff);
                    green[i] = (byte) ((resultData[i] >> 8) & 0xff);
                    blue[i] = (byte) (resultData[i] & 0xff);
                }
                
                this.encodedImage = PNG.toPNG(temp.getWidth(), temp.getHeight(), alpha, red, green, blue);
                this.fileformat = "png";
                
            }
            
            FileConnection dirConn = (FileConnection) Connector.open("file:///E:/imaxeapp");
            if(!dirConn.exists()){
                dirConn.mkdir();
            }
            dirConn.close();
            FileConnection outConn = (FileConnection) Connector.open("file:///E:/imaxeapp/"+filename.getString()+"."+fileformat);
            if(!outConn.exists()){
                outConn.create();
            }
            OutputStream outstream = outConn.openOutputStream();
            outstream.write(encodedImage);
            outstream.flush();
            outstream.close();
            outConn.close();
            Alert alert = new Alert("Saving Successfully","Image has been saved",null,AlertType.INFO);
            alert.setTimeout(Alert.FOREVER);
            switchDisplayable(alert,imaxeapp.getFHome());
        } catch(Exception e){
        
        }
    }
    
}
