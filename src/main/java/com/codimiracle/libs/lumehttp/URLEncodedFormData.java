package com.codimiracle.libs.lumehttp;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class URLEncodedFormData extends FormData {

    public URLEncodedFormData() {
    }

    @Override
    public String put(String name, String value) {
        try {
            return super.put(name, URLEncoder.encode(value, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}