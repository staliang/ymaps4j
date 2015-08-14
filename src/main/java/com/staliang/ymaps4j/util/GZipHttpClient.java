package com.staliang.ymaps4j.util;

import org.apache.commons.io.IOUtils;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HttpContext;
import org.apache.log4j.Logger;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Alexandr_Badin on 12.08.2015
 */
public class GZipHttpClient {

    private static final Logger logger = Logger.getLogger(GZipHttpClient.class);

    private final HttpClient client;

    private HttpClient getClient() {
        DefaultHttpClient client = new DefaultHttpClient();
        client.addRequestInterceptor(new HttpRequestInterceptor() {

            public void process(
                    final HttpRequest request,
                    final HttpContext context) throws HttpException, IOException {
                if (!request.containsHeader("Accept-Encoding")) {
                    request.addHeader("Accept-Encoding", "gzip");
                }
            }

        });

        client.addResponseInterceptor(new HttpResponseInterceptor() {

            public void process(
                    final HttpResponse response,
                    final HttpContext context) throws HttpException, IOException {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    Header header = entity.getContentEncoding();
                    if (header != null) {
                        HeaderElement[] elements = header.getElements();
                        for (int i = 0; i < elements.length; i++) {
                            if (elements[i].getName().equalsIgnoreCase("gzip")) {
                                response.setEntity(
                                        new GzipDecompressingEntity(response.getEntity()));
                                return;
                            }
                        }
                    }
                }
            }

        });
        return client;
    }

    public GZipHttpClient() {
        client = getClient();
    }

    public String get(String url) throws IOException, URISyntaxException {
        if (logger.isDebugEnabled()) {
            logger.debug(url);
        }

        HttpResponse resp = client.execute(new HttpGet(new URI(url)));
        String result = new String(IOUtils.toByteArray(resp.getEntity().getContent()), "UTF-8");

        if (logger.isDebugEnabled()) {
            logger.debug(result);
        }

        return result;
    }
}
