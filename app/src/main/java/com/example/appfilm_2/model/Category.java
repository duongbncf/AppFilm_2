
package com.example.appfilm_2.model;

import java.util.HashMap;
import java.util.Map;

public class Category {

    private Integer idCategories;
    private String name;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getIdCategories() {
        return idCategories;
    }

    public void setIdCategories(Integer idCategories) {
        this.idCategories = idCategories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
