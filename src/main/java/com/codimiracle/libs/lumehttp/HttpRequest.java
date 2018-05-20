package com.codimiracle.libs.lumehttp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.util.Map;

public class HttpRequest {
    private static final String HTTP_HEADER_CONTENT_TYPE = "Content-Type";
    private static final String HTTP_FORM_CONTENT_TYPE = "application/x-www-form-urlencoded";
    private HttpURLConnection httpURLConnection;
    protected HttpRequest(HttpURLConnection httpURLConnection) {
        this.httpURLConnection = httpURLConnection;
    }

    public void setRequestHeader(String key, String value) {
        httpURLConnection.setRequestProperty(key, value);
    }

    public String getRequestHeader(String key) {
        return httpURLConnection.getRequestProperty(key);
    }

    public void setRequestMethod(String method) throws ProtocolException {
        httpURLConnection.setRequestMethod(method);
    }

    public void setRequestBody(byte[] bytes) throws IOException {
        getOutputStream().write(bytes);
    }

    public OutputStream getOutputStream() throws IOException {
        httpURLConnection.setDoOutput(true);
        return httpURLConnection.getOutputStream();
    }

    public void setFormData(FormData formData) throws IOException {
        String step = "";
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : formData.entrySet()) {
            builder.append(step);
            builder.append(entry.getKey());
            builder.append('=');
            builder.append(entry.getValue());
            step = "&";
        };
        setRequestHeader(HTTP_HEADER_CONTENT_TYPE, HTTP_FORM_CONTENT_TYPE);
        setRequestBody(builder.toString().getBytes());
    }
}
