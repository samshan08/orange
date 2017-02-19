#language: zh-CN
功能: 验证动态消息的获取和解析能力

  场景: 通过DynamicMessage构造复杂对象，序列化和反序列化
    假如加载desc文件vsc.pvr.api.pvrService.desc
    当使用类型dsf.vsc.pvr.api.QueryRecordPlansResp构造DynamicMessage对象，并且序列化成为二进制
    当使用生成代码序列化dsf.vsc.pvr.api.QueryRecordPlansResp对象，成为二进制
    那么比较类型dsf.vsc.pvr.api.QueryRecordPlansResp的序列化结果，两者二进制相同
    那么卸载desc文件vsc.pvr.api.pvrService.desc

  场景: 通过生成代码方式构造复杂对象，判断其是否能够反向生成DynamicMessage
     假如加载desc文件vsc.pvr.api.pvrService.desc
     当使用生成代码序列化dsf.vsc.pvr.api.QueryRecordPlansResp对象，成为二进制
     当通过DynamicMessage反序列化机制将二进制反序列化成为dsf.vsc.pvr.api.QueryRecordPlansResp对象，并修改值
     那么比较修改后的dsf.vsc.pvr.api.QueryRecordPlansResp，两者二进制相同
     那么卸载desc文件vsc.pvr.api.pvrService.desc

  场景: 通过生成代码方式构造PB复杂对象，判断其是否能够反向生成DynamicMessage
    假如加载desc文件vsc.pvr.api.pvrService.desc
    当使用生成代码序列化dsf.vsc.pvr.api.QueryRecordPlansResp对象，成为二进制
    当使用PB动态消息，反序列化PB对象dsf.vsc.pvr.api.QueryRecordPlansResp，并修改值其中的值
    那么比较修改后的dsf.vsc.pvr.api.QueryRecordPlansResp，两者二进制相同
    那么卸载desc文件vsc.pvr.api.pvrService.desc