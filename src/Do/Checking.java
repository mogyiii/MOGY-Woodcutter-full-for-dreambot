package Do;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.wrappers.interactive.GameObject;

import java.util.List;

public class Checking{
    private Factory _factory;
    public Checking(Factory factory) {
        _factory = factory;
    }

    public String checkaxe(){
        String[] axes = {"Crystal axe","Infernal axe", "3rd age axe", "Dragon axe", "Gilded axe", "Rune axe", "Adamant axe", "Mithril axe", "Black axe", "Blessed axe", "Steel axe", "Iron axe", "Bronze axe"};
        for(int i = 0; i < axes.length; i++) {
            if (Inventory.contains(axes[i])){
                _factory.getIU().SetThought(axes[i] + " is exist!");
                return axes[i];
            }
        }
        return  "";
    }
    public boolean checktinderbox(){
        if (Inventory.contains("Tinderbox")){
            return true;
        }else{
            return false;
        }
    }
    public String checktreeType(){

        List<GameObject> Trees = GameObjects.all(GameObject -> GameObject != null && (GameObject.getName().equals("Dying tree") ||
                    GameObject.getName().equals("Evergreen") ||
                    GameObject.getName().equals("Jungle tree") ||
                    GameObject.getName().equals("Dead tree") ||
                    GameObject.getName().equals("Tree")) && _factory.getSelectAreas().getCurrentArea().contains(GameObject.getTile()));

        GameObject ReturnTree = GameObjects.closest("Tree");
        for(int i = 0; i < Trees.size(); i++){
            if(Walking.getAStarPathFinder().calculate(Players.localPlayer().getTile(), ReturnTree.getTile()).size() > Walking.getAStarPathFinder().calculate(Players.localPlayer().getTile(), Trees.get(i).getTile()).size()){
                ReturnTree = Trees.get(i);
            }
        }

        return ReturnTree.getName();
    }
}
