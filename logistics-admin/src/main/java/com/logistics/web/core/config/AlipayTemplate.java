package com.logistics.web.core.config;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.logistics.system.domain.vo.PayVo;
import lombok.Data;
import org.springframework.stereotype.Component;

//@ConfigurationProperties(prefix = "alipay")
@Component
@Data
public class AlipayTemplate {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public String app_id ="2021000117643416";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public String merchant_private_key="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCOVuLBKf8kahMA/HSDFwbDJMlxt3Nmcte6aKH//8vwZn8ezTwCyZBbSX/maUMMSr6NPShSd8cL4vibJBv2yckNZMSCtnl4kNb3kYTvEMZEVChk0xVB2mwYmzslUHpYIykUzFaHMelLOpSX2jbM4x7RWJnknnycRUyBq57tu1/3gK1ZglSn3TnCI1wGlnx3rfyq3y1zdisrifue2HhVJsMg5rFg2EPKRg+tOgy2yDM08Nl6qLsxIDRgi8xFxIq8rbxiygPQT3P+7jDTsUATYD+NQ/xHB3PKtcV+LIVPcG6faHVbhJRbs3crZRftMmMIEJmBa5gjnQ2aH3NLcIQk5mLNAgMBAAECggEAcz653XfpUkxoEpZUp4bPPNTeHSpRTxOQwybQc3tiXcQ/DGtTOVaFgMPjwGRdmugxnB12Nj8wc9lTPBliYtBB7ow15zdi5lEvG8dAgGdugAg7pFabkXQNrWaR3sZoKFBfhK1lKZUptHU7ti9By2Xgn9NC1q3TaptZNGwGA6mAK91OCt/gOYny39pa3LVQgRA2yaWxcnMKfUaAKj562xdEvHxx6wNDXOp3DIrAF2AiOEp9QRjiu02hAjtDYdWaE9RlfpDxG3hyhFK1ycPSccpcIRQUy8PMgbFTv7d8BQjKZ+WdTvpV6+N/Cc52xXzMxxWswEQbXGVyu+8GYC08U2TnaQKBgQD3C9ufav7HDh0obc8crYLzH6ejCLNn1B0TXUyXa+fx1yvit3LVuRtpBKkhnwi/45xrwZaRyOVIH57Alg3bchP2O1rrOsvXMSKULKKG7ARiBwyGP8Wicaf7X+EOM3g6TjUcIGyxzjKveNsidUgZkPIuI34dstknHNJ4chMjaoocJwKBgQCTf4mScitvp7JHq+IWL0SPUciDUwMUUK0JrUj3ajRyEmzSReiMs7nEN+n3e0lqQ8nokUcFMXXPTCCS2u1Y5W187CXZ8yaps3UCxD91tV7KFYYe/BIo26BLWmwFxze6Higj4M743ECILkp3DlFPHE8egKaMtCt8zlNFBA3gZtT96wKBgDgKzZidegyH7B9mwawcxG8IyEuYxrIQEUR6Tsyu4uAe0srMmNVY95ncP83ruN1m8e4T7WdG7pRs7+N6ZsxYVaTvFBH2tZUshU6dzYTT966RMMsnnlrac6P3vSMuk+COK0glJuMzBpZ90SBBAMkj83x5+rsb/lz8EdbW8fZ/zbLTAoGANYvndbLc8fv/TOWpfkRKr30RPhu00ns3VnwCMU/tGjx2JSAb/HiCyClEVwP68wkJE9Up40jBbDBG4wl3gP5JcUCJvUX4k0MDB64XG7RsZRDLUBRSw2J4HuMEtFROdtLb36qNeOr8wnOLtQ0Nl5q/XmN2Ez6VSwncUQHXx8+NI2ECgYEA9S4otefNoBKT3tm7FswUJOShf4POUaCVac7osD3iIFVegjz73RNxbWHTrVlBDK7DIAfoUQtN8Lf7yni7RPOMTi+nrZSyXEIC+9qLGNlC63skqjvQ9nm7UHsDoD+G2haMrjh6a0HQ4lfNbVvn9HZmCWN3YukzsdI2uJI7+UiI1YI=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public String alipay_public_key="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnAnylC0OqRxpqqrrFe9JnaU2kJEiudk/MYSdJvi0m1Sy9DIOfmxqIa+EJ43lAgqMpvOJ9FjdiSX9Jd4533HkQIOclIPJwx1ygMaUIuoLoL5GIZPJu2ntsgx3OzVGxV+aW4Y3o2KAFyCxPF+edMkb2H69i/x7lK09MtMKzksk1mFzs0JHaaTaKxm9ZAYtiUjXrKniusvvWPt0b1Pb09GmydDfqLaqdB0pxebQU0GouG6D2lhTurqbawCyX65LeEfxxcxck8ZErcCnmtBSVXPKRirP9YiB+4z6GcnSI+RN6q/1vV44QM226AB+5RVSbZXXeupHFYSbmd9Ut8aWgwsDTQIDAQAB";

    // 服务器[异步通知]页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    // 支付宝会悄悄的给我们发送一个请求，告诉我们支付成功的信息
    public String notify_url;

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    //同步通知，支付成功，一般跳转到成功页
    public String return_url;

    // 签名方式
    private  String sign_type;

    // 字符编码格式
    private  String charset;

    //订单超时时间
    private String timeout = "1m";

    // 支付宝网关； https://openapi.alipaydev.com/gateway.do
    public String gatewayUrl;

    public  String pay(PayVo vo) throws AlipayApiException {

        //AlipayClient alipayClient = new DefaultAlipayClient(AlipayTemplate.gatewayUrl, AlipayTemplate.app_id, AlipayTemplate.merchant_private_key, "json", AlipayTemplate.charset, AlipayTemplate.alipay_public_key, AlipayTemplate.sign_type);
        //1、根据支付宝的配置生成一个支付客户端
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl,
                app_id, merchant_private_key, "json",
                charset, alipay_public_key, sign_type);

        //2、创建一个支付请求 //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(return_url);
        alipayRequest.setNotifyUrl(notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = vo.getOut_trade_no();
        //付款金额，必填
        String total_amount = vo.getTotal_amount();
        //订单名称，必填
        String subject = vo.getSubject();
        //商品描述，可空
        String body = vo.getBody();

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"timeout_express\":\""+timeout+"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        String result = alipayClient.pageExecute(alipayRequest).getBody();

        //会收到支付宝的响应，响应的是一个页面，只要浏览器显示这个页面，就会自动来到支付宝的收银台页面
        System.out.println("支付宝的响应："+result);

        return result;

    }
}