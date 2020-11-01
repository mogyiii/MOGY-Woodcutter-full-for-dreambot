package Do;

import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.map.Map;
import org.dreambot.api.wrappers.interactive.GameObject;


public class ChopAction {
    private int playerpossitionX;
    private int playerpossitionY;
    private Factory _factory;
    private GameObject Tree;
    public ChopAction(Factory factory) {
        _factory = factory;
    }

    public void cutting(){
        _factory.getIU().SetActivity("Cutting");
        _factory.getIU().SetThought("This's boring!");
        _factory.getMain().sleep(200, 400);
    }
    public void chop(String TreeName){
        _factory.getIU().SetActivity( "Chop");
        GameObject tree = GameObjects.closest(TreeName);
        if(tree != null && _factory.getSelectAreas().getCurrentArea().contains(tree.getTile()) && Map.canReach(tree)){
            if((_factory.getMain().getLocalPlayer().getX() == playerpossitionX && _factory.getMain().getLocalPlayer().getY() == playerpossitionY)){
                Tree = tree;
                tree.interact("Chop down");
            }
            playerpossitionX = _factory.getMain().getLocalPlayer().getX();
            playerpossitionY = _factory.getMain().getLocalPlayer().getY();
            _factory.getMain().sleep(200, 500);
        }else{
            GameObject elseTree;

            for(int i = 0; i < GameObjects.all(TreeName).size(); i++){
                elseTree = GameObjects.all(TreeName).get(i);
                if(elseTree != null && _factory.getSelectAreas().getCurrentArea().contains(elseTree.getTile()) && Map.canReach(elseTree)){
                    Tree = elseTree;
                    elseTree.interact("Chop down");
                    break;
                }
            }
        }
        if(tree.getX() == 0 && tree.getY() == 0){
            _factory.getMain().log("Please delete Jagexcache folder");
            _factory.getIU().SetThought( "ah shit here we go again");
            _factory.getIU().SetActivity( "Please delete Jagexcache folder!");
        }
    }
    public boolean IsChopping(String treename){
        _factory.getIU().SetActivity("Chop");
        _factory.getIU().SetThought("Chop Chop");
        GameObject tree = GameObjects.closest(treename);
        if(tree != null){
            if((_factory.getMain().getLocalPlayer().getX() == playerpossitionX && _factory.getMain().getLocalPlayer().getY() == playerpossitionY)){
                tree.interact("Chop down");
            }
            playerpossitionX = _factory.getMain().getLocalPlayer().getX();
            playerpossitionY = _factory.getMain().getLocalPlayer().getY();
            _factory.getMain().sleep(200, 1000);
            return true;
        }else{
            return false;
        }
    }

    public GameObject getTree() {
        return Tree;
    }
}

