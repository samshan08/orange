package org.orange.griffin.bdd.framework.rule;

import lombok.RequiredArgsConstructor;
import org.junit.rules.ExternalResource;
import org.orange.griffin.bdd.framework.config.MetaConfig;
import org.orange.griffin.bdd.framework.config.MetaConfigLoader;

import java.util.Map;

/**
 *
 * @author SAM
 * @date 2018/1/28
 */
@RequiredArgsConstructor
public class StartupRule extends ExternalResource {

    private static Map<String, MetaConfig> metaConfigs = MetaConfigLoader.loadMetaConfig();

    private final Class<?> mainClazz;

    private MetaConfig currentMetaConfig = null;

    @Override
    protected void before() throws Throwable {
        currentMetaConfig = metaConfigs.get(mainClazz.getCanonicalName());
        if (null == currentMetaConfig) {
            throw new IllegalAccessException("MetaConfig for " + mainClazz.getCanonicalName()
              + " is not found.");
        }

    }

    @Override
    protected void after() {

    }
}
