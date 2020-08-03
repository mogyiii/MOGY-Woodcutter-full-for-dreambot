package woodcutter;

import GUI.JWindow;
import org.dreambot.api.data.GameState;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.wrappers.widgets.message.Message;
import java.awt.*;
@ScriptManifest(category = Category.WOODCUTTING, name = "Mogy Woodcutter", author = "Mogyiii", version = 2.4)

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
        GetFactory().getTime().setStartTime(System.currentTimeMillis());
        GetFactory().getSelectAreas().setCurrentArea(new Area(getLocalPlayer().getX() -range,getLocalPlayer().getY() -range,getLocalPlayer().getX() +range,getLocalPlayer().getY()+range));
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

            if(getGameObjects().closest("Wilderness Ditch").exists()){
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
                GetFactory().getSelectAreas().setCurrentArea(new Area(getLocalPlayer().getX() -range,getLocalPlayer().getY() -range,getLocalPlayer().getX() +range,getLocalPlayer().getY()+range));
                GetFactory().getInterfaceGraphics().setStarting(false);
                getSkillTracker().start(Skill.WOODCUTTING);
                getSkillTracker().start(Skill.FIREMAKING);
                GetFactory().getXPs().setCurrent_woodcutting_xp(getSkillTracker().getGainedExperience(Skill.WOODCUTTING));
                GetFactory().getXPs().setCurrent_firemaking_xp(getSkillTracker().getGainedExperience(Skill.FIREMAKING));
                GetFactory().getXPs().setStartedlevelup(getSkills().getRealLevel(Skill.WOODCUTTING));
                GetFactory().getXPs().setFiremakingStartedlevelup(getSkills().getRealLevel(Skill.FIREMAKING));
            }
            GetFactory().getSelectAreas().SelectedArea(getWindow().getAreaLocation(), getWindow().getTreetype());
            GetFactory().getAntiBan().random_AntiBan();
        }
        if(getWindow().getisenableDebbuger()){
            GetFactory().getDebuger().debug();
        }
        GetFactory().getAreas().SetClosesBank(getBank().getClosestBankLocation().getCenter().getArea(2));
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
