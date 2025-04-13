package com.chainxi.system;

import org.junit.jupiter.api.Test;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PathMatcherTest {

    @Test
    public void testPath() throws URISyntaxException {
        PathMatcher pathMatcher = new AntPathMatcher();
        assertTrue(pathMatcher.match("/**", "/menu:/sys"));
        assertTrue(pathMatcher.match("/**", new URI("/docs/commit.html?12").getPath()));
        assertEquals("cvs/commit",
                pathMatcher.extractPathWithinPattern("/docs/*", "/docs/cvs/commit"));
        assertEquals("docs/cvs/commit",
                pathMatcher.extractPathWithinPattern("/d?cs/*", "/docs/cvs/commit"));
    }

    @Test
    public void testByteArray() {
        byte[] bytes = {0, 1};
        System.out.println(bytes);
    }
    @Test
    public void testBitwise() {
        for (int i = 1; i < ((1 << 8) - 1); i++) {
            System.out.println(i);
        }
    }
}