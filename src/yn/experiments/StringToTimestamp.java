package yn.experiments;

import java.sql.Timestamp;

public class StringToTimestamp {
    public static void main(String[] args) {
        String text = "2011-10-02 18:48:05.123456";
        Timestamp ts = Timestamp.valueOf(text);
        System.out.println(ts.getNanos());
    }
}