package com.drools.constants;

import lombok.Getter;

/**
 * This enum contains the different session names to read the rules from different drools folders
 * @author Tapas rout
 *
 */
@Getter
public enum DroolsConstants {

    SAMPLE("sampleSession");

    private String session;

    private DroolsConstants(String session) {
        this.session=session;
    }
}
