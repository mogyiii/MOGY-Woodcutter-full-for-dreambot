package Do;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.wrappers.interactive.GameObject;
import woodcutter.MainClass;

public class DoWalk{
    private MainClass mainClass;
    private Factory _factory;
    public DoWalk(MainClass main, Factory factory) {
        mainClass = main;
        _factory = factory;
    }

    public void walkingtoarea(Area Selectedarea){
        _factory.getIU().SetActivity( "Walking to area!");
        _factory.getIU().SetThought( "Few can foresee whither their road will lead them, till they come to its end.");
        mainClass.getWalking().walk(Selectedarea.getRandomTile());
        if(mainClass.getWalking().getRunEnergy() >= 20){
            if(!(mainClass.getWalking().isRunEnabled())){
                mainClass.getWalking().toggleRun();
            }
        }

        mainClass.sleep(100, 300);
    }
    public void WildernessJumping(){
        _factory.getIU().SetThought( "Bunny hop");
        GameObject gap = mainClass.getGameObjects().closest("Wilderness Ditch");
        gap.interact("Cross");
        mainClass.sleep(1000, 1500);
        mainClass.getWidgets().getWidget(475).getChild(11).interact();
        mainClass.sleep(100, 250);
    }
}
