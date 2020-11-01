package Do;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;

public class Cutters{
    private boolean first = true;
    private Factory _factory;
    public Cutters(Factory factory) {
        _factory = factory;
    }


    public void MultiCutter(Area bank_area, Area Selected_area, String tree_type, String logs_type){
        _factory.getSelectAreas().setCurrentArea(Selected_area);
        if(Inventory.isFull()){
            if(_factory.getMain().getWindow().getburn() && _factory.getChecking().checktinderbox()){
                _factory.getIU().SetActivity( "Burn logs");
                _factory.getDoLogs().burnlogs(logs_type);
            }else if(_factory.getMain().getWindow().getburn() && !_factory.getChecking().checktinderbox()){
                _factory.getIU().SetActivity("Dropping logs");
                _factory.getIU().SetThought( "oh no :(");
                Inventory.dropAll(logs_type);
            }else{
                _factory.getDoWalk().walkingtoarea(bank_area);
                if (bank_area.contains(_factory.getMain().getLocalPlayer())) {
                    _factory.getDoLogs().banking();
                }
            }
            _factory.getMain().sleep(500, 1000);
        }else{
            if(Selected_area.contains(_factory.getMain().getLocalPlayer())){
                if(_factory.getMain().getLocalPlayer().isAnimating()){
                    _factory.getChopAction().cutting();
                }else{
                    if(!Players.localPlayer().isMoving()){
                        _factory.getChopAction().chop(tree_type);
                        _factory.getMain().sleep(200, 400);
                    }
                }
            }else{
                _factory.getDoWalk().walkingtoarea(Selected_area.getCenter().getArea(2));
            }
        }
    }
    public void MultiTypeCutter(Area bank_area, Area Selected_area, String logs_type){
        _factory.getSelectAreas().setCurrentArea(Selected_area);
        if(Inventory.isFull()){
            if(_factory.getMain().getWindow().getburn() && _factory.getChecking().checktinderbox()){
                _factory.getIU().SetActivity( "Burn logs");
                _factory.getDoLogs().burnlogs(logs_type);
            }else if(_factory.getMain().getWindow().getburn() && !_factory.getChecking().checktinderbox()){
                _factory.getIU().SetActivity("Dropping logs");
                Inventory.dropAll(logs_type);
            }else{
                _factory.getDoWalk().walkingtoarea(bank_area);
                if (bank_area.contains(_factory.getMain().getLocalPlayer())) {
                    _factory.getDoLogs().banking();
                }
            }
            _factory.getMain().sleep(500, 1000);
        }else{
            if(Selected_area.contains(_factory.getMain().getLocalPlayer())){
                if(_factory.getMain().getLocalPlayer().isAnimating()){
                    _factory.getChopAction().cutting();
                }else{
                    if(!Players.localPlayer().isMoving()){
                        _factory.getChopAction().chop(_factory.getChecking().checktreeType());
                        _factory.getMain().sleep(200, 400);
                    }
                }
            }else{
                _factory.getDoWalk().walkingtoarea(Selected_area);
            }
        }
    }
    public void remoteBankcutter(Area bank){
        if(first){
            _factory.getMain().setSelectedarea(bank);
            first = false;
        }
        _factory.getSelectAreas().setCurrentArea(_factory.getMain().getSelectedarea());
        if(Inventory.isFull()){
            if(_factory.getMain().getWindow().getburn() && _factory.getChecking().checktinderbox()){
                _factory.getIU().SetActivity( "Burn logs");
                if(_factory.getMain().getWindow().getTreetype().contains("Tree")){
                    _factory.getDoLogs().burnlogs("Logs");
                }else{
                    _factory.getDoLogs().burnlogs(_factory.getMain().getWindow().getTreetype() + " logs");
                }
            }else {
                _factory.getDoWalk().walkingtoarea(bank);
                if (bank.contains(_factory.getMain().getLocalPlayer())) {
                    _factory.getDoLogs().banking();
                }
            }
            _factory.getMain().sleep(500, 1500);
        }else{
            if(_factory.getMain().getSelectedarea().contains(_factory.getMain().getLocalPlayer())){
                if(_factory.getMain().getLocalPlayer().isAnimating()){
                    _factory.getChopAction().cutting();
                }else{
                    if(_factory.getChopAction().IsChopping(_factory.getMain().getWindow().getTreetype())){
                        _factory.getMain().setSelectedarea(new Area(GameObjects.closest(_factory.getMain().getWindow().getTreetype()).getX() - 3,
                                GameObjects.closest(_factory.getMain().getWindow().getTreetype()).getY() -3,
                                GameObjects.closest(_factory.getMain().getWindow().getTreetype()).getX() +3,
                                GameObjects.closest(_factory.getMain().getWindow().getTreetype()).getY()+3));
                        _factory.getChopAction().chop(_factory.getMain().getWindow().getTreetype());
                        _factory.getMain().sleep(300, 1000);
                    }
                }
            }else{
                _factory.getDoWalk().walkingtoarea(_factory.getMain().getSelectedarea());
            }
        }

    }
}
