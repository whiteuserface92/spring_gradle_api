package com.dlsdlworld.spring.api.validator;

import org.hibernate.validator.internal.constraintvalidators.bv.number.InfinityNumberComparatorHelper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.OptionalInt;

/**

 */
final class NumberComparatorHelper {

    private NumberComparatorHelper() {
    }

    public static int compare(BigDecimal number, long value) {
        return number.compareTo( BigDecimal.valueOf( value ) );
    }

    public static int compare(BigInteger number, long value) {
        return number.compareTo( BigInteger.valueOf( value ) );
    }

    public static int compare(Long number, long value) {
        return number.compareTo( value );
    }

    public static int compare(Number number, long value, OptionalInt treatNanAs) {
        if ( number instanceof Double ) {
            return compare( (Double) number, value, treatNanAs );
        }
        if ( number instanceof Float ) {
            return compare( (Float) number, value, treatNanAs );
        }

        if ( number instanceof BigDecimal ) {
            return compare( (BigDecimal) number, value );
        }
        if ( number instanceof BigInteger ) {
            return compare( (BigInteger) number, value );
        }

        if ( number instanceof Byte || number instanceof Integer || number instanceof Long || number instanceof Short ) {
            return compare( number.longValue(), value );
        }

        return compare( number.doubleValue(), value, treatNanAs );
    }

    public static int compare(Double number, long value, OptionalInt treatNanAs) {
        OptionalInt infinity = InfinityNumberComparatorHelper.infinityCheck( number, treatNanAs );
        if ( infinity.isPresent() ) {
            return infinity.getAsInt();
        }
        return Double.compare( number, value );
    }

    public static int compare(Float number, long value, OptionalInt treatNanAs) {
        OptionalInt infinity = InfinityNumberComparatorHelper.infinityCheck( number, treatNanAs );
        if ( infinity.isPresent() ) {
            return infinity.getAsInt();
        }
        return Float.compare( number, value );
    }
}

