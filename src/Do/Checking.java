package Do;

import woodcutter.MainClass;

public class Checking{
    private Factory _factory;
    private MainClass mainClass;
    public Checking(MainClass main, Factory factory) {
        mainClass = main;
        _factory = factory;
    }

    public String checkaxe(){
        String[] axes = {"Infernal axe", "3rd age axe", "Dragon axe", "Rune axe", "Adamant axe", "Mithril axe", "Black axe", "Blessed axe", "Steel axe", "Iron axe", "Bronze axe"};
        for(int i = 0; i < axes.length; i++) {
            if (mainClass.getInventory().contains(axes[i])){
                _factory.getIU().SetThought(axes[i] + " is exist!");
                return axes[i];
            }
        }
        return  "";
    }
    public boolean checktinderbox(){
        if (mainClass.getInventory().contains("Tinderbox")){
            _factory.getIU().SetThought( "Tinderbox is exist!");
            return true;
        }else{
            return false;
        }
    }
    public String checktreeType(){
        String[] trees = {"Dying tree", "Evergreen", "Jungle tree", "Dead tree", "Tree"};
        for(int i = 0; i < trees.length; i++) {
            _factory.getIU().SetThought( trees[i] + " is " +String.valueOf(mainClass.getGameObjects().all().toString().contains(trees[i])));
            if (mainClass.getGameObjects().all().toString().contains(trees[i])){
                return trees[i];
            }
        }
        return "";
    }
}
