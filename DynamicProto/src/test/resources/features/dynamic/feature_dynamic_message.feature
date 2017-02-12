#language: zh-CN
功能: 验证动态消息的获取和解析能力

  场景: 通过DynamicMessage构造复杂对象，序列化和反序列化
    假如加载desc文件vsc.pvr.api.pvrService.desc
    当使用类型dsf.vsc.pvr.api.QueryRecordPlansResp构造DynamicMessage对象，并且序列化成为二进制
    当使用生成代码序列化dsf.vsc.pvr.api.QueryRecordPlansResp对象，成为二进制
    那么比较类型dsf.vsc.pvr.api.QueryRecordPlansResp的序列化结果，两者二进制相同

