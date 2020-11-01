package Do;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.Shop;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.wrappers.interactive.NPC;

public class DoLogs {
    private boolean lighting;
    private Factory _factory;
    public DoLogs(Factory factory) {
        _factory = factory;
    }

    public void burnlogs(String logname){
        _factory.getIU().SetThought( "i hope, i find Bernie!");
        lighting = true;
        while(Inventory.contains(logname)){
            _factory.getMain().log(logname);
            if(_factory.getMain().getLocalPlayer().isAnimating()){
                _factory.getIU().SetThought("Light up now!!!");
                _factory.getMain().sleep(1500, 3000);
            }else if(Players.localPlayer().isMoving()){
                _factory.getIU().SetThought("Moving...");
                _factory.getMain().sleep(200,300);
            }else if(!lighting){
                Walking.walk(_factory.getSelectAreas().getCurrentArea().getRandomTile());
                _factory.getMain().sleep(200,300);
                lighting = true;
            }else{
                _factory.getIU().SetThought("Burning...");
                Inventory.interact("Tinderbox", "Use");
                Inventory.interact(logname, "Use");
                _factory.getMain().sleep(1500, 3000);
            }
        }
    }
    public void banking(){
        _factory.getIU().SetActivity("Banking");
        _factory.getIU().SetThought( "take care of it!");
        NPC banker = NPCs.closest(npc -> npc != null && npc.hasAction("Bank"));
        if (banker.interact("Bank")) {
            if (_factory.getMain().sleepUntil(() -> Bank.open(), 9000)) {
                Bank.depositAllExcept(_factory.getChecking().checkaxe());
                _factory.getMain().sleep(500, 1500);
                Bank.close();
                _factory.getMain().sleep(200, 3000);
            }
        }
    }
    public void selling(String logname){
        _factory.getIU().SetActivity("Selling");
        NPC shopper = NPCs.closest(npc -> npc != null && npc.hasAction("Trade"));
        if(shopper.interact("Trade")){
            if(_factory.getMain().sleepUntil(() -> Shop.open(), 9000)){
                Shop.sellFifty(logname);
                Shop.close();
                if(Inventory.contains(logname)){
                    Inventory.dropAll(logname);
                }
                _factory.getMain().sleep(500, 3000);
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
