package by.av.taf.Utils;

public class Utils {

    public static <T extends Number & Comparable<? super T>> boolean isInRange(T value, T min, T max) {
        return value.compareTo(min) >= 0 && value.compareTo(max) <= 0;
    }

    public static <T extends Number & Comparable<? super T>> boolean isItMin(T value, T min) {
        return value.compareTo(min) >= 0;
    }

    public static <T extends Number & Comparable<? super T>> boolean isItMax(T value, T max) {
        return value.compareTo(max) <= 0;
    }
}
