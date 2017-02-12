package org.orange.dynamic.steps;

import com.google.protobuf.Descriptors;
import cucumber.api.java.zh_cn.假如;
import cucumber.api.java.zh_cn.当;
import cucumber.api.java.zh_cn.那么;
import org.junit.Assert;
import org.orange.dynamic.DynamicDescriptorPool;
import org.orange.dynamic.container.DynamicMessage;
import org.orange.grpc.marshaller.ProtoMessageMarshaller;
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
        ProtoMessageMarshaller protoMessageMarshaller = new ProtoMessageMarshaller(DynamicDescriptorPool.getInstance().findTypeDescriptor(typeName));
        com.google.protobuf.DynamicMessage dynamicProto = protoMessageMarshaller.parse(new ByteArrayInputStream(dynamicBytes));
        System.out.println(dynamicProto);
        com.google.protobuf.DynamicMessage typicalProto = protoMessageMarshaller.parse(new ByteArrayInputStream(typicalBytes));
        System.out.println(typicalProto);
        Assert.assertTrue(Arrays.equals(dynamicBytes, typicalBytes));
    }


}
