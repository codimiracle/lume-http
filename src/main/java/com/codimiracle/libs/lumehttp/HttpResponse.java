package com.codimiracle.libs.lumehttp;

import com.codimiracle.libs.lumehttp.enums.HttpHeader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class HttpResponse {
    private HttpURLConnection httpURLConnection;
    protected HttpResponse(HttpURLConnection httpURLConnection) {
        this.httpURLConnection = httpURLConnection;
    }
    public InputStream getInputStream() throws IOException {
        return httpURLConnection.getInputStream();
    }

    public String getResponseHeader(HttpHeader key) {
        return httpURLConnection.getHeaderField(key.toString());
    }

    public int getResponseCode() throws IOException {
        return httpURLConnection.getResponseCode();
    }
    public String getResponseMessage() throws IOException {
        return httpURLConnection.getResponseMessage();
    }
    public String getResponseBody() throws IOException {
        StringBuilder builder = new StringBuilder();
        char[] buffer = new char[1024];
        InputStreamReader reader = new InputStreamReader(httpURLConnection.getInputStream());
        int read = -1;
        while ((read = reader.read(buffer)) != -1)
            builder.append(buffer, 0, read);
        return builder.toString();
    }
}
