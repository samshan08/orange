package org.orange.dynamic.container.impl;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Message;
import org.apache.commons.lang.StringUtils;
import org.orange.dynamic.container.DynamicMessage;
import org.orange.dynamic.exceptions.FieldNotExistException;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Created by SAM on 2017/2/18.
 */
public abstract class AbstractDynamicMessage implements DynamicMessage
{
    protected Descriptors.Descriptor descriptor;

    protected Message.Builder builder;

    //sub message type values
    protected Map<String, SubMessageValue> subMessages = new HashMap<String, SubMessageValue>();

    public int size()
    {
        return builder.getAllFields().size();
    }

    public boolean isEmpty()
    {
        return builder.getAllFields().isEmpty();
    }

    public boolean containsKey(Object key)
    {
        for (Entry<Descriptors.FieldDescriptor, Object> entry : builder.getAllFields().entrySet())
        {
            if (StringUtils.equals(entry.getKey().getName(), String.valueOf(key)))
            {
                return true;
            }
        }
        return false;
    }

    public boolean containsValue(Object value)
    {
        throw new UnsupportedOperationException("DynamicMessage don't support containsValue");
    }

    protected Descriptors.FieldDescriptor preconditionCheck(String key)
    {
        Descriptors.FieldDescriptor fieldDescriptor = descriptor.findFieldByName(key);
        if (null == fieldDescriptor)
        {
            throw new FieldNotExistException(key, descriptor.getFullName());
        }
        return fieldDescriptor;
    }


    public void putAll(Map<? extends String, ?> m)
    {
        for (Map.Entry<? extends String, ?> entry : m.entrySet())
        {
            put(entry.getKey(), entry.getValue());
        }
    }



    public Set<String> keySet()
    {
        Set<String> result = new HashSet<String>();
        for (Entry<Descriptors.FieldDescriptor, Object> entry : builder.getAllFields().entrySet())
        {
            result.add(entry.getKey().getName());
        }
        return result;
    }

    public Collection<Object> values()
    {
        throw new UnsupportedOperationException("DynamicMessage don't support values");
    }

    public Set<Map.Entry<String, Object>> entrySet()
    {
        throw new UnsupportedOperationException("DynamicMessage don't support entrySet");
    }

    public DynamicMessage fill(final Object value)
    {
        if (null == value) {
            return this;
        }
        if (value instanceof Map)
        {
            Map<?, ?> mapValue = (Map<?, ?>)value;
            for (Map.Entry<?, ? > entry : mapValue.entrySet())
            {
                put(String.valueOf(entry.getKey()),entry.getValue());
            }
        }
        else
        {
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

    @Override
    public DynamicMessage fromProtoMessage(Message protoMessage)
    {
        clear();
        Map<Descriptors.FieldDescriptor, Object> allFields = protoMessage.getAllFields();
        for (Entry<Descriptors.FieldDescriptor, Object> entry : allFields.entrySet())
        {
            builder.setField(entry.getKey(), entry.getValue());
        }
        return this;
    }

    public Object remove(Object key)
    {
        String keyStr = String.valueOf(key);
        Descriptors.FieldDescriptor fieldDescriptor = preconditionCheck(keyStr);
        if (subMessages.containsKey(keyStr))
        {
            subMessages.remove(keyStr);
        }
        if (builder.hasField(fieldDescriptor))
        {
            builder.clearField(fieldDescriptor);
        }
        return null;
    }

    public void clear()
    {
        subMessages.clear();
        builder.clear();
    }

    @Override
    public Message toProtoMessage()
    {
        for (Map.Entry<String, SubMessageValue> entry : subMessages.entrySet())
        {
            SubMessageValue subMessageValue = entry.getValue();
            subMessageValue.buildProtoMessage(builder);
        }
        return builder.build();
    }

}
