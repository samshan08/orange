package org.orange.utils;

import com.google.protobuf.Descriptors;

/**
 * Created by SAM on 2017/2/18.
 */
public class ProtoUtils
{
    /**
     * 判断是否基础类型
     * @param type
     * @return 是否基础类型
     */
    public static boolean isProtoBasicType(Descriptors.FieldDescriptor.Type type)
    {
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
}
