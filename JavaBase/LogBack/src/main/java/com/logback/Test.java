package com.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Sean on 2019/3/25
 *
 * @author Sean
 */
public class Test {
    private static final Logger LOGGER = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        LOGGER.info("log back test msg ");
    }
}
