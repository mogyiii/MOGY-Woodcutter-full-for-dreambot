package woodcutter;

import GUI.JWindow;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.SkillTracker;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.wrappers.widgets.message.Message;
import java.awt.*;
@ScriptManifest(category = Category.WOODCUTTING, name = "Mogy Woodcutter", author = "Mogyiii", version = 2.5)

public class MainClass extends AbstractScript  {
    private GUI.JWindow window;
    private boolean starter = false;
    private int range = 5;
    private Do.Factory factory;
    Area Selectedarea = new Area();
    @Override
    public void onStart(){
        window = new GUI.JWindow(this);
        getWindow().setVisible(true);
        this.factory = new Do.Factory(this);
        GetFactory().getIU().SetActivity( "Starting");
    }
    @Override
    public void onExit() {
        super.onExit();
    }
    @Override
    public void onMessage(Message message) {
        if(message.getMessage().contains("You get some")){
            GetFactory().getInterfaceGraphics().setLogcuts(GetFactory().getInterfaceGraphics().getLogcuts() + 1);
        }else if(message.getMessage().contains("The fire catches")){
            GetFactory().getInterfaceGraphics().setBurned_logs(GetFactory().getInterfaceGraphics().getBurned_logs() + 1);
        }else if(message.getMessage().contains("You can't light a fire here.")){
            GetFactory().getIU().SetThought("I can't light a fire here!!");
            GetFactory().getDoLogs().setLighting(false);
        }else if(message.getMessage().contains("I can't reach that!")){

            if(GameObjects.closest("Wilderness Ditch").exists()){
                GetFactory().getIU().SetActivity( "Jump!");
                GetFactory().getDoWalk().WildernessJumping();
            }else {
                GetFactory().getDoWalk().walkingtoarea(Selectedarea);
            }
        }else{
            GetFactory().getMessage().Answer(message, window);
        }
    }
    @Override
    public void onPaint(Graphics graphics) {
        GetFactory().getInterfaceGraphics().Drawn(graphics, getWindow());
    }


    @Override
    public int onLoop() {

        if(isStarter()) {
            if(GetFactory().getInterfaceGraphics().isStarting()){
                range = getWindow().getAreaSize();
                GetFactory().getTime().setStartTime(System.currentTimeMillis());
                GetFactory().getSelectAreas().setCurrentArea(Area.generateArea(range, getLocalPlayer().getTile()));
                GetFactory().getInterfaceGraphics().setStarting(false);
                SkillTracker.start(Skill.WOODCUTTING);
                SkillTracker.start(Skill.FIREMAKING);
            }
            GetFactory().getSelectAreas().SelectedArea(getWindow().getAreaLocation(), getWindow().getTreetype());
            GetFactory().getAntiBan().randomAntiBan();
        }
        if(getWindow().getisenableDebbuger()){
            GetFactory().getDebuger().debug();
        }
        GetFactory().getAreas().SetClosesBank(Bank.getClosestBankLocation().getCenter().getArea(2));
        return ((int) (Math.random() * 20));
    }

    //Setters

    public void setSelectedarea(Area selectedarea) {
        Selectedarea = selectedarea;
    }

    public void setStarter(boolean starter) {this.starter = starter;}

    //Getters

    public Area getSelectedarea() {
        return Selectedarea;
    }

    public boolean isStarter() {
        return starter;
    }

    public JWindow getWindow() {
        return window;
    }

    public Do.Factory GetFactory() {
        return factory;
    }
}
