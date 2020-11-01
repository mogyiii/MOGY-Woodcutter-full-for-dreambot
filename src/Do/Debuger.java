package Do;

import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.map.Tile;

public class Debuger{
    private String treeCloses = "";
    private Tile TreeTile;
    private Factory _factory;
    public Debuger(Factory factory) {
        _factory = factory;
    }

    public void debug(){
        treeCloses = "X: " + GameObjects.closest(_factory.getMain().getWindow().getTreetype()).getX() + ", Y: " + GameObjects.closest(_factory.getMain().getWindow().getTreetype()).getY();
        if(!_factory.getMain().getWindow().getTreetype().equals("Tree")){
            TreeTile = GameObjects.closest(_factory.getMain().getWindow().getTreetype()).getTile();
        }else{
            TreeTile = GameObjects.closest(_factory.getChecking().checktreeType()).getTile();
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
