package com.krxk.minispring.beans.factory.support;

import com.krxk.minispring.beans.BeansException;
import com.krxk.minispring.beans.factory.BeanFactory;
import com.krxk.minispring.beans.factory.config.BeanDefinition;
import com.krxk.minispring.beans.factory.config.BeanPostProcessor;
import com.krxk.minispring.beans.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.List;

// 实现 Bean 工厂接口
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {
    private final List<BeanPostProcessor>beanPostProcessors = new ArrayList<BeanPostProcessor>();
    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    protected <T> T doGetBean(final String name, final Object[] args) {
        Object bean = getSingleton(name);
        if (bean != null) {
            return (T) bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T) createBean(name, beanDefinition, args);
    }

    /**
     * 返回将要被应用的 BeanPostProcessors 列表
     * @return
     */
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor){
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    protected abstract BeanDefinition getBeanDefinition(String beansName) throws BeansException;
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args)
            throws BeansException;
}
