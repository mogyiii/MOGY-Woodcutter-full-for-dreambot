package Do;

import org.dreambot.api.methods.skills.Skill;
import woodcutter.MainClass;

import java.util.Random;

public class AnitBan{
    private MainClass mainClass;
    private Factory _factory;
    public AnitBan(MainClass main, Factory factory) {
        mainClass = main;
        _factory = factory;
    }

    public void random_AntiBan() {

        Random srand = new Random();
        double chances = srand.nextDouble();
        double chances2 = srand.nextDouble();
        if (chances < 0.096) {
            mainClass.log("Anti-ban: Changing Camera angle");
            _factory.getIU().SetActivity("Anti-ban: Changing Camera angle");
            mainClass.getCamera().rotateToEvent(srand.nextInt() + 360, srand.nextInt() + 90);
        }else if(chances > 0.096 && chances < 0.192){
            _factory.getIU().SetThought("hmm...");
            mainClass.log("Anti-ban: Changing mouse position");
            _factory.getIU().SetActivity("Anti-ban: Changing mouse position");
            mainClass.getMouse().move();
        }else if(chances > 0.192 && chances < 0.194){
            _factory.getIU().SetThought("Zzz...");
            mainClass.log("Anti-ban: Random sleep");
            _factory.getIU().SetActivity("Anti-ban: Random sleeping");
            mainClass.sleep(10000, 50000);
        }
        else if(chances > 0.200 && chances < 0.256) {
            _factory.getIU().SetThought("Check XP...");
            mainClass.log("Anti-ban: Checking skill XP");
            _factory.getIU().SetActivity("Anti-ban: Checking skill XP");
            mainClass.getSkills().open();
            mainClass.sleep(100, 500);
            mainClass.getSkills().hoverSkill(Skill.WOODCUTTING);
            mainClass.sleep(1000, 1500);
            mainClass.getKeyboard().typeSpecialKey(1);
        }
        else if(chances > 0.257 && chances < 0.295){
            _factory.getIU().SetThought("Check XP...");
            mainClass.log("Anti-ban: Checking skill XP");
            _factory.getIU().SetActivity("Anti-ban: Checking skill XP");
            mainClass.getSkills().open();
            mainClass.sleep(100, 500);
            mainClass.getSkills().hoverSkill(Skill.FIREMAKING);
            mainClass.sleep(1000, 1500);
            mainClass.getKeyboard().typeSpecialKey(1);

        }else if(chances > 0.296 && chances < 0.350){
            _factory.getIU().SetThought("This music is not good...");
            _factory.getIU().SetActivity("Anti-ban: Move cursor Outside Screen");
            mainClass.log("Anti-ban: Move cursor Outside Screen");
            mainClass.getMouse().moveMouseOutsideScreen();
            mainClass.sleep(2888, 5111);
            mainClass.getMouse().isMouseInScreen();
        }else if(chances > 0.350 && chances < 0.395){
            _factory.getIU().SetThought("What is this?");
            if(chances2 < 0.2){
                mainClass.getWidgets().getWidget(548).getChild(61).interact();
            } else if(chances2 > 0.2 && chances2 < 0.4){
                mainClass.getWidgets().getWidget(548).getChild(43).interact();
            } else{
                mainClass.getWidgets().getWidget(548).getChild(40).interact();
            }
            _factory.getIU().SetActivity("Anti-ban: Open Random tab");
            mainClass.log("Anti-ban: Open Random tab");
            mainClass.sleep(200, 500);
        }else if(chances > 0.395 && chances < 0.400){
            if(!mainClass.getWindow().getwhop()) {
                _factory.getIU().SetThought("I don't like this world!");
                _factory.getIU().SetActivity("Anti-ban: Hop world");
                mainClass.log("Anti-ban: Hop world");
                if (!mainClass.getClient().isMembers()) {
                    mainClass.getWorldHopper().hopWorld(mainClass.getWorlds().f2p().get(srand.nextInt(mainClass.getWorlds().getNormalizedWorlds().size())).getID(), mainClass.getWorldHopper().openWorldHopper());
                    mainClass.getWorldHopper().isWorldHopperOpen();
                    mainClass.sleep(5000, 7000);
                } else {
                    mainClass.getWorldHopper().hopWorld(mainClass.getWorlds().members().get(srand.nextInt(mainClass.getWorlds().getNormalizedWorlds().size())).getID(), mainClass.getWorldHopper().openWorldHopper());
                    mainClass.getWorldHopper().isWorldHopperOpen();
                    mainClass.sleep(5000, 7000);
                }
            }
        }
    }
}
