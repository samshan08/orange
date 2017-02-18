package org.orange.dynamic.container;

import com.google.protobuf.Message;

import java.util.Map;

/**
 * Created by Sam on 2017/2/7.
 */
public interface DynamicMessage extends Map<String, Object>
{
    /**
     * Convert from Protobuf DynamicMessage
     * @param protoMessage
     * @return this
     */
    DynamicMessage fromProtoMessage(Message protoMessage);

    /**
     * Convert this to Proto DynamicMessage
     * @return Proto DynamicMessage
     */
    Message toProtoMessage();


}
