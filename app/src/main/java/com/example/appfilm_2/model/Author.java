
package com.example.appfilm_2.model;

import java.util.HashMap;
import java.util.Map;

public class Author {

    private Integer idAuthor;
    private String name;
    private String avatar;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

//    public Author(Integer idAuthor, String name, String avatar) {
//        this.idAuthor = idAuthor;
//        this.name = name;
//        this.avatar = avatar;
//    }

    public Integer getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(Integer idAuthor) {
        this.idAuthor = idAuthor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
