package parser;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class TimestampParser {

    public String parseTimestamp() {
        Instant instant = Instant.now();
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.UTC);
        String output = offsetDateTime.toString().replace("-", "_");
        int index = output.indexOf(".");
        String time = output.substring(0, index) + "Z";
        return time;
    }


}
