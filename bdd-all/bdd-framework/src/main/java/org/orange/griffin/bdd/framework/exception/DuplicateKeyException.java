package org.orange.griffin.bdd.framework.exception;

import lombok.RequiredArgsConstructor;

/**
 *
 * @author SAM
 * @date 2018/1/28
 */
@RequiredArgsConstructor
public class DuplicateKeyException extends Exception {

    private final String key;

    @Override
    public String getMessage() {
        return "duplicate key " + key + " found for metaConfigs";
    }
}
