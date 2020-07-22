package Do;

import org.dreambot.api.wrappers.interactive.GameObject;
import woodcutter.MainClass;

import java.util.Random;

public class ChopAction {
    private int playerpossitionX;
    private int playerpossitionY;
    private MainClass mainClass;
    private Factory _factory;
    private GameObject Tree;
    public ChopAction(MainClass main, Factory factory) {
        mainClass = main;
        _factory = factory;
    }

    public void cutting(){
        _factory.getIU().SetActivity("Cutting");
        _factory.getIU().SetThought("This's boring!");
        mainClass.sleep(200, 400);
    }
    public void chop(String TreeName){
        _factory.getIU().SetActivity( "Chop");
        GameObject tree = mainClass.getGameObjects().closest(TreeName);
        if(tree != null && _factory.getSelectAreas().getCurrentArea().contains(tree.getTile()) && mainClass.getMap().canReach(tree)){
            if((mainClass.getLocalPlayer().getX() == playerpossitionX && mainClass.getLocalPlayer().getY() == playerpossitionY)){
                Tree = tree;
                tree.interact("Chop down");
            }
            playerpossitionX = mainClass.getLocalPlayer().getX();
            playerpossitionY = mainClass.getLocalPlayer().getY();
            mainClass.sleep(200, 500);
        }else{
            GameObject elseTree;

            for(int i = 0; i < mainClass.getGameObjects().all(TreeName).size(); i++){
                elseTree = mainClass.getGameObjects().all(TreeName).get(i);
                if(elseTree != null && _factory.getSelectAreas().getCurrentArea().contains(elseTree.getTile()) && mainClass.getMap().canReach(elseTree)){
                    Tree = elseTree;
                    elseTree.interact("Chop down");
                    break;
                }
            }
        }
        if(tree.getX() == 0 && tree.getY() == 0){
            mainClass.log("Please delete Jagexcache folder");
            _factory.getIU().SetThought( "ah shit here we go again");
            _factory.getIU().SetActivity( "Please delete Jagexcache folder!");
        }
    }
    public boolean IsChopping(String treename){
        _factory.getIU().SetActivity("Chop");
        _factory.getIU().SetThought("Chop Chop");
        GameObject tree = mainClass.getGameObjects().closest(treename);
        if(tree != null){
            if((mainClass.getLocalPlayer().getX() == playerpossitionX && mainClass.getLocalPlayer().getY() == playerpossitionY)){
                tree.interact("Chop down");
            }
            playerpossitionX = mainClass.getLocalPlayer().getX();
            playerpossitionY = mainClass.getLocalPlayer().getY();
            mainClass.sleep(200, 1000);
            return true;
        }else{
            return false;
        }
    }

    public GameObject getTree() {
        return Tree;
    }
}

