# orange

<p><b>DynamicProto：</b>提供接口动态构建protobuf消息，以及适用于GRPC的序列化和反序列化能力</p>

<p>org.orange.grpc.MethodBuilder#buildDynamicMessageMethod：通过方法全名（服务名+"/"+方法名），构造给予DynamicMessage的编解码器</p>
<p>org.orange.grpc.MethodBuilder#buildProtoMessageMethod：通过方法全名（服务名+"/"+方法名），构造给予DynamicMessage的编解码器</p>
<p>org.orange.dynamic.DynamicDescriptorPool：提供动态消息池的对象能力：</p>
<p>&nbsp;&nbsp;load:加载desc文件到缓存中</p>
<p>&nbsp;&nbsp;unload：从缓存卸载desc文件</p>
<p>&nbsp;&nbsp;createDynamicMessageContainer：构造DynamicMessage对象，该对象可提供类似Map的接口来进行值操作</p>
<p>&nbsp;&nbsp;findTypeDescriptor：根据类全名获取protobuf的类型描述</p>
<p>&nbsp;&nbsp;findMethodDescriptor：根据方法全名（服务名+"/"+方法名）获取protobuf的方法描述</p>
