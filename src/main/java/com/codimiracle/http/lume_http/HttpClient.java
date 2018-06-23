package com.codimiracle.http.lume_http;

import com.codimiracle.http.lume_http.enums.HttpHeader;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Base on {@code HttpUrlConnection }
 */
public class HttpClient {
    private static final int DEFAULT_TIMEOUT = 500;
    private URL url;
    private String userAgent;
    private CookieManager cookieManager;
    private HttpURLConnection httpURLConnection;

    private HttpRequest request;
    private HttpResponse response;

    public HttpClient() {
    }

    public void setCookieManager(CookieManager cookieManager) {
        this.cookieManager = cookieManager;
        CookieHandler.setDefault(cookieManager);
    }

    public CookieManager getCookieManager() {
        return cookieManager;
    }

    public void open(URL url) throws IOException {
        if (httpURLConnection != null) {
            close();
            httpURLConnection = null;
        }
        this.url = url;
        httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setConnectTimeout(DEFAULT_TIMEOUT);
        if (userAgent != null)
            httpURLConnection.setRequestProperty(HttpHeader.USER_AGENT.toString(), userAgent);
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setConnectTimeout(int milliseconds) {
        httpURLConnection.setConnectTimeout(milliseconds);
    }

    public int getConnectTimeout() {
        return httpURLConnection.getConnectTimeout();
    }

    public HttpRequest getHttpRequest() throws IOException {
        try {
            cookieManager.get(url.toURI(), httpURLConnection.getRequestProperties());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if (request == null) {
            request = new HttpRequest(httpURLConnection);
        }
        return request;
    }

    public HttpResponse getHttpResponse() throws IOException {
        httpURLConnection.connect();
        try {
            cookieManager.put(url.toURI(), httpURLConnection.getHeaderFields());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        if (response == null) {
            response = new HttpResponse(httpURLConnection);
        }
        return response;
    }

    public void close() {
        httpURLConnection.disconnect();
    }
}
