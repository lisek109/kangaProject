package parser;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class TimestampParser {

    public static void main(String[] args) {
        parseTimestamp();
    }

    public static void parseTimestamp() {
        Instant instant = Instant.now();
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.UTC);
        String output = offsetDateTime.toString().replace("-", "_");
        int index = output.indexOf(".");
        String time = output.substring(0, index) + "Z";


        System.out.println(time);
    }


}
