package org.orange.dynamic.container.proxy;

import com.google.protobuf.Message;

/**
 * Created by SAM on 2017/2/18.
 */
public interface MessageProxy
{
    Message.Builder newBuilder();

    Message getDefaultInstance();

    MessageProxy findFieldProtoProxy(String fieldName);

}
