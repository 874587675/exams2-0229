package org.example.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.config.PayConfig;
import org.example.object.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * @program:
 * @ClassName:
 * @description:
 * @author: zgc
 * @date:
 * @Version 1.0
 **/
@Controller
@RequiredArgsConstructor
@RequestMapping("/alipay")
public class AlipayController {

    private final AlipayTradePagePayRequest alipayRequest;

    private final AlipayClient alipayClient;

    private final PayConfig payConfig;

    //点击支付请求

    /**
     * @return form表单
     * @Author zgc
     * @Description
     * @Param outTradeNo  商户订单号，商户网站订单系统中唯一订单号，必填  对应缴费记录的orderNo
     * @Param totalAmount  付款金额，必填
     * @Param subject 主题 必填
     * @Param body 商品描述 选填
     **/
    @ResponseBody
    @RequestMapping("/pay/{outTradeNo}")
    public AjaxResult Pay(@PathVariable Long outTradeNo) throws AlipayApiException {
        String totalAmount = "88.88";
        String subject = "测试订单";
        String body = "我看看这么个事。看看body有啥用";
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + outTradeNo + "\","
                + "\"total_amount\":\"" + totalAmount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //用于执行支付宝请求并获取响应的请求体，通常pageExecute()方法默认发送Post请求
        //alipayRequest.setMethod('GET'); // 设置请求方法为 GET  如果想使用GET请求
        //const responseBody = alipayClient.pageExecute(alipayRequest).getBody(); // 执行GET请求并获取消息体
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        return AjaxResult.success(result);
    }

    //支付回调
    //这里不能用@RestController
    @ResponseBody
    @RequestMapping("/return_url")
    public String returnUrl(HttpServletRequest request) throws AlipayApiException, UnsupportedEncodingException {
        //获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
//        request.getParameterMap() 方法返回一个 Map<String, String[]>。
//        这个 Map 的键是请求参数的名字，值是一个字符串数组，表示该参数可能的多个值。
//        例如，如果一个表单中有多个同名输入框，所有的值会被存储在一个数组中。
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        
        //调用SDK验证签名
        //这一段代码主要是进行签名验证，而不是加密或解密。
//        具体来说，它的功能是检查来自支付宝的请求数据是否被篡改。以下是更详细的解释：
//
//签名验证：当支付宝发送请求时，它会用私钥对请求参数进行签名。接收方（你的服务器）使用支付宝的公钥来验证这个签名。
//
//安全性：如果验证通过，表示请求数据在传输过程中没有被修改；如果验证失败，则可能是请求被篡改，存在安全风险。
//
//所以，这段代码并不涉及加密或解密，而是确保数据的完整性和安全性。
        boolean signVerified = AlipaySignature.rsaCheckV1(params, payConfig.getAlipayPublicKey(), payConfig.getCharSet(), payConfig.getSignType());
        //以上的是支付宝验签方法
        
        //——请在这里编写您的程序（以下代码仅作参考）——
        //验签成功后
        if (signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

            String result = "提示：trade_no:" + trade_no + "\n out_trade_no:" + out_trade_no + "\n total_amount:" + total_amount;

            // 支付流程成功之后，我们就可以执行自己的流程，比如订单的支付状态改成 已支付
            // .....
//            System.out.println("-----  修改订单状态的代码 ---------");
            String msg = "支付已完成";
            return msg;

        } else {
            return "支付出现问题";
        }
        //——请在这里编写您的程序（以上代码仅作参考）——
    }

    /**
     * @return
     * @Author zgc
     * @Description
     * @Param out_trade_no订单支付时传入的商户订单号, 和支付宝交易号不能同时为空。
     * trade_no,out_trade_no如果同时存在优先取trade_no
     * @Param trade_no 支付宝交易号，和商户订单号不能同时为空
     * @Param query_options 查询选项 商户通过上送该参数来定制同步需要额外返回的信息字段，数组格式,比如
     * 交易结算信息: trade_settle_info
     * 交易支付使用的资金渠道: fund_bill_list
     * 交易支付时使用的所有优惠券信息: voucher_detail_list
     * 交易支付使用单品券优惠的商品优惠信息: discount_goods_detail
     * 商家优惠金额: mdiscount_amount
     * 医保信息: medical_insurance_info
     **/
    @ResponseBody
    @RequestMapping("/query")
    //这里要注意 如果你要使用订单号和流水号来查，流水号一定不能是null，但可以为空，但两者不能同时为空
    public String query(Long outTradeNo) throws AlipayApiException {
        String tradeNo = "";
        //设置请求参数
        AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();
        alipayRequest.setBizContent("{\"out_trade_no\":\""+ outTradeNo +"\","+"\"trade_no\":\""+ tradeNo +"\"}");

        //请求
        String result = alipayClient.execute(alipayRequest).getBody();
        
        return result;
    }
    
    @ResponseBody
    @RequestMapping("/refund")
    public String refund(Long outTradeNo) throws AlipayApiException {
        String refundAmount = "88.88"; // 退款金额
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + outTradeNo + "\"," + "\"refund_amount\":\"" + refundAmount + "\"}");

        String result = alipayClient.execute(alipayRequest).getBody();
        return result;
    }
    
}
