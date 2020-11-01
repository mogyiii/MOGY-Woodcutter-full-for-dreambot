package Do;

import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.methods.widget.Widgets;
import org.dreambot.api.wrappers.interactive.GameObject;

public class DoWalk{
    private Factory _factory;
    public DoWalk(Factory factory) {
        _factory = factory;
    }

    public void walkingtoarea(Area Selectedarea){
        _factory.getIU().SetActivity( "Walking to area!");
        _factory.getIU().SetThought( "Few can foresee whither their road will lead them, till they come to its end.");
        Walking.walk(Selectedarea.getRandomTile());
        if(Walking.getRunEnergy() >= 20){
            if(!(Walking.isRunEnabled())){
                Walking.toggleRun();
            }
        }

        _factory.getMain().sleep(100, 300);
    }
    public void WildernessJumping(){
        _factory.getIU().SetThought( "Bunny hop");
        GameObject gap = GameObjects.closest("Wilderness Ditch");
        gap.interact("Cross");
        _factory.getMain().sleep(1000, 1500);
        Widgets.getWidget(475).getChild(11).interact();
        _factory.getMain().sleep(100, 250);
    }
}
