package Do;

import org.dreambot.api.methods.map.Area;
import woodcutter.MainClass;

public class Cutters{
    private boolean first = true;
    private MainClass mainClass;
    private Factory _factory;
    public Cutters(MainClass main, Factory factory) {
        mainClass = main;
        _factory = factory;
    }


    public void MultiCutter(Area bank_area, Area Selected_area, String tree_type, String logs_type){
        _factory.getSelectAreas().setCurrentArea(Selected_area);
        if(mainClass.getInventory().isFull()){
            if(mainClass.getWindow().getburn() && _factory.getChecking().checktinderbox()){
                _factory.getIU().SetActivity( "Burn logs");
                _factory.getDoLogs().burnlogs(logs_type);
            }else if(mainClass.getWindow().getburn() && !_factory.getChecking().checktinderbox()){
                _factory.getIU().SetActivity("Dropping logs");
                _factory.getIU().SetThought( "oh no :(");
                mainClass.getInventory().dropAll(logs_type);
            }else{
                _factory.getDoWalk().walkingtoarea(bank_area);
                if (bank_area.contains(mainClass.getLocalPlayer())) {
                    _factory.getDoLogs().banking();
                }
            }
            mainClass.sleep(500, 1000);
        }else{
            if(Selected_area.contains(mainClass.getLocalPlayer())){
                if(mainClass.getLocalPlayer().isAnimating()){
                    _factory.getChopAction().cutting();
                }else{
                    if(!mainClass.getPlayers().localPlayer().isMoving()){
                        _factory.getChopAction().chop(tree_type);
                        mainClass.sleep(200, 400);
                    }
                }
            }else{
                _factory.getDoWalk().walkingtoarea(Selected_area.getCenter().getArea(2));
            }
        }
    }
    public void MultiTypeCutter(Area bank_area, Area Selected_area, String logs_type){
        _factory.getSelectAreas().setCurrentArea(Selected_area);
        if(mainClass.getInventory().isFull()){
            if(mainClass.getWindow().getburn() && _factory.getChecking().checktinderbox()){
                _factory.getIU().SetActivity( "Burn logs");
                _factory.getDoLogs().burnlogs(logs_type);
            }else if(mainClass.getWindow().getburn() && !_factory.getChecking().checktinderbox()){
                _factory.getIU().SetActivity("Dropping logs");
                mainClass.getInventory().dropAll(logs_type);
            }else{
                _factory.getDoWalk().walkingtoarea(bank_area);
                if (bank_area.contains(mainClass.getLocalPlayer())) {
                    _factory.getDoLogs().banking();
                }
            }
            mainClass.sleep(500, 1000);
        }else{
            if(Selected_area.contains(mainClass.getLocalPlayer())){
                if(mainClass.getLocalPlayer().isAnimating()){
                    _factory.getChopAction().cutting();
                }else{
                    if(!mainClass.getPlayers().localPlayer().isMoving()){
                        _factory.getChopAction().chop(_factory.getChecking().checktreeType());
                        mainClass.sleep(200, 400);
                    }
                }
            }else{
                _factory.getDoWalk().walkingtoarea(Selected_area);
            }
        }
    }
    public void remoteBankcutter(Area bank){
        if(first){
            mainClass.setSelectedarea(bank);
            first = false;
        }
        _factory.getSelectAreas().setCurrentArea(mainClass.getSelectedarea());
        if(mainClass.getInventory().isFull()){
            if(_factory.get_mainClass().getWindow().getburn() && _factory.getChecking().checktinderbox()){
                _factory.getIU().SetActivity( "Burn logs");
                if(mainClass.getWindow().getTreetype().contains("Tree")){
                    _factory.getDoLogs().burnlogs("Logs");
                }else{
                    _factory.getDoLogs().burnlogs(mainClass.getWindow().getTreetype() + " logs");
                }
            }else {
                _factory.getDoWalk().walkingtoarea(bank);
                if (bank.contains(mainClass.getLocalPlayer())) {
                    _factory.getDoLogs().banking();
                }
            }
            mainClass.sleep(500, 1500);
        }else{
            if(mainClass.getSelectedarea().contains(mainClass.getLocalPlayer())){
                if(mainClass.getLocalPlayer().isAnimating()){
                    _factory.getChopAction().cutting();
                }else{
                    if(_factory.getChopAction().IsChopping(mainClass.getWindow().getTreetype())){
                        mainClass.setSelectedarea(new Area(mainClass.getGameObjects().closest(mainClass.getWindow().getTreetype()).getX() - 3,mainClass.getGameObjects().closest(mainClass.getWindow().getTreetype()).getY() -3,mainClass.getGameObjects().closest(mainClass.getWindow().getTreetype()).getX() +3,mainClass.getGameObjects().closest(mainClass.getWindow().getTreetype()).getY()+3));
                        _factory.getChopAction().chop(mainClass.getWindow().getTreetype());
                        mainClass.sleep(300, 1000);
                    }
                }
            }else{
                _factory.getDoWalk().walkingtoarea(mainClass.getSelectedarea());
            }
        }

    }
}
