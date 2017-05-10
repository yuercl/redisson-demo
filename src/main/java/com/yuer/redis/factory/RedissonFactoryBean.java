package com.yuer.redis.factory;

import com.yuer.redis.conf.RedissonConfig;
import org.redisson.Redisson;
import org.redisson.RedissonClient;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RedissonFactoryBean implements DisposableBean, FactoryBean<RedissonTemplate> {

    @Autowired
    RedissonConfig config;

    private RedissonClient redisson;

    @Override
    public RedissonTemplate getObject() throws Exception {
        redisson = Redisson.create(config.getConfig());
        RedissonTemplate template = new RedissonTemplate();
        template.setRedisson(redisson);
        return template;
    }

    @Override
    public Class<?> getObjectType() {
        return RedissonTemplate.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void destroy() throws Exception {
        if (redisson != null) {
            synchronized (this) {
                if (redisson != null) {
                    redisson.shutdown();
                }
            }
        }
    }

    public RedissonConfig getConfig() {
        return config;
    }

    public void setConfig(RedissonConfig config) {
        this.config = config;
    }

}
