package Do;

import org.dreambot.api.wrappers.interactive.NPC;
import woodcutter.MainClass;

public class DoLogs {
    private boolean lighting;
    private Factory _factory;
    private MainClass mainClass;
    public DoLogs(MainClass main, Factory factory) {
    mainClass = main;
    _factory = factory;
    }

    public void burnlogs(String logname){
        _factory.getIU().SetThought( "i hope, i find Bernie!");
        lighting = true;
        while(mainClass.getInventory().contains(logname)){
            mainClass.log(logname);
            if(mainClass.getLocalPlayer().isAnimating()){
                _factory.getIU().SetThought("Light up now!!!");
                mainClass.sleep(1500, 3000);
            }else if(mainClass.getPlayers().localPlayer().isMoving()){
                _factory.getIU().SetThought("Moving...");
                mainClass.sleep(200,300);
            }else if(!lighting){
                mainClass.getWalking().walk(_factory.getSelectAreas().getCurrentArea().getRandomTile());
                mainClass.sleep(200,300);
                lighting = true;
            }else{
                _factory.getIU().SetThought("Burning...");
                mainClass.getInventory().interact("Tinderbox", "Use");
                mainClass.getInventory().interact(logname, "Use");
                mainClass.sleep(1500, 3000);
            }
        }
    }
    public void banking(){
        _factory.getIU().SetActivity("Banking");
        _factory.getIU().SetThought( "take care of it!");
        NPC banker = mainClass.getNpcs().closest(npc -> npc != null && npc.hasAction("Bank"));
        if (banker.interact("Bank")) {
            if (mainClass.sleepUntil(() -> mainClass.getBank().open(), 9000)) {
                mainClass.getBank().depositAllExcept(_factory.getChecking().checkaxe());
                mainClass.getBank().close();
                mainClass.sleep(200, 3000);
            }
        }
    }
    public void selling(String logname){
        _factory.getIU().SetActivity("Selling");
        NPC shopper = mainClass.getNpcs().closest(npc -> npc != null && npc.hasAction("Trade"));
        if(shopper.interact("Trade")){
            if(mainClass.sleepUntil(() ->mainClass.getShop().open(), 9000)){
                mainClass.getShop().sellFifty(logname);
                mainClass.getShop().close();
                if(mainClass.getInventory().contains(logname)){
                    mainClass.getInventory().dropAll(logname);
                }
                mainClass.sleep(500, 3000);
            }
        }
    }
    //getters and setters
    public boolean isLighting() {
        return lighting;
    }

    public void setLighting(boolean lighting) {
        this.lighting = lighting;
    }
}
