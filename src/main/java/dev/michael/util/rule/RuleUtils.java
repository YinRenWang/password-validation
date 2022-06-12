package dev.michael.util.rule;

public class RuleUtils {

    public static boolean isContainsRangeChar(String str, int min, int max) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= min && c <= max)
                return true;
        }
        return false;
    }
}
