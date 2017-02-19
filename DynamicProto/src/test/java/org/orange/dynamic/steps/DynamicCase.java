package org.orange.dynamic.steps;

import com.google.protobuf.Descriptors;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import cucumber.api.java.zh_cn.假如;
import cucumber.api.java.zh_cn.当;
import cucumber.api.java.zh_cn.那么;
import io.grpc.MethodDescriptor;
import org.junit.Assert;
import org.orange.dynamic.DynamicDescriptorPool;
import org.orange.dynamic.container.DynamicMessage;
import org.orange.grpc.MethodBuilder;
import org.orange.vsc.pvr.api.generate.PvrSerivceProto;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SAM on 2017/2/12.
 */
public class DynamicCase
{
    private Map<String, byte[]> dynamicStream = new HashMap<String, byte[]>();

    private Map<String, byte[]> typicalStream = new HashMap<String, byte[]>();

    @假如("加载desc文件(.+)")
    public void loadDescriptor(String descName) throws IOException, Descriptors.DescriptorValidationException {
        String path = "desc/" + descName;
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        DynamicDescriptorPool.getInstance().load(path, stream);
    }

    @当("使用类型(.+)构造DynamicMessage对象，并且序列化成为二进制")
    public void buildDynamicMessage(String typeName)
    {
        DynamicMessage dynamicMessageContainer = DynamicDescriptorPool.getInstance().createDynamicMessageContainer(typeName);
        ((DynamicMessage)((DynamicMessage)dynamicMessageContainer.get("arg0")).get("result")).put("retCode", "1234567");
        ((DynamicMessage)((DynamicMessage)dynamicMessageContainer.get("arg0")).get("result")).put("retMsg", "you are done");
        ((DynamicMessage)((DynamicMessage)dynamicMessageContainer.get("arg0")).get("plan")).put("planId", "001");
        ((DynamicMessage)((DynamicMessage)dynamicMessageContainer.get("arg0")).get("plan")).put("profileSn", 31233l);
        ((DynamicMessage)((DynamicMessage)((DynamicMessage)dynamicMessageContainer.get("arg0")).get("plan")).get("plan")).put("planId", "02323");
        ((DynamicMessage)((DynamicMessage)((DynamicMessage)dynamicMessageContainer.get("arg0")).get("plan")).get("plan")).put("srcDeviceId", "tech-device");
        dynamicStream.put(typeName, dynamicMessageContainer.toProtoMessage().toByteArray());
    }


    @当("使用生成代码序列化(.+)对象，成为二进制")
    public void buildTypicalMessage(String typeName)
    {
        PvrSerivceProto.Result.Builder resultBuilder = PvrSerivceProto.Result.newBuilder();
        resultBuilder.setRetCode("1234567");
        resultBuilder.setRetMsg("you are done");

        PvrSerivceProto.QueryRecordPlansResponse.Builder respBuilder = PvrSerivceProto.QueryRecordPlansResponse.newBuilder();
        respBuilder.setResult(resultBuilder.build());

        PvrSerivceProto.RecordPlan.Builder planBuilder = PvrSerivceProto.RecordPlan.newBuilder();
        planBuilder.setPlanId("001");
        planBuilder.setProfileSn(31233l);
        PvrSerivceProto.RecordPlan.Builder planBuilder1 = PvrSerivceProto.RecordPlan.newBuilder();
        planBuilder1.setPlanId("02323");
        planBuilder1.setSrcDeviceId("tech-device");
        planBuilder.setPlan(planBuilder1.build());

        respBuilder.setPlan(planBuilder.build());

        PvrSerivceProto.QueryRecordPlansResp.Builder builder = PvrSerivceProto.QueryRecordPlansResp.newBuilder();
        builder.setArg0(respBuilder.build());

        typicalStream.put(typeName, builder.build().toByteArray());
    }

    @那么("比较类型(.+)的序列化结果，两者二进制相同")
    public void compareBinary(String typeName)
    {
        byte[] dynamicBytes = dynamicStream.get(typeName);
        byte[] typicalBytes = typicalStream.get(typeName);
        Assert.assertTrue(Arrays.equals(dynamicBytes, typicalBytes));
    }

    @当("通过DynamicMessage反序列化机制将二进制反序列化成为(.+)对象，并修改值")
    public void deserializeProtoToDynamic(String typeName)
    {
        byte[] typicalBytes = typicalStream.get(typeName);
        MethodDescriptor<com.google.protobuf.DynamicMessage, com.google.protobuf.DynamicMessage> methodDesc =
                MethodBuilder.buildProtoMessageMethod(DynamicDescriptorPool.generateFullName("dsf.vsc.pvr.api.pvrSerivce", "queryRecordPlans"));
        com.google.protobuf.DynamicMessage protoMsg = methodDesc.getResponseMarshaller().parse(new ByteArrayInputStream(typicalBytes));
        DynamicMessage dynamicMessage = DynamicDescriptorPool.getInstance().createDynamicMessageContainer(typeName, true);
        dynamicMessage.fromProtoMessage(protoMsg);
        ((DynamicMessage)((DynamicMessage)dynamicMessage.get("arg0")).get("result")).put("retMsg", "you are a good boy");
        dynamicStream.put(typeName, dynamicMessage.toProtoMessage().toByteArray());
    }

    @那么("比较修改后的(.+)，两者二进制相同")
    public void modifyAndCompareBinary(String typeName) throws InvalidProtocolBufferException {
        byte[] dynamicBytes = dynamicStream.get(typeName);
        byte[] typicalBytes = typicalStream.get(typeName);
        PvrSerivceProto.QueryRecordPlansResp.Builder builder = PvrSerivceProto.QueryRecordPlansResp.newBuilder();
        builder.mergeFrom(typicalBytes);
        PvrSerivceProto.Result.Builder resultBuilder = builder.getArg0().getResult().toBuilder();
        resultBuilder.setRetMsg("you are a good boy");
        builder.setArg0(builder.getArg0().toBuilder().setResult(resultBuilder.build()));
        typicalBytes = builder.build().toByteArray();
        Assert.assertTrue(Arrays.equals(dynamicBytes, typicalBytes));
    }

    @那么("卸载desc文件(.+)")
    public void unloadDescriptor(String descName) throws IOException, Descriptors.DescriptorValidationException {
        String path = "desc/" + descName;
        DynamicDescriptorPool.getInstance().unload(path);
    }

    @当("使用PB动态消息，反序列化PB对象(.+)，并修改值其中的值")
    public void loadPBMessageToDynamic(String typeName) throws InvalidProtocolBufferException {
        byte[] typicalBytes = typicalStream.get(typeName);
        PvrSerivceProto.QueryRecordPlansResp.Builder builder = PvrSerivceProto.QueryRecordPlansResp.newBuilder();
        builder.mergeFrom(typicalBytes);
        PvrSerivceProto.QueryRecordPlansResp typicalMessage = builder.build();
        DynamicMessage dynamicMessage = DynamicDescriptorPool.getInstance().createDynamicMessageContainer(typeName);
        dynamicMessage.fromProtoMessage(typicalMessage);
        ((DynamicMessage)((DynamicMessage)dynamicMessage.get("arg0")).get("result")).put("retMsg", "you are a good boy");
        Message protoMessage = dynamicMessage.toProtoMessage();
        dynamicStream.put(typeName, protoMessage.toByteArray());
        Assert.assertEquals(typicalMessage.getParserForType(), protoMessage.getParserForType());
    }

}
