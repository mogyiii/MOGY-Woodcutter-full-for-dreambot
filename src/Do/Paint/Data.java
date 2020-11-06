package Do.Paint;

import static java.lang.Math.toIntExact;

public class Data {
    private int logcuts = 0;
    private int burned_logs = 0;
    private String dot = "...";
    private int i = 0;
    private long second2;
    private  boolean starting = true;
    private long startTime = 0;
    private int secondaryWidth = 285;
    private int secondaryHeight = 20;
    private int MainWidth = 20;
    private int MainHeight = 50;
    public String dot(){
        long seconds = System.currentTimeMillis()/ 1000l;
        if(toIntExact(second2) <  toIntExact(seconds)){
            for(int j = 0; j < i; j++){
                dot +=".";
            }
            if(dot.length() >= 5){
                dot ="";
                i = 0;
            }
            i++;
        }
        second2 = System.currentTimeMillis()/ 1000l;
        return dot;
    }

    public int getLogcuts() {
        return logcuts;
    }

    public void setLogcuts(int logcuts) {
        this.logcuts = logcuts;
    }

    public int getBurned_logs() {
        return burned_logs;
    }

    public void setBurned_logs(int burned_logs) {
        this.burned_logs = burned_logs;
    }

    public String getDot() {
        return dot;
    }

    public void setDot(String dot) {
        this.dot = dot;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public long getSecond2() {
        return second2;
    }

    public void setSecond2(long second2) {
        this.second2 = second2;
    }

    public boolean isStarting() {
        return starting;
    }

    public void setStarting(boolean starting) {
        this.starting = starting;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public int getSecondaryWidth() {
        return secondaryWidth;
    }

    public int getSecondaryHeight() {
        return secondaryHeight;
    }

    public int getMainWidth() {
        return MainWidth;
    }

    public int getMainHeight() {
        return MainHeight;
    }
}
