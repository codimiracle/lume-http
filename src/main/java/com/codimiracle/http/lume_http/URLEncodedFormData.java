package com.codimiracle.http.lume_http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class URLEncodedFormData extends FormData {
    public class UnsupportEncodingException extends RuntimeException {
        public UnsupportEncodingException(String s) {
            super(s);
        }
    }

    public URLEncodedFormData() {
    }

    @Override
    public String put(String name, String value) {
        try {
            return super.put(URLEncoder.encode(name, "utf-8"), URLEncoder.encode(value, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new UnsupportEncodingException(e.getMessage());
        }
    }

    @Override
    protected Enctype getEnctype() {
        return Enctype.URLENCODED;
    }

    @Override
    protected String getFormRequestData() {
        String step = "";
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : entrySet()) {
            builder.append(step);
            builder.append(entry.getKey());
            builder.append('=');
            builder.append(entry.getValue());
            step = "&";
        }
        ;
        return builder.toString();
    }
}