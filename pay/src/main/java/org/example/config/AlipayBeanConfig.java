package org.example.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConfig;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program:
 * @ClassName:
 * @description:
 * @author: zgc
 * @date:
 * @Version 1.0
 **/
@Configuration
@RequiredArgsConstructor
public class AlipayBeanConfig {
    
    private final PayConfig payConfig;
    
    @Bean
    public AlipayTradePagePayRequest alipayTradePagePayRequest(){
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setReturnUrl(payConfig.getReturnUrl());
        request.setNotifyUrl(payConfig.getNotifyUrl());
        return request;
    }
    
    //AlipayClient 是支付宝 SDK 中的一个核心对象，用于与支付宝服务器进行通信。
    //配置请求：通过构造函数传入的参数（如网关 URL、应用 ID、私钥等）配置了请求的基本信息，确保请求能够正确发送。
    //发送请求：AlipayClient 提供方法来执行 API 请求，处理响应，并解析结果。
    //安全性：使用私钥和公钥进行签名和验签，确保交易的安全性和数据的完整性。
    @Bean
    public AlipayClient alipayClient(){
        AlipayClient alipayClient = new DefaultAlipayClient(payConfig.getGatewayUrl(), payConfig.getAppId(),
                payConfig.getMerchantPrivateKey(),"json", payConfig.getCharSet(), payConfig.getAlipayPublicKey(),
                payConfig.getSignType());
        return alipayClient;
    }
}
