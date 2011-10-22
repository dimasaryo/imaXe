import javax.microedition.lcdui.*;

/**
 * @author Dimas Aryo
 */
public class vdScale implements CommandListener,ItemStateListener,Runnable {
    
    private imaxeApp imaxeapp;
    private Thread rescale;

//<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
private Form form;
private ChoiceGroup aspectRatio;
private TextField targetHeight;
private TextField targetWidth;
private Command cmdProcess;
private Command cmdCancel;
//</editor-fold>//GEN-END:|fields|0|
    /**
     * The vdScale constructor.
     * @param midlet the midlet used for getting
     */
    public vdScale(imaxeApp imaxeapp) {
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

//<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Displayables ">//GEN-BEGIN:|4-commandAction|0|4-preCommandAction
/**
 * Called by a system to indicated that a command has been invoked on a particular displayable.
 * @param command the Command that was invoked
 * @param displayable the Displayable where the command was invoked
 */
public void commandAction (Command command, Displayable displayable) {//GEN-END:|4-commandAction|0|4-preCommandAction
        // write pre-action user code here
if (displayable == form) {//GEN-BEGIN:|4-commandAction|1|19-preAction
if (command == cmdCancel) {//GEN-END:|4-commandAction|1|19-preAction
                switchDisplayable(null,imaxeapp.chome);
//GEN-LINE:|4-commandAction|2|19-postAction
                // write post-action user code here
} else if (command == cmdProcess) {//GEN-LINE:|4-commandAction|3|17-preAction
                rescale = new Thread(this);
                rescale.start();
//GEN-LINE:|4-commandAction|4|17-postAction
                // write post-action user code here
}//GEN-BEGIN:|4-commandAction|5|4-postCommandAction
}//GEN-END:|4-commandAction|5|4-postCommandAction
        // write post-action user code here
}//GEN-BEGIN:|4-commandAction|6|
//</editor-fold>//GEN-END:|4-commandAction|6|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: form ">//GEN-BEGIN:|11-getter|0|11-preInit
/**
 * Returns an initiliazed instance of form component.
 * @return the initialized component instance
 */
public Form getForm () {
if (form == null) {//GEN-END:|11-getter|0|11-preInit
            // write pre-init user code here
form = new Form ("New Image Size", new Item[] { getTargetWidth (), getTargetHeight (), getAspectRatio () });//GEN-BEGIN:|11-getter|1|11-postInit
form.addCommand (getCmdProcess ());
form.addCommand (getCmdCancel ());
form.setCommandListener (this);//GEN-END:|11-getter|1|11-postInit
            form.setItemStateListener(this);
}//GEN-BEGIN:|11-getter|2|
return form;
}
//</editor-fold>//GEN-END:|11-getter|2|





//<editor-fold defaultstate="collapsed" desc=" Generated Getter: aspectRatio ">//GEN-BEGIN:|14-getter|0|14-preInit
/**
 * Returns an initiliazed instance of aspectRatio component.
 * @return the initialized component instance
 */
public ChoiceGroup getAspectRatio () {
if (aspectRatio == null) {//GEN-END:|14-getter|0|14-preInit
            // write pre-init user code here
aspectRatio = new ChoiceGroup ("", Choice.MULTIPLE);//GEN-BEGIN:|14-getter|1|14-postInit
aspectRatio.append ("Aspect Ratio", null);
aspectRatio.setSelectedFlags (new boolean[] { true });//GEN-END:|14-getter|1|14-postInit
            // write post-init user code here
}//GEN-BEGIN:|14-getter|2|
return aspectRatio;
}
//</editor-fold>//GEN-END:|14-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: cmdProcess ">//GEN-BEGIN:|16-getter|0|16-preInit
/**
 * Returns an initiliazed instance of cmdProcess component.
 * @return the initialized component instance
 */
public Command getCmdProcess () {
if (cmdProcess == null) {//GEN-END:|16-getter|0|16-preInit
            // write pre-init user code here
cmdProcess = new Command ("Apply", Command.OK, 0);//GEN-LINE:|16-getter|1|16-postInit
            // write post-init user code here
}//GEN-BEGIN:|16-getter|2|
return cmdProcess;
}
//</editor-fold>//GEN-END:|16-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: cmdCancel ">//GEN-BEGIN:|18-getter|0|18-preInit
/**
 * Returns an initiliazed instance of cmdCancel component.
 * @return the initialized component instance
 */
public Command getCmdCancel () {
if (cmdCancel == null) {//GEN-END:|18-getter|0|18-preInit
            // write pre-init user code here
cmdCancel = new Command ("Cancel", Command.CANCEL, 0);//GEN-LINE:|18-getter|1|18-postInit
            // write post-init user code here
}//GEN-BEGIN:|18-getter|2|
return cmdCancel;
}
//</editor-fold>//GEN-END:|18-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: targetWidth ">//GEN-BEGIN:|20-getter|0|20-preInit
/**
 * Returns an initiliazed instance of targetWidth component.
 * @return the initialized component instance
 */
public TextField getTargetWidth () {
if (targetWidth == null) {//GEN-END:|20-getter|0|20-preInit
            // write pre-init user code here
targetWidth = new TextField ("Image Width", null, 32, TextField.ANY);//GEN-LINE:|20-getter|1|20-postInit
            targetWidth.setString(Integer.toString(imaxeapp.getTempImage().getWidth()));
}//GEN-BEGIN:|20-getter|2|
return targetWidth;
}
//</editor-fold>//GEN-END:|20-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: targetHeight ">//GEN-BEGIN:|21-getter|0|21-preInit
/**
 * Returns an initiliazed instance of targetHeight component.
 * @return the initialized component instance
 */
public TextField getTargetHeight () {
if (targetHeight == null) {//GEN-END:|21-getter|0|21-preInit
            // write pre-init user code here
targetHeight = new TextField ("Image Height", null, 32, TextField.ANY);//GEN-LINE:|21-getter|1|21-postInit
            targetHeight.setString(Integer.toString(imaxeapp.getTempImage().getHeight()));
}//GEN-BEGIN:|21-getter|2|
return targetHeight;
}
//</editor-fold>//GEN-END:|21-getter|2|

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

    public void itemStateChanged(Item item) {
        if(item==targetWidth){
            if(aspectRatio.isSelected(0)){
                int ctargetHeight=(int) (((double)imaxeapp.getTempImage().getHeight()/(double)imaxeapp.getTempImage().getWidth())*Integer.parseInt(targetWidth.getString()));
                targetHeight.setString(Integer.toString(ctargetHeight));
            }
        } else if(item==targetHeight){
            if(aspectRatio.isSelected(0)){
                int ctargetWidth = (int) (((double)imaxeapp.getTempImage().getWidth()/(double)imaxeapp.getTempImage().getHeight())*Integer.parseInt(targetHeight.getString()));
                targetWidth.setString(Integer.toString(ctargetWidth));
            }
        }
    }

    public void run() {
        try{
            geometryProcess gp = new geometryProcess();
            Image tempImage = gp.rescale(imaxeapp.getTempImage(), Integer.parseInt(targetWidth.getString()), Integer.parseInt(targetHeight.getString()));
            imaxeapp.setTempImage(tempImage);
            imaxeapp.chome.setOriginalImage(tempImage);
            imaxeapp.chome.repaint();
            switchDisplayable(null,imaxeapp.chome);
        } catch(Exception e){
        
        }
    }
}
