package org.orange.dynamic.container.impl;

import com.google.protobuf.Descriptors;
import org.orange.dynamic.container.DynamicMessage;
import org.orange.dynamic.exceptions.FieldNotExistException;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Sam on 2017/2/7.
 */
public class DynamicMessageImpl implements DynamicMessage{


    private final Descriptors.Descriptor containingDescriptor;

    private com.google.protobuf.DynamicMessage.Builder  dynamicBuilder;

    private Map<String, Object> valueCache = new HashMap<String, Object>();

    //sub message type values
    private Map<String, SubMessageValue> subMessages = new HashMap<String, SubMessageValue>();

    public DynamicMessageImpl(Descriptors.Descriptor containingDescriptor) {
        this.containingDescriptor = containingDescriptor;
        dynamicBuilder = com.google.protobuf.DynamicMessage.newBuilder(containingDescriptor);
    }

    public int size() {
        return valueCache.size();
    }

    public boolean isEmpty() {
        return valueCache.isEmpty();
    }

    public boolean containsKey(Object key) {
        return valueCache.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return valueCache.containsValue(value);
    }

    public Object get(Object key) {
        String keyStr = String.valueOf(key);
        Descriptors.FieldDescriptor fieldDescriptor = prequisitionCheck(String.valueOf(key));
        if (isProtoBasicType(fieldDescriptor.getType())) {
            return valueCache.get(keyStr);
        }
        if (subMessages.containsKey(keyStr)) {
            SubMessageValue subMessageValue = subMessages.get(keyStr);
            return subMessageValue.getContainerValues();
        }
        //Create a new container for next Set
        SubMessageValue subMessageValue = new SubMessageValue(fieldDescriptor);
        subMessages.put(keyStr, subMessageValue.fill(null));
        return subMessageValue.getContainerValues();
    }

    public Object put(String key, Object value) {
        Descriptors.FieldDescriptor fieldDescriptor = prequisitionCheck(key);
        if (isProtoBasicType(fieldDescriptor.getType())) {
            dynamicBuilder.setField(fieldDescriptor, value);
        }
        else {
            //due with sub messageTypes
            SubMessageValue subMessageValue = new SubMessageValue(fieldDescriptor);
            subMessages.put(key, subMessageValue.fill(value));
        }
        return valueCache.put(key, value);
    }

    private Descriptors.FieldDescriptor prequisitionCheck(String key) {
        Descriptors.FieldDescriptor fieldDescriptor = containingDescriptor.findFieldByName(key);
        if (null == fieldDescriptor) {
            throw new FieldNotExistException(key, containingDescriptor.getFullName());
        }
        return fieldDescriptor;
    }

    public Object remove(Object key) {
        String keyStr = String.valueOf(key);
        Descriptors.FieldDescriptor fieldDescriptor = prequisitionCheck(keyStr);
        if (subMessages.containsKey(keyStr)) {
            subMessages.remove(keyStr);
        }
        if (dynamicBuilder.hasField(fieldDescriptor)) {
            dynamicBuilder.clearField(fieldDescriptor);
        }
        return valueCache.remove(keyStr);
    }

    public void putAll(Map<? extends String, ?> m) {
        for (Map.Entry<? extends String, ?> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public void clear() {
        subMessages.clear();
        dynamicBuilder.clear();
        valueCache.clear();
    }

    public Set<String> keySet() {
        return valueCache.keySet();
    }

    public Collection<Object> values() {
        return valueCache.values();
    }

    public Set<Map.Entry<String, Object>> entrySet() {
        return valueCache.entrySet();
    }

    private static boolean isProtoBasicType(Descriptors.FieldDescriptor.Type type) {
        Descriptors.FieldDescriptor.JavaType javaType = type.getJavaType();
        return  javaType == Descriptors.FieldDescriptor.JavaType.DOUBLE ||
                javaType == Descriptors.FieldDescriptor.JavaType.FLOAT ||
                javaType == Descriptors.FieldDescriptor.JavaType.LONG ||
                javaType == Descriptors.FieldDescriptor.JavaType.INT ||
                javaType == Descriptors.FieldDescriptor.JavaType.BOOLEAN ||
                javaType == Descriptors.FieldDescriptor.JavaType.STRING ||
                javaType == Descriptors.FieldDescriptor.JavaType.BYTE_STRING ||
                javaType == Descriptors.FieldDescriptor.JavaType.ENUM;
    }

    public DynamicMessage fill(final Object value) {
        if (null == value) {
            return this;
        }
        if (value instanceof Map) {
            Map<?, ?> mapValue = (Map<?, ?>)value;
            for (Map.Entry<?, ? > entry : mapValue.entrySet()) {
                put(String.valueOf(entry.getKey()),entry.getValue());
            }
        } else {
            ReflectionUtils.doWithFields(value.getClass(), new ReflectionUtils.FieldCallback() {
                public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                    if (!field.isAccessible()) {
                        ReflectionUtils.makeAccessible(field);
                    }
                    put(field.getName(), ReflectionUtils.getField(field, value));
                }
            }, new ReflectionUtils.FieldFilter() {
                public boolean matches(Field field) {
                    return !(Modifier.isStatic(field.getModifiers()) || Modifier.isTransient(field.getModifiers()));
                }
            });
        }
        return this;
    }

    public com.google.protobuf.DynamicMessage toProtoMessage() {
        for (Map.Entry<String, SubMessageValue> entry : subMessages.entrySet()) {
            SubMessageValue subMessageValue = entry.getValue();
            dynamicBuilder.setField(subMessageValue.getFieldDescriptor(), subMessageValue.toProtoMessage());
        }
        return dynamicBuilder.build();
    }




}
