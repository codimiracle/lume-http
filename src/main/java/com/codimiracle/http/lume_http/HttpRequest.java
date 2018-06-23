package com.codimiracle.http.lume_http;

import com.codimiracle.http.lume_http.enums.HttpHeader;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;

public class HttpRequest {
    public enum RequestMethod {
        POST("POST"),
        GET("GET");

        private String method;

        RequestMethod(String method) {
            this.method = method;
        }

        @Override
        public String toString() {
            return method;
        }
    }

    private HttpURLConnection httpURLConnection;

    protected HttpRequest(HttpURLConnection httpURLConnection) {
        this.httpURLConnection = httpURLConnection;
    }

    public void setRequestHeader(HttpHeader key, String value) {
        httpURLConnection.setRequestProperty(key.toString(), value);
    }

    public String getRequestHeader(HttpHeader key) {
        return httpURLConnection.getRequestProperty(key.toString());
    }

    public void setRequestMethod(RequestMethod method) throws ProtocolException {
        httpURLConnection.setRequestMethod(method.toString());
    }

    public void setRequestBody(byte[] bytes) throws IOException {
        getOutputStream().write(bytes);
    }

    public OutputStream getOutputStream() throws IOException {
        httpURLConnection.setDoOutput(true);
        return httpURLConnection.getOutputStream();
    }

    public void setFormData(FormData formData) throws IOException {
        setRequestHeader(HttpHeader.CONTENT_TYPE, formData.getEnctype().toString());
        setRequestBody(formData.getFormRequestData().getBytes());
    }
}
