package com.codimiracle.http.lume_http;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class FormData {
    public enum Enctype {
        URLENCODED("application/x-www-form-urlencoded"),
        MULTIPART("multipart/form-data"),
        TEXT("text/plain");
        private String enctype;

        Enctype(String enctype) {
            this.enctype = enctype;
        }

        @Override
        public String toString() {
            return enctype;
        }
    }

    private Map<String, String> characterFormData;

    public FormData() {
        characterFormData = new HashMap<>();
    }

    public String put(String name, String value) {
        return characterFormData.put(name, value);
    }

    public String get(String name) {
        return characterFormData.get(name);
    }

    protected Set<Map.Entry<String, String>> entrySet() {
        return characterFormData.entrySet();
    }

    protected abstract Enctype getEnctype();

    protected abstract String getFormRequestData();
}
