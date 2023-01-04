package org.miglecz.sample.p92;

import static java.lang.Integer.parseInt;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private final int floor = 0;
    private final int ceil = 100_000;

    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {
        final Scanner scanner = new Scanner(System.in);
        for (; ; ) {
            System.out.printf("Please enter a decimal number in the range of %d-%d: ", floor, ceil);
            System.out.printf("The received number in text form is: %s\n", translate(parseInt(scanner.next())));
        }
    }

    private final Map<Integer, String> digitMap = Map.ofEntries(
        entry(0, "zero")
        , entry(1, "one")
        , entry(2, "two")
        , entry(3, "three")
        , entry(4, "four")
        , entry(5, "five")
        , entry(6, "six")
        , entry(7, "seven")
        , entry(8, "eight")
        , entry(9, "nine")
        , entry(10, "ten")
        , entry(11, "eleven")
        , entry(12, "twelve")
        , entry(13, "thirteen")
        , entry(14, "fourteen")
        , entry(15, "fifteen")
        , entry(16, "sixteen")
        , entry(17, "seventeen")
        , entry(18, "eighteen")
        , entry(19, "nineteen")
        , entry(20, "twenty")
        , entry(30, "thirty")
        , entry(40, "forty")
        , entry(50, "fifty")
        , entry(60, "sixty")
        , entry(70, "seventy")
        , entry(80, "eighty")
        , entry(90, "ninety")
        , entry(100, "hundred")
        , entry(1000, "thousand")
    );

    private String translate(final int input) {
        if (input < floor || input > ceil) {
            throw new IllegalArgumentException();
        }
        final StringBuilder result = new StringBuilder();
        if (input >= 1000) {
            result.append(translate(input / 1000))
                .append(digitMap.get(1000));
            final int mod1000 = input % 1000;
            if (mod1000 > 0) {
                result.append(translate(mod1000));
            }
        } else if (input >= 100) {
            result.append(translate(input / 100))
                .append(digitMap.get(100));
            final int mod100 = input % 100;
            if (mod100 > 0) {
                result.append(translate(mod100));
            }
        } else if (input >= 20) {
            result.append(digitMap.get(clearDigitsOfFirstDecimalPlace(input)));
            final int mod10 = input % 10;
            if (mod10 > 0) {
                result.append(translate(mod10));
            }
        } else {
            result.append(digitMap.get(input));
        }
        return result.toString();
    }

    private int clearDigitsOfFirstDecimalPlace(final int input) {
        return (input / 10) * 10;
    }

    private static <K, V> Map.Entry<K, V> entry(final K k, final V v) {
        return new AbstractMap.SimpleEntry<>(k, v);
    }
}
