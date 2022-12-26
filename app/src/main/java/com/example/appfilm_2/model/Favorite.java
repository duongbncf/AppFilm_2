
package com.example.appfilm_2.model;

import java.util.HashMap;
import java.util.Map;

public class Favorite {

    private Integer idFavorite;
    private Integer idUser;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getIdFavorite() {
        return idFavorite;
    }

    public void setIdFavorite(Integer idFavorite) {
        this.idFavorite = idFavorite;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
