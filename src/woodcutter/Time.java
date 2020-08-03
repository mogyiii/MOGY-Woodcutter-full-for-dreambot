package woodcutter;

import static java.lang.Math.toIntExact;

public class Time extends MainClass {
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
