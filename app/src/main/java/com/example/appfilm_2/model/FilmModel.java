
package com.example.appfilm_2.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilmModel {

    private Integer idFilm;
    private Integer id_author = null;
    private Integer id_categories = null;
    private String name;
    private String avatar;
    private String cover_image;

    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
    }

    private String depcription;
    private String url;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(Integer idFilm) {
        this.idFilm = idFilm;
    }

    public Integer getIdAuthor() {
        return id_author;
    }

    public void setIdAuthor(Integer idAuthor) {
        this.id_author = idAuthor;
    }

    public Integer getIdCategories() {
        return id_categories;
    }

    public void setIdCategories(Integer idCategories) {
        this.id_categories = idCategories;
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

    public String getDepcription() {
        return depcription;
    }

    public void setDepcription(String depcription) {
        this.depcription = depcription;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }


}
