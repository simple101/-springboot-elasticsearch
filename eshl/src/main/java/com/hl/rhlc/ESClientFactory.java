package com.hl.rhlc;

import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Log4j2
public class ESClientFactory {
    @Value("${es.rest.high.level.host}")
    private String HOST;
    @Value("${es.rest.high.level.port}")
    private int PORT;
    private static final String SCHEMA = "http";
    private static final int CONNECT_TIME_OUT = 1000;
    private static final int SOCKET_TIME_OUT = 30000;
    private static final int CONNECTION_REQUEST_TIME_OUT = 500;
    private static final int MAX_CONNECT_NUM = 100;
    private static final int MAX_CONNECT_PER_ROUTE = 100;
    private static RestClientBuilder builder;

    @PostConstruct
    public void init() {
        log.info(".. start init RestHighLevelClient");
        HttpHost HTTP_HOST = new HttpHost(HOST, PORT, SCHEMA);
        builder = RestClient.builder(HTTP_HOST);
        setConnectTimeOutConfig();
        setMutiConnectConfig();
        log.info(".. end init RestHighLevelClient");
    }

    // 主要关于异步httpclient的连接延时配置
    private void setConnectTimeOutConfig() {
        builder.setRequestConfigCallback(builder -> {
            builder.setConnectTimeout(CONNECT_TIME_OUT);
            builder.setSocketTimeout(SOCKET_TIME_OUT);
            builder.setConnectionRequestTimeout(CONNECTION_REQUEST_TIME_OUT);
            return builder;
        });
    }

    /**
     * 主要关于异步httpclient的连接数配置
     */
    private void setMutiConnectConfig() {
        builder.setHttpClientConfigCallback(httpAsyncClientBuilder -> {
            httpAsyncClientBuilder.setMaxConnTotal(MAX_CONNECT_NUM);
            httpAsyncClientBuilder.setMaxConnPerRoute(MAX_CONNECT_PER_ROUTE);
            return httpAsyncClientBuilder;
        });
    }

    public RestClient getClient() {
        RestClient restClient = builder.build();
        return restClient;
    }

    public RestHighLevelClient getHighLevelClient() {
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(builder);
        return restHighLevelClient;
    }

    public void close(RestHighLevelClient restHighLevelClient) {
        if (restHighLevelClient != null) {
            try {
                restHighLevelClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
