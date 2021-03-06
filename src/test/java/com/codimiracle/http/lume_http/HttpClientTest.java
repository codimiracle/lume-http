package com.codimiracle.http.lume_http;

import com.codimiracle.http.lume_http.http.FormData;

import org.junit.Before;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpClientTest {
    private HttpClient httpClient;
    @Before
    public void setUp() {
        httpClient = new HttpClient();
    }

    public void TestConnection() {
        try {
            URL url = new URL("http://localhost/castore-server/?q=/User/SignIn");
            FormData formData = new FormData();
            formData.put("username", "codimiracle");
            formData.put("password", "ce9ec288e5");
            formData.put("user_sign_in", "user_sign_in");
            httpClient.open(url);
            HttpRequest request = httpClient.getHttpRequest();
            request.setRequestHeader("User-Agent", "CAstore/1.0");
            request.setFormData(formData);
            HttpResponse response = httpClient.getHttpResponse();
            System.out.println(response.getResponseBody());
            url = new URL("http://localhost/castore-server/?q=/User/");
            httpClient.open(url);
            request = httpClient.getHttpRequest();
            request.setRequestHeader("User-Agent", "CAstore/1.0");
            response = httpClient.getHttpResponse();
            System.out.println(response.getResponseBody());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
