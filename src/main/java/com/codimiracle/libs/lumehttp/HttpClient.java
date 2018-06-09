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
public class HttpClient {
    private static final int DEFAULT_TIMEOUT = 500;
    private static final String USER_AGENT_HEADER = "User-Agent";
    private String cookie;
    private URL url;
    private String userAgent;
    private CookieManager cookieManager;
    private HttpURLConnection httpURLConnection;

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
            httpURLConnection.setRequestProperty(USER_AGENT_HEADER, userAgent);
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

    public void close() {
        httpURLConnection.disconnect();
    }
}
