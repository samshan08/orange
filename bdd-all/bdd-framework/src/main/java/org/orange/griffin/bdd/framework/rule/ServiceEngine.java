package org.orange.griffin.bdd.framework.rule;

import org.orange.griffin.bdd.framework.config.MetaConfig;

import java.util.function.Function;

/**
 * 服务引擎接口，含有相关的服务进程信息
 * @author SAM
 * @date 2018/1/28
 */
public interface ServiceEngine {

    /**
     * 根据服务元数据配置，启动服务引擎
     * @param serverConfig
     */
    void start(MetaConfig.ServerConfig serverConfig);


    /**
     * 停止服务引擎
     */
    void stop();

    /**
     * 获取引擎中的成员，比如appContext等
     * @param key
     * @param <T>
     * @return 引擎中的成员
     */
    <T> T getParam(String key);

    /**
     * 在引擎的子系统中执行方法，比如在某个特定的application中执行，退出后清理
     * @param procedure
     * @param param
     * @param <T>
     * @param <R>
     * @return 执行结果
     */
    <T, R> R safeRunProcedure(Function<T, R> procedure, T param);

}
