package com.vasilyevii;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

class DIYGson {

    String toJson(Object obj) {

        StringBuilder json = new StringBuilder();
        serializeObject(obj, json, null);
        return json.toString();
    }

    private void serializeObject(Object obj, StringBuilder json, Object fieldName) {

        if (obj == null) {
            return;
        }

        if (fieldName != null) {
            json.append("\"").append(fieldName).append("\":");
        }

        if (obj.getClass().isArray()) {

            Object[] arr = (Object[]) obj;

            json.append("[");
            for (int i = 0; i < arr.length; i++) {
                serializeObject(arr[i], json, null);
                if (i < arr.length - 1) {
                    json.append(",");
                }
            }
            json.append("]");

        } else if (obj instanceof Collection) {

            json.append("[");
            Iterator iterator = ((Collection) obj).iterator();
            while (iterator.hasNext()) {
                serializeObject(iterator.next(), json, null);
                if (iterator.hasNext()) {
                    json.append(",");
                }
            }
            json.append("]");

        } else if (obj instanceof Map) {

            Map<?,?> map = (Map) obj;
            Set<? extends Map.Entry<?, ?>> entrySet = map.entrySet();
            int entrySetSize = entrySet.size();
            int entrySetCount = 0;

            json.append("{");
            for (Map.Entry<?,?> entry : entrySet) {
                entrySetCount++;
                serializeObject(entry.getValue(), json, entry.getKey());
                if (entrySetCount < entrySetSize) {
                    json.append(",");
                }
            }
            json.append("}");

        } else if (obj instanceof Byte || obj instanceof Character || obj instanceof Short || obj instanceof Integer
                || obj instanceof Long || obj instanceof Float || obj instanceof Double || obj instanceof Boolean) {

            json.append(obj);

        } else if (obj instanceof String) {

            json.append("\"").append(obj).append("\"");

        } else {

            json.append("{");
            Field[] fields = obj.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {

                Field field = fields[i];
                if (!Modifier.isStatic(field.getModifiers()) && !Modifier.isTransient(field.getModifiers())) {
                    field.setAccessible(true);
                    try {
                        Object fieldValue = field.get(obj);
                        if (fieldValue != null) {
                            serializeObject(field.get(obj), json, field.getName());
                            if (i < fields.length - 1) {
                                json.append(",");
                            }
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            json.append("}");
        }
    }
}
