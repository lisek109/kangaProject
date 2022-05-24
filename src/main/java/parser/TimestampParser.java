package parser;

import provider.TimeProvider;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class TimestampParser implements TimeProvider {

    public String parseTimestamp() {
        Instant instant = Instant.now();
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.UTC);
        // Was forced to replace : character with - character. Otherwise exception would be throwed.
        String output = offsetDateTime.toString().replace("-", "_").replace(":", "-");
        int index = output.indexOf(".");
        String time = output.substring(0, index) + "Z";
        return time;
    }


}
