package com.mytest.config.elasticsearch;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyStore;


@Slf4j
@Component
public class ESClient {

    public RestHighLevelClient restHighLevelClient;

    @Autowired
    private EsConfig esConfig;

    @PostConstruct
    private void init() {
        //初始化RestHighLevelClient
        this.initRestHighLevelClient();
    }

    private ESClient() {
    }

    private void initRestHighLevelClient() {
        try {
            if (restHighLevelClient == null) {
                if (!esConfig.getIsSingleton()) {
                    //正式环境 使用ssl认证 集群连接
                    log.info("******************ES集群连接开始****************");
                    restHighLevelClient = getClusterHighLevelClient();
                    log.info("******************ES集群连接成功****************");
                    return;
                }
                //单机连接
                log.info("******************单机ES连接开始****************");
                restHighLevelClient = getSingleHighLevelClient();
                log.info("******************单机ES连接成功****************");
            }
        } catch (Exception e) {
            log.error("es连接出现异常:{}", e.toString());
        }
    }

    /**
     * 集群连接
     *
     * @return
     * @throws Exception
     */
    private RestHighLevelClient getClusterHighLevelClient() throws Exception {
        Path trustStorePath = Paths.get(esConfig.getCapath());
        KeyStore truststore = KeyStore.getInstance("pkcs12");

        String keyStorePass = esConfig.getKeystorepass();
        InputStream is = Files.newInputStream(trustStorePath);
        truststore.load(is, keyStorePass.toCharArray());

        SSLContextBuilder sslBuilder = SSLContexts.custom().loadTrustMaterial(truststore, null);
        final SSLContext sslContext = sslBuilder.build();

        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(esConfig.getUsername(), esConfig.getPassword()));
        log.info("连接ES集群: {}:{},{}:{},{}:{}", esConfig.getNode1Ip(), esConfig.getNode1Port(), esConfig.getNode2Ip(), esConfig.getNode2Port(), esConfig.getNode3Ip(), esConfig.getNode3Port());
        RestClientBuilder rclientBuilder = RestClient.builder(
                new HttpHost(esConfig.getNode1Ip(), esConfig.getNode1Port(), esConfig.getNode1Scheme()),
                new HttpHost(esConfig.getNode2Ip(), esConfig.getNode2Port(), esConfig.getNode2Scheme()),
                new HttpHost(esConfig.getNode3Ip(), esConfig.getNode3Port(), esConfig.getNode3Scheme()))
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                        return httpClientBuilder.setSSLContext(sslContext)
                                .setSSLHostnameVerifier(new NoopHostnameVerifier())
                                .setDefaultCredentialsProvider(credentialsProvider);
                    }
                });
        restHighLevelClient = new RestHighLevelClient(rclientBuilder);
        return restHighLevelClient;
    }

    /**
     * 单机连接
     *
     * @return
     * @throws Exception
     */
    private RestHighLevelClient getSingleHighLevelClient() {
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(esConfig.getUsername(), esConfig.getPassword()));
        log.info("连接单机ES: {}:{}", esConfig.getNode1Ip(), esConfig.getNode1Port());
        RestClientBuilder rclientBuilder = RestClient.builder(new HttpHost(esConfig.getNode1Ip(), esConfig.getNode1Port(), esConfig.getNode1Scheme()))
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                        return httpClientBuilder.setSSLHostnameVerifier(new NoopHostnameVerifier())
                                .setDefaultCredentialsProvider(credentialsProvider);
                    }
                });
        restHighLevelClient = new RestHighLevelClient(rclientBuilder);
        return restHighLevelClient;
    }

}