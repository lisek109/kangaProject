package parser;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class TimestampParserTest {

    private static final TimestampParser TIMESTAMP_PARSER = new TimestampParser();

    @Test
    void shouldReturnDateTimeInRightFormat() {
        //given
        Pattern pattern = Pattern.compile("^\\d{4}_\\d{2}_\\d{2}T\\d{2}-\\d{2}-\\d{2}Z");
        //when
        String result = TIMESTAMP_PARSER.parseTimestamp();
        Matcher matcher = pattern.matcher(result);
        //then
        assertTrue(matcher.matches());
    }

}