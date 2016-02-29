package com.bandi.rest.configuration;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.FactoryBean;

public class ConfigurableAlias implements FactoryBean, BeanFactoryAware {
    protected String      name;

    protected BeanFactory factory;
    
    public void setName(String name) {

        this.name = name;
    }

    public void setBeanFactory(BeanFactory factory) throws BeansException {

        this.factory = factory;
    }

	@Override
	public Object getObject() throws Exception {

        // look up bean in factory
        if (factory.containsBean(name)) {
            return factory.getBean(name);
        }
        return null;
	}

	@Override
	public Class getObjectType() {

		 // type unknown, return null
        return null;
	}

	@Override
	public boolean isSingleton() {
		// only alias to singletons, add "singleton" attribute to support configurable
        return true;
	}

}
