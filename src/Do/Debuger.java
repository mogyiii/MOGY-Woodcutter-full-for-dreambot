package Do;

import org.dreambot.api.methods.map.Tile;
import woodcutter.MainClass;

public class Debuger{
    private String treeCloses = "";
    private Tile TreeTile;
    private MainClass mainClass;
    private Factory _factory;
    public Debuger(MainClass main, Factory factory) {
        mainClass = main;
        _factory = factory;
    }

    public void debug(){
        treeCloses = "X: " + mainClass.getGameObjects().closest(mainClass.getWindow().getTreetype()).getX() + ", Y: " + mainClass.getGameObjects().closest(mainClass.getWindow().getTreetype()).getY();
        if(!mainClass.getWindow().getTreetype().equals("Tree")){
            TreeTile = mainClass.getGameObjects().closest(mainClass.getWindow().getTreetype()).getTile();
        }else{
            TreeTile = mainClass.getGameObjects().closest(_factory.getChecking().checktreeType()).getTile();
        }
    }

    public String getTreeCloses() {
        return treeCloses;
    }

    public void setTreeCloses(String treeCloses) {
        this.treeCloses = treeCloses;
    }

    public Tile getTreeTile() {
        return TreeTile;
    }

    public void setTreeTile(Tile treeTile) {
        TreeTile = treeTile;
    }
}
