package com.osfocus.springcloud.eurekaconsumer;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class LoggingClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        System.out.println("Intercepted");
        System.out.println("URI = " + httpRequest.getURI());

        ClientHttpResponse response = clientHttpRequestExecution.execute(httpRequest, bytes);

        System.out.println(response.getHeaders());
        return response;
    }
}
