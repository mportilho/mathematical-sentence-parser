package io.github.mportilho.mathsentenceparser.syntaxtree;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public final class MathSentenceUtils {

    public static Object tryConventingKnownTypes(Object value, MathContext context) {
        if (value instanceof BigDecimal) {
            return value;
        } else if (value instanceof Number) {
            return new BigDecimal(value.toString(), context);
        } else if (value instanceof Date) {
            return LocalDateTime.ofInstant(((Date) value).toInstant(), ZoneId.systemDefault());
        }
        return value;
    }

}
