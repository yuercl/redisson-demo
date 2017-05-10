package com.yuer.redis.conf;

import org.redisson.Config;
import org.redisson.SingleServerConfig;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.StringUtils;

public class RedissonConfig implements InitializingBean {
    private Config config;

    private String hostName  ;
    private int port  ;
    private int timeout   ;
    private String password;
    private int database  ;

    public RedissonConfig() {
    }

    public RedissonConfig(Config config, String hostName, int port, int timeout, String password, int database) {
        this.config = config;
        this.hostName = hostName;
        this.port = port;
        this.timeout = timeout;
        this.password = password;
        this.database = database;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig.setAddress(hostName + ":" + port).setDatabase(database).setTimeout(timeout);
        if (!StringUtils.isEmpty(password)) {
            singleServerConfig.setPassword(password);
        }
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

}
