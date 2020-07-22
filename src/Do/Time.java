package Do;

import woodcutter.MainClass;

public class Time{
    private MainClass mainClass;
    private Factory _factory;
    public Time(MainClass main, Factory factory) {
        mainClass = main;
        _factory = factory;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    private long startTime;
    public static String eclapsedtime(Long startTime){
        long elapsed;
        elapsed = ((System.currentTimeMillis() - startTime) / 1000);

        return String.format("%02d:%02d:%02d", elapsed / 3600, (elapsed % 3600) / 60, (elapsed % 60));
    }
    public static long eclapsedsec(Long startTime){
        long elapsed;
        elapsed = ((System.currentTimeMillis() - startTime) / 1000);
        return elapsed;
    }
}
