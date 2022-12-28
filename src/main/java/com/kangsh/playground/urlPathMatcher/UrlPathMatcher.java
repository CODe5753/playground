package com.kangsh.playground.urlPathMatcher;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/*
 * [용도]
 * asterisk(*) 를 사용하는 경로의 whitelist를 관리할 때 쓰임
 * Filter와 같은 곳에서는 URL Matcher 기능이 따로 없기에 메서드를 직접 구현해야함
 *
 * [출처]
 * org.casbin.jcasbin.util.BuildInFunctions.class
 * https://github.com/casbin/jcasbin
 * */
public class UrlPathMatcher {

    private static final Pattern KEY_MATCH_PATTERN = Pattern.compile("\\{[^/]+\\}");

    public static boolean equals(String[] whitelist, String path) {
        return Arrays.stream(whitelist)
            .anyMatch(str -> keyMatch(path, str));
    }

    public static boolean keyMatch(String key1, String key2) {
        key2 = key2.replace("*", "[^/]*");
        key2 = "^" + KEY_MATCH_PATTERN.matcher(key2).replaceAll("[^/]+") + "$";

        try {
            return regexMatch(key1, key2);
        } catch (PatternSyntaxException var3) {
            return false;
        }
    }

    public static boolean regexMatch(String key1, String key2) {
        return Pattern.compile(key2).matcher(key1).lookingAt();
    }
}
