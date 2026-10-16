package com.mangzai.shapeshiftercompass.tools;

import java.lang.reflect.*;

/** Optional SSCA API access. Call read-only getters on copied item stacks. */
public final class AddonAccess {
    private static final String BASE = "net.jackcooper.shapeShifterCurseAddon.";
    private AddonAccess() {}
    public static Class<?> type(String name) throws ClassNotFoundException { return Class.forName(BASE + name); }
    public static Object call(String name, String method, Class<?>[] params, Object... args) throws ReflectiveOperationException {
        return type(name).getMethod(method, params).invoke(null, args);
    }
    public static Object get(Object object, String method) throws ReflectiveOperationException {
        return object.getClass().getMethod(method).invoke(object);
    }
    public static Object field(Object object, String field) throws ReflectiveOperationException {
        return object.getClass().getField(field).get(object);
    }
}
