# orange

DynamicProto：提供接口动态构建protobuf消息，以及适用于GRPC的序列化和反序列化能力

org.orange.grpc.MethodBuilder#buildDynamicMessageMethod：通过方法全名（服务名+"/"+方法名），构造给予DynamicMessage的编解码器
org.orange.grpc.MethodBuilder#buildProtoMessageMethod：通过方法全名（服务名+"/"+方法名），构造给予DynamicMessage的编解码器
org.orange.dynamic.DynamicDescriptorPool：提供动态消息池的对象能力：
   load:加载desc文件到缓存中
   unload：从缓存卸载desc文件
   createDynamicMessageContainer：构造DynamicMessage对象，该对象可提供类似Map的接口来进行值操作
   findTypeDescriptor：根据类全名获取protobuf的类型描述
   findMethodDescriptor：根据方法全名（服务名+"/"+方法名）获取protobuf的方法描述
