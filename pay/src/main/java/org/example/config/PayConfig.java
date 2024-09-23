package org.example.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @program:
 * @ClassName:
 * @description:
 * @author: zgc
 * @date:
 * @Version 1.0
 **/
@ConfigurationProperties(prefix = "alipay")
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayConfig {
    
    public String appId;
    public String merchantPrivateKey;
    public String alipayPublicKey;
    public String notifyUrl;
    public String returnUrl;
    public String gatewayUrl;
    public String signType;
    public String charSet;
    public String logPath;
}
