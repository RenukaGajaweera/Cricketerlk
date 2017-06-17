package com.cricketerlk.common;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.util.HashMap;


/**
 * Created by Supun on 5/4/2017.
 */

@Configuration
@PropertySource("classpath:application.properties")
public class Configurations {

    @Autowired
    private Environment env;

    private static Logger logger = Logger.getLogger(Configurations.class);
    private static String IMAGE_UPLOAD_PATH;
    private static HashMap<String, String> CONF_MAP = new HashMap<String, String>();

    public Configurations() {}

    @PostConstruct
    public void init() {
        CONF_MAP.put(Constants.IMAGE_UPLOAD_PATH, env.getProperty(Constants.IMAGE_UPLOAD_PATH));
        logger.info(Constants.IMAGE_UPLOAD_PATH + ": " + CONF_MAP.get(Constants.IMAGE_UPLOAD_PATH));
    }

    public static String getConfigurations(String key) {
        return CONF_MAP.get(key);
    }
}
