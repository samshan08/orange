package org.orange.griffin.bdd.framework.rule;

import org.orange.griffin.bdd.framework.config.MetaConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 *
 * @author SAM
 * @date 2018/1/28
 */
public class ServiceEngineManager {

    private static Map<String, ServiceEngineThread> clientServiceEngines = new HashMap<>();

    private static Map<String, ServiceEngineThread> serverServiceEngines = new HashMap<>();


    private static void startServiceEngine(MetaConfig.ServerConfig config, Consumer<ServiceEngineThread> procedure) {
        String configPath = config.getConfigPath();
        ServiceEngineThread serviceEngineThread = new ServiceEngineThread("ServiceEngine-Worker", config);
        if (config.isServer()) {
            if (null != serverServiceEngines.putIfAbsent(configPath, serviceEngineThread)) {
                throw new IllegalStateException("ServiceEngine with configPath " + configPath + " already started.");
            }
        } else {
            if (null != clientServiceEngines.putIfAbsent(configPath, serviceEngineThread)) {
                throw new IllegalStateException("ServiceEngine with configPath " + configPath + " already started.");
            }
        }
        procedure.accept(serviceEngineThread);
    }

    private static void stopServiceEngine(MetaConfig.ServerConfig config, Consumer<ServiceEngineThread> procedure) {
        String configPath = config.getConfigPath();
        ServiceEngineThread serviceEngineThread = null;
        if (config.isServer()) {
            serviceEngineThread = serverServiceEngines.get(configPath);
        } else {
            serviceEngineThread = clientServiceEngines.get(configPath);
        }
        if (null == serviceEngineThread) {
            throw new IllegalStateException("ServiceEngine with configPath " + configPath + " already stopped.");
        }
        procedure.accept(serviceEngineThread);
    }

    static void asyncStartServiceEngine(MetaConfig.ServerConfig config) {
        startServiceEngine(config, (serviceEngineThread -> serviceEngineThread.start()));
    }

    static void syncStartServiceEngine(MetaConfig.ServerConfig config) {
        startServiceEngine(config, (serviceEngineThread -> serviceEngineThread.bootEngine()));
    }

    static void asyncStopServiceEngine(MetaConfig.ServerConfig config) {
        stopServiceEngine(config, (serviceEngineThread -> {
            serviceEngineThread.setPoison(true);
            try {
                serviceEngineThread.join(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
    }

    static void syncStopServiceEngine(MetaConfig.ServerConfig config) {
        stopServiceEngine(config, (serviceEngineThread -> serviceEngineThread.stopEngine()));
    }


}
