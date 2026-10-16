package com.mangzai.shapeshiftercompass.tools;

/** SSC's two live APIs. Do not use the unfinished new_form_system in SSC 1.9.2. */
final class SscReflection {
    @FunctionalInterface
    interface Classes {
        Class<?> find(String name) throws ClassNotFoundException;
    }

    private SscReflection() {}

    static Object currentForm(Object player, Class<?> playerClass, Classes classes)
            throws ReflectiveOperationException {
        try {
            Class<?> utils = classes.find("net.onixary.shapeShifterCurseFabric.player_form.utils.FormUtils");
            return utils.getMethod("getPlayerForm", playerClass).invoke(null, player);
        } catch (ClassNotFoundException | NoSuchMethodException unavailable) {
            Class<?> registry = classes.find("net.onixary.shapeShifterCurseFabric.player_form.ability.RegPlayerFormComponent");
            Object key = registry.getField("PLAYER_FORM").get(null);
            Object component = component(key, player, classes);
            if (component == null) return null;
            Class<?> type = classes.find("net.onixary.shapeShifterCurseFabric.player_form.ability.PlayerFormComponent");
            return type.getMethod("getCurrentForm").invoke(component);
        }
    }

    static Object component(Object key, Object entity, Classes classes) throws ReflectiveOperationException {
        // Component keys have generated implementation classes; invoke the public API instead.
        Class<?> componentKey;
        try { componentKey = classes.find("org.ladysnake.cca.api.v3.component.ComponentKey"); }
        catch (ClassNotFoundException oldCca) {
            componentKey = classes.find("dev.onyxstudios.cca.api.v3.component.ComponentKey");
        }
        return componentKey.getMethod("get", Object.class).invoke(key, entity);
    }

    static String formId(Object form) throws ReflectiveOperationException {
        if (form == null) return null;
        Object id;
        try {
            id = form.getClass().getMethod("getFormID").invoke(form);
        } catch (NoSuchMethodException legacy) {
            id = form.getClass().getField("FormID").get(form);
        }
        return id == null ? null : id.toString();
    }
}
