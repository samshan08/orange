package org.orange.griffin.bdd.framework.config;

import org.orange.griffin.bdd.framework.exception.DuplicateKeyException;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 *
 * @author SAM
 * @date 2018/1/28
 */
public class MetaConfigLoader {

    private static final String METACONFIG_PATH = "META-INF/griffin/metaconfig.yaml";

    /**
     * load the metaconfig-* for all the test cases
     */
    public static Map<String, MetaConfig> loadMetaConfig() {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        List<InputStream> inputStreams = new ArrayList<>();
        Yaml yaml = new Yaml();
        Map<String, MetaConfig> result = new HashMap<>(20);
        try {
            Enumeration<URL> resources = (cl != null ? cl.getResources(METACONFIG_PATH) : ClassLoader.getSystemResources(METACONFIG_PATH));
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                URLConnection urlConnection = resource.openConnection();
                inputStreams.add(urlConnection.getInputStream());
            }
            for (InputStream stream : inputStreams) {
                List<MetaConfig> metaConfigs = yaml.load(stream);
                for (MetaConfig metaConfig : metaConfigs) {
                    if (null != result.putIfAbsent(metaConfig.getMain(), metaConfig)) {
                        throw new DuplicateKeyException(metaConfig.getMain());
                    }
                }
            }
            return result;

        } catch (Exception e) {

            throw new IllegalArgumentException("Error initializing metaConfigs", e);
        } finally {
            for (InputStream stream : inputStreams) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
