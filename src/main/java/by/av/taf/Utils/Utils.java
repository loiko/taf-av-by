package by.av.taf.Utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Utils {
    private static final Logger logger = LogManager.getLogger();

    public static <T extends Number & Comparable<? super T>> boolean isInRange(T value, T min, T max) {
        boolean result = value.compareTo(min) >= 0 && value.compareTo(max) <= 0;
        logger.info("Value {} is in range [{}, {}]: {}", value, min, max, result);
        return result;
    }

    public static <T extends Number & Comparable<? super T>> boolean isItMin(T value, T min) {
        boolean result = value.compareTo(min) >= 0;
        logger.info("Value {} is minimum {}: {}", value, min, result);
        return result;
    }

    public static <T extends Number & Comparable<? super T>> boolean isItMax(T value, T max) {
        boolean result = value.compareTo(max) <= 0;
        logger.info("Value {} is maximum {}: {}", value, max, result);
        return result;
    }
}
