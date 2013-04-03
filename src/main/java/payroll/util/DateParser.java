package payroll.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {

    public static Date parseDateFromText(String text) throws ParseException {
        return new SimpleDateFormat("dd-mm-yyyy").parse(text);
    }
}
