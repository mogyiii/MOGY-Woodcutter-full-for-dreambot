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
    private XPs XP;
    private InterfaceGraphics IG;
    private Debuger DB;
    private AnitBan AB;
    private Areas Areas;
    private AnswareMessage message;
    public Factory(MainClass main) {
        this._main = main;
        IU = new InteractionUser(main, this);
        DW = new DoWalk(main, this);
        CK = new Checking(main, this);
        CA = new ChopAction(main, this);
        CR = new Cutters(main, this);
        DL = new DoLogs(main, this);
        SA = new SelectAreas(main, this);
        Time = new Time(main, this);
        XP = new XPs(main, this);
        IG = new InterfaceGraphics(main, this);
        DB = new Debuger(main, this);
        AB = new AnitBan(main, this);
        Areas = new Areas(main, this);
        message = new AnswareMessage(main, this);
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

    public XPs getXPs() {
        return XP;
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

    public MainClass get_mainClass() {
        return _main;
    }

    public AnswareMessage getMessage() {
        return message;
    }
}
