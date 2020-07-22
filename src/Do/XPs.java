package Do;

import woodcutter.MainClass;

public class XPs{

    private int Startedlevelup;
    private int FiremakingStartedlevelup;
    private long current_woodcutting_xp;
    private long current_firemaking_xp;
    private MainClass mainClass;
    private Factory _factory;
    public XPs(MainClass main, Factory factory) {
        mainClass = main;
        _factory = factory;
    }

    public int getStartedlevelup() {
        return Startedlevelup;
    }

    public void setStartedlevelup(int startedlevelup) {
        Startedlevelup = startedlevelup;
    }

    public int getFiremakingStartedlevelup() {
        return FiremakingStartedlevelup;
    }

    public void setFiremakingStartedlevelup(int firemakingStartedlevelup) {
        FiremakingStartedlevelup = firemakingStartedlevelup;
    }

    public long getCurrent_woodcutting_xp() {
        return current_woodcutting_xp;
    }

    public void setCurrent_woodcutting_xp(long current_woodcutting_xp) {
        this.current_woodcutting_xp = current_woodcutting_xp;
    }

    public long getCurrent_firemaking_xp() {
        return current_firemaking_xp;
    }

    public void setCurrent_firemaking_xp(long current_firemaking_xp) {
        this.current_firemaking_xp = current_firemaking_xp;
    }
}
