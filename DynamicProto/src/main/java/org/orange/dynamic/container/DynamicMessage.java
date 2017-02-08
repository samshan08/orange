package org.orange.dynamic.container;

import java.util.Map;

/**
 * Created by Sam on 2017/2/7.
 */
public interface DynamicMessage extends Map<String, Object> {

    com.google.protobuf.DynamicMessage toProtoMessage();

    DynamicMessage fill(final Object value);

}
