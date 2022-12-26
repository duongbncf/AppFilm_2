
package com.example.appfilm_2.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserModel {

    private List<Trailer> trailer = null;
    private List<User> user = null;
    private List<FilmModel> filmModel = null;
    private List<Author> author = null;
    private List<Category> categories = null;
    private List<Favorite> favorite = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<Trailer> getTrailer() {
        return trailer;
    }

    public void setTrailer(List<Trailer> trailer) {
        this.trailer = trailer;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public List<FilmModel> getFilm() {
        return filmModel;
    }

    public void setFilm(List<FilmModel> filmModel) {
        this.filmModel = filmModel;
    }

    public List<Author> getAuthor() {
        return author;
    }

    public void setAuthor(List<Author> author) {
        this.author = author;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Favorite> getFavorite() {
        return favorite;
    }

    public void setFavorite(List<Favorite> favorite) {
        this.favorite = favorite;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
