package com.codimiracle.http.lume_http.enums;

public enum HttpHeader {
    CONTENT_TYPE("Content-Type"),
    USER_AGENT("User-Agent");

    private String headerName;

    HttpHeader(String name) {
        this.headerName = name;
    }

    @Override
    public String toString() {
        return headerName;
    }
}