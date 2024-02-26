package com.krxk.minispring.beans.factory.support;

import com.krxk.minispring.beans.factory.config.BeanDefinition;

// Bean 类的缓存
public interface BeanDefinitionRegistry {
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
