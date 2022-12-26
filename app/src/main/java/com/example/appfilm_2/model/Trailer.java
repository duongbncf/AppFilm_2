
package com.example.appfilm_2.model;

import java.util.HashMap;
import java.util.Map;

public class Trailer {

    private String Tcover;
    private String Ttitle;
    private String Turl;
    private String Tvid;
    private Integer id;
    private String Tdepcription;

    private String Tauthor;

    public String getTauthor() {
        return Tauthor;
    }

    public void setTauthor(String tauthor) {
        Tauthor = tauthor;
    }

    public String getTdepcription() {
        return Tdepcription;
    }

    public void setTdepcription(String tdepcription) {
        Tdepcription = tdepcription;
    }

    public Trailer() {
    }

    public String getTcover() {
        return Tcover;
    }

    public void setTcover(String tcover) {
        Tcover = tcover;
    }

    public String getTtitle() {
        return Ttitle;
    }

    public void setTtitle(String ttitle) {
        Ttitle = ttitle;
    }

    public String getTurl() {
        return Turl;
    }

    public void setTurl(String turl) {
        Turl = turl;
    }

    public String getTvid() {
        return Tvid;
    }

    public void setTvid(String tvid) {
        Tvid = tvid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
