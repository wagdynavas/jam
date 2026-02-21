package com.wagdynavas.jam.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static java.lang.System.currentTimeMillis;
import static org.apache.commons.lang3.StringUtils.leftPad;
import static org.apache.commons.lang3.StringUtils.substring;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JBUtils {

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public static String generateKey(String prefix, int length)
    {
        String suffix = Long.toString(currentTimeMillis());
        int prefixLength = prefix.length();
        int suffixLength = suffix.length();

        if ((prefixLength + suffixLength) < length)
        {
            return leftPad(prefix + suffix, length, "0");
        } else if (prefixLength >= length)
        {
            return substring(prefix, -length);
        } else
        {
            return prefix + substring(suffix, prefixLength - length);
        }
    }
}
