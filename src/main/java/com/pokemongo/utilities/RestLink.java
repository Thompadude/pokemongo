package com.pokemongo.utilities;

public class RestLink {

    private String uri;
    private String rel;

    public RestLink() {
    }

    public RestLink(String uri, String rel) {
        this.uri = uri;
        this.rel = rel;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

}