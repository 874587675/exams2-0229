package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program:
 * @ClassName:
 * @description:
 * @author: zgc
 * @date:
 * @Version 1.0
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/alipay")
public class PayController {
    /*
        创建Controller类后，首先设置如下私有属性
        APP_ID （appId，从沙箱管理页面获得）
        APP_PRIVATE_KEY （应用私钥，最开始在密钥工具生成而来）
        ALIPAY_PUBLIC_KEY （支付宝公钥，上一步骤获得）
        GATEWAY_URL （支付宝网关地址，在开发平台沙箱管理页面中获得）
        SIGN_TYPE （签名类型）
        NOTIFY_URL （异步回调地址，须是公网IP，后面再解释）
        RETURN_URL （同步回调地址，可以是私网IP）
    **/
    
    private final String APP_ID = "9021000138628340";
    
    private final String APP_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCC0QkLuU2OEqQvUR5XH1G96G2KRHr+pRT7O6gcZVkrDtIMkBQLRRhPMWQhrQ0ufy4u9h9Y+n2UDnkfzFM6J9ArlMk3UP1QQqj34O35tUS1Ugl/I+arE6yKDBwbxvYGFdhVMGp0HCf+PEkRYKt3SCK8z4027j4cDlArMU5/5sSN1CQbMGE3Cx5LU2+e7Evl7gKVTQLprxgubOX0h+/uHo44tvYKNI1F82FBFSMInK85Uits0vE8NvuMH1k2r9qs/1I8wqjmGOiTnJ3dGZ59kaXlrpx094W7erj1ClQ99a+/mP4S5PtBrKF0Kh+NkcvSiXOYzAe4rzxKsmZUdYZ1RnolAgMBAAECggEAHIu62pkUCGWe+EhOQ5qs/rDifmKVKE4jtvL8qz2eaJvyUBV8mjz4ogdYH1Ug5D2SeWLx0jvO/WjvG3Y9zxny/h+YjP6aWFJDhtvJck0Q8jQ1qE3QPxKq+vDLjXAsLYXE0EohYLboLz0+gJFEuWG/Ws47tXnlib/B1LsMszJLP9txnOL4BGPCGk54TtgtfizX5C1/17dwK2Namhb2mtzB3uswhK/qR8fU0ZRgjC0ar9y0Siydk8RwbMBucNeFLAiYQMA34FVXGNtkI3DtGTpZ9tRr1bul51bK6wz6xA7EVCFHUCFgkyAOjIWV+NP2wiedzUWuftyXOjpE2sumxa2BIQKBgQDrimFZtQ3x7pQAm8871W6chedFLzgiWMky5DKFjqArTxb0mu72smVFR65Bo8RWOgMLcryAd/HBPMpB9P4J8bSegqS4AEJCPIsWnOEB7ofgVoDAVcBAivnYuoiq5SQl+HqSl5kBsecW9XOl1D/l0kog9aYHK+q+2Jur0FLwwa/V+QKBgQCOLfMnq07hwBrp8xVlKKPOzDDO1nTixfLtxUh/H4uesHdvOQZGApeRbqYfMhWkgPCkOwQGZJumTWI+mQiUnFPYIZNOPaf5fGs2LlGy6gUmXSOuJlmFZmiGsPnjCmvRlHdqOKO65k+ZIKQ1MSrPdjB3Q7FGXxCsp0f9f/2uU8KgjQKBgC+/FG7f4dzKFRxueTrS0CJQjooawVFNGDQFyCwEtnkxBiE9ivVdQmQh3LQXo4QrLFTUxFtydQJL9WAbbqfLZGbBauyVuNi+dx91W7NNjCOwxVa3y0oKIg2J+oxR+NrtnMM8kdqjGoov5WqXNQC8eSRnR2mqwGJBfIT+rOF7Ec7ZAoGAaQTXzj1jDqzvNE8xRn82P9HjW2RdAgRk4+wNGJkccCK4lZbxY1UWz32DhxdflRsIpMZSusYlkjkmkEZJaDvwUny6adhw8n9MUcD058qj8ypo6IG7LiXun06pxWI1MaEQc41SowLrWJgbso6WqJcMRYvirbAaiO0m58BaiSGlaSECgYAH7kBqW3aWgCx9Dguy3SxqK7NoZX6SnAobz8UEFatUTSByCHE7BZu7r+R8EUol6QWSb0YxRfX1X0UU3mJz8mypMPytS1i30KHn9I8zndTRu6f5cfmWMB4h49ZJdloV2si+kOhhm4UY3Se/5KMOJN0iSXbUM52OAQdJEuECnx/CeA==";
    
    private final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApexvahz1cAD2NwDWWAul42nIwHnua2oKr+2iU5KvhCKu2jbFD8uEPrpU+vkCnCoRPjTsp6H+oxF7zQCA06lvUWM2kQkL7SSEjLN1o2P7LEfayVJRAZBqxCAbumSDezvk7qkcEMWHTBEt1l0ziPMeplFu1/wruwPwfqIHCcJb8EPjeMIrLywFIhbPGasgDTPoeK4x0iUHkxS/rMPRwhCUuBqUytyWDA4H7eAJyXObFxg2wLZO22t11zW7PoZaDCoswqQlJ5lNN1dgAkd/ejbnDDBxzcWW3IYEXbfXrVo9uUgK21s33yXjvaMf2JTRIc2OIsgGtiLBgY5xiqPooii0PwIDAQAB";
    
    private final String GATEWAY_URL = "https://openapi-sandbox.dl.alipaydev.com/gateway.do";
    
    private final String SIGN_TYPE = "RSA2";
    
    private final String NOTIFY_URL = "http://your_notify_url";
    
    private final String RETURN_URL = "http://your_return_url";
    
    
    
    
}
