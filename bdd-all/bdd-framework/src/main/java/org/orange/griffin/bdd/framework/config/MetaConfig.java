package org.orange.griffin.bdd.framework.config;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SAM
 * @date 2018/1/28
 */
@Data
public class MetaConfig {

    private String main;

    private List<ServerConfig> server = new ArrayList<ServerConfig>();

    private List<ClientConfig> client = new ArrayList<ClientConfig>();

    /**
     *
     */
    @Data
    public static class ServerConfig {

        private String configPath;

        private List<String> springConfigPath = new ArrayList<String>();

        public boolean isServer() {
            return true;
        }
    }

    /**
     *
     */
    @Data
    public static class ClientConfig extends ServerConfig {

        private List<String> serviceToWait = new ArrayList<String>();

        @Override
        public boolean isServer() {
            return false;
        }
    }

}
