package org.orange.griffin.bdd.framework.rule;

import lombok.Getter;
import lombok.Setter;
import org.orange.griffin.bdd.framework.config.MetaConfig;

import java.util.ServiceLoader;

/**
 *
 * @author SAM
 * @date 2018/1/28
 */
public class ServiceEngineThread extends Thread {

    @Setter
    private volatile boolean poison = false;

    @Getter
    private ServiceEngine serviceEngine;

    private final MetaConfig.ServerConfig serverConfig;

    public ServiceEngineThread(String name, MetaConfig.ServerConfig serverConfig) {
        super(name);
        this.serverConfig = serverConfig;
        ServiceLoader<ServiceEngine> serviceLoader = ServiceLoader.load(ServiceEngine.class);
        if ( !serviceLoader.iterator().hasNext()) {
            throw new IllegalArgumentException("Missing implementation for ServiceEngine");
        }
        for (ServiceEngine engine : serviceLoader) {
            serviceEngine = engine;
            break;
        }
    }

    @Override
    public void run() {
        bootEngine();
        while (!poison) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        stopEngine();
    }


    public void bootEngine() {
        serviceEngine.start(serverConfig);
    }

    public void stopEngine() {
        serviceEngine.stop();
    }
}
