//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.puke.template.util;

public class Util {
    private Util() {
    }

    public static void checkNotNull(Object object) {
        if (object == null) {
            throw new NullPointerException();
        }
    }

    public static void checkNotNull(Object obj, String message) {
        if (obj == null) {
            throw new NullPointerException(message);
        }
    }

    public static String getResourcePath(String relativePath) {
        return Class.class.getResource(relativePath).getPath();
    }

    public static boolean isEmpty(String text) {
        return text == null || text.length() == 0;
    }
}
