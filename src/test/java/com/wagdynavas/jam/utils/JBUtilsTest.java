package com.wagdynavas.jam.utils;

import com.wagdynavas.jam.util.JBUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class JBUtilsTest {

    @Test
    void generateUUID_returnsValidUUIDString() {
        String uuid = JBUtils.generateUUID();
        assertNotNull(uuid);
        assertTrue(uuid.matches("^[0-9a-f]{8}-[0-9a-f]{4}-4[0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$"));
    }

    @Test
    void generateKey_withShortPrefixAndSuffix_returnsPaddedString() {
        String result = JBUtils.generateKey("abc", 15);
        assertEquals(15, result.length());
    }

    @Test
    void generateKey_withLongPrefix_returnsTruncatedSuffix() {
        String prefix = "longprefix12345";
        String result = JBUtils.generateKey(prefix, 15);
        assertEquals(15, result.length());
    }
}