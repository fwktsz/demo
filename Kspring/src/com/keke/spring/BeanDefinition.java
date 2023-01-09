package com.keke.spring;

/**
 * @author k 2023/1/9 16:30
 */
public class BeanDefinition {

    private Class type;
    private String scope;

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
