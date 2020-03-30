package com.vasilyevii;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

class DIYGson {

    String toJson(Object obj) {

        if (obj == null) {
            return "null";
        }

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

            int arrLength = Array.getLength(obj);

            json.append("[");
            for (int i = 0; i < arrLength; i++) {
                serializeObject(Array.get(obj, i), json, null);
                if (i < arrLength - 1) {
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

        } else if (obj instanceof Byte || obj instanceof Short || obj instanceof Integer || obj instanceof Long
                || obj instanceof Float || obj instanceof Double || obj instanceof Boolean) {

            json.append(obj);

        } else if (obj instanceof String || obj instanceof Character) {

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

            int lastChar = json.length() - 1;
            if (json.toString().charAt(lastChar) == ',') {
                json.deleteCharAt(lastChar);
            }

            json.append("}");
        }
    }
}
