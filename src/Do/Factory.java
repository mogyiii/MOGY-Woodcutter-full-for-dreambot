package Do;

import woodcutter.MainClass;

public class Factory {
    private woodcutter.MainClass _main;
    private InteractionUser IU;
    private DoWalk DW;
    private Checking CK;
    private ChopAction CA;
    private Cutters CR;
    private DoLogs DL;
    private SelectAreas SA;
    private Time Time;
    private InterfaceGraphics IG;
    private Debuger DB;
    private AnitBan AB;
    private Areas Areas;
    private AnswareMessage message;
    public Factory(MainClass main) {
        this._main = main;
        IU = new InteractionUser();
        DW = new DoWalk(this);
        CK = new Checking(this);
        CA = new ChopAction(this);
        CR = new Cutters(this);
        DL = new DoLogs(this);
        SA = new SelectAreas(this);
        Time = new Time();
        IG = new InterfaceGraphics(this);
        DB = new Debuger(this);
        AB = new AnitBan(this);
        Areas = new Areas();
        message = new AnswareMessage(this);
    }

    public InteractionUser getIU() {
        return IU;
    }

    public DoWalk getDoWalk() {return DW;}

    public Checking getChecking() {return CK;}

    public ChopAction getChopAction() {return CA;}

    public Cutters getCutters() {return CR;}

    public DoLogs getDoLogs() {return DL;}

    public SelectAreas getSelectAreas() {return SA;}

    public Do.Time getTime() {
        return Time;
    }

    public InterfaceGraphics getInterfaceGraphics() {
        return IG;
    }

    public Debuger getDebuger() {
        return DB;
    }

    public AnitBan getAntiBan() {
        return AB;
    }

    public Do.Areas getAreas() {
        return Areas;
    }

    public MainClass getMain() {
        return _main;
    }

    public AnswareMessage getMessage() {
        return message;
    }
}
