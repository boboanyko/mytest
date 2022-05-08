package com.mytest.config.elasticsearch;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
//@Configuration
@ConfigurationProperties(prefix = "elasticsearch")
public class EsConfig {

    private Boolean isSingleton;
    private String capath;
    private String keystorepass;
    private String username;
    private String password;

    private String node1Ip;
    private Integer node1Port;
    private String node1Scheme;

    private String node2Ip;
    private Integer node2Port;
    private String node2Scheme;

    private String node3Ip;
    private Integer node3Port;
    private String node3Scheme;
}
