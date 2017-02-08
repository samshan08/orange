package org.orange.dynamic.container;

import java.util.List;

/**
 * Created by Sam on 2017/2/7.
 */
public interface DynamicMessageList extends List<DynamicMessage> {

    List<com.google.protobuf.DynamicMessage> toProtoMessages();

    DynamicMessageList fill(final Object value);
}
