package com.codimiracle.libs.lumehttp;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Base on {@code HttpUrlConnection }
 */
public class HttpClient implements AutoCloseable {

    private String cookie;
    private URL url;
    private CookieManager cookieManager;
    private HttpURLConnection httpURLConnection;

    public HttpClient() {
        cookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);
    }

    public void open(URL url) throws IOException {
        if (httpURLConnection != null) {
            close();
            httpURLConnection = null;
        }
        this.url = url;
        httpURLConnection = (HttpURLConnection) url.openConnection();
    }

    public HttpRequest getHttpRequest() throws IOException {
        try {
            cookieManager.get(url.toURI(), httpURLConnection.getRequestProperties());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return new HttpRequest(httpURLConnection);
    }

    public HttpResponse getHttpResponse() throws IOException {
        httpURLConnection.connect();
        try {
            cookieManager.put(url.toURI(), httpURLConnection.getHeaderFields());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return new HttpResponse(httpURLConnection);
    }


    @Override
    public void close() {
        httpURLConnection.disconnect();
    }
}
