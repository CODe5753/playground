package com.kangsh.playground.urlPathMatcher;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class UrlPathMatcherTest {

    @Test
    public void test() {
        String[] whitelist = {
            "/api/*/auth",
            "/api/v2/*",
            "/api/v3/test*"
        };

        assertTrue(UrlPathMatcher.equals(whitelist, "/api/test/auth"));
        assertTrue(UrlPathMatcher.equals(whitelist, "/api/v2/a"));
        assertTrue(UrlPathMatcher.equals(whitelist, "/api/v3/testvv"));

        assertFalse(UrlPathMatcher.equals(whitelist, "/a/b/c"));
        assertFalse(UrlPathMatcher.equals(whitelist, "/api/test/auth2"));
        assertFalse(UrlPathMatcher.equals(whitelist, "/api/v3/a"));
        assertFalse(UrlPathMatcher.equals(whitelist, "/api2/a/b"));
        assertFalse(UrlPathMatcher.equals(whitelist, "/api/v3/testvv/abc"));
    }
}