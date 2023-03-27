package com.logistics;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.logistics.common.utils.QRGenUtils;
import com.google.zxing.qrcode.encoder.QRCode;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringAmqpTest {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    String APP_ID="2021000117643416";
    // 商户私钥，您的PKCS8格式RSA2私钥
    String APP_PRIVATE_KEY="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCOVuLBKf8kahMA/HSDFwbDJMlxt3Nmcte6aKH//8vwZn8ezTwCyZBbSX/maUMMSr6NPShSd8cL4vibJBv2yckNZMSCtnl4kNb3kYTvEMZEVChk0xVB2mwYmzslUHpYIykUzFaHMelLOpSX2jbM4x7RWJnknnycRUyBq57tu1/3gK1ZglSn3TnCI1wGlnx3rfyq3y1zdisrifue2HhVJsMg5rFg2EPKRg+tOgy2yDM08Nl6qLsxIDRgi8xFxIq8rbxiygPQT3P+7jDTsUATYD+NQ/xHB3PKtcV+LIVPcG6faHVbhJRbs3crZRftMmMIEJmBa5gjnQ2aH3NLcIQk5mLNAgMBAAECggEAcz653XfpUkxoEpZUp4bPPNTeHSpRTxOQwybQc3tiXcQ/DGtTOVaFgMPjwGRdmugxnB12Nj8wc9lTPBliYtBB7ow15zdi5lEvG8dAgGdugAg7pFabkXQNrWaR3sZoKFBfhK1lKZUptHU7ti9By2Xgn9NC1q3TaptZNGwGA6mAK91OCt/gOYny39pa3LVQgRA2yaWxcnMKfUaAKj562xdEvHxx6wNDXOp3DIrAF2AiOEp9QRjiu02hAjtDYdWaE9RlfpDxG3hyhFK1ycPSccpcIRQUy8PMgbFTv7d8BQjKZ+WdTvpV6+N/Cc52xXzMxxWswEQbXGVyu+8GYC08U2TnaQKBgQD3C9ufav7HDh0obc8crYLzH6ejCLNn1B0TXUyXa+fx1yvit3LVuRtpBKkhnwi/45xrwZaRyOVIH57Alg3bchP2O1rrOsvXMSKULKKG7ARiBwyGP8Wicaf7X+EOM3g6TjUcIGyxzjKveNsidUgZkPIuI34dstknHNJ4chMjaoocJwKBgQCTf4mScitvp7JHq+IWL0SPUciDUwMUUK0JrUj3ajRyEmzSReiMs7nEN+n3e0lqQ8nokUcFMXXPTCCS2u1Y5W187CXZ8yaps3UCxD91tV7KFYYe/BIo26BLWmwFxze6Higj4M743ECILkp3DlFPHE8egKaMtCt8zlNFBA3gZtT96wKBgDgKzZidegyH7B9mwawcxG8IyEuYxrIQEUR6Tsyu4uAe0srMmNVY95ncP83ruN1m8e4T7WdG7pRs7+N6ZsxYVaTvFBH2tZUshU6dzYTT966RMMsnnlrac6P3vSMuk+COK0glJuMzBpZ90SBBAMkj83x5+rsb/lz8EdbW8fZ/zbLTAoGANYvndbLc8fv/TOWpfkRKr30RPhu00ns3VnwCMU/tGjx2JSAb/HiCyClEVwP68wkJE9Up40jBbDBG4wl3gP5JcUCJvUX4k0MDB64XG7RsZRDLUBRSw2J4HuMEtFROdtLb36qNeOr8wnOLtQ0Nl5q/XmN2Ez6VSwncUQHXx8+NI2ECgYEA9S4otefNoBKT3tm7FswUJOShf4POUaCVac7osD3iIFVegjz73RNxbWHTrVlBDK7DIAfoUQtN8Lf7yni7RPOMTi+nrZSyXEIC+9qLGNlC63skqjvQ9nm7UHsDoD+G2haMrjh6a0HQ4lfNbVvn9HZmCWN3YukzsdI2uJI7+UiI1YI=";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    String ALIPAY_PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnAnylC0OqRxpqqrrFe9JnaU2kJEiudk/MYSdJvi0m1Sy9DIOfmxqIa+EJ43lAgqMpvOJ9FjdiSX9Jd4533HkQIOclIPJwx1ygMaUIuoLoL5GIZPJu2ntsgx3OzVGxV+aW4Y3o2KAFyCxPF+edMkb2H69i/x7lK09MtMKzksk1mFzs0JHaaTaKxm9ZAYtiUjXrKniusvvWPt0b1Pb09GmydDfqLaqdB0pxebQU0GouG6D2lhTurqbawCyX65LeEfxxcxck8ZErcCnmtBSVXPKRirP9YiB+4z6GcnSI+RN6q/1vV44QM226AB+5RVSbZXXeupHFYSbmd9Ut8aWgwsDTQIDAQAB";

    String CHARSET = "UTF-8";
    String serverUrl = "https://openapi.alipaydev.com/gateway.do";
    String sign_type = "RSA2";

    @Test
    public void test15() throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(serverUrl,APP_ID,APP_PRIVATE_KEY,"json",CHARSET,ALIPAY_PUBLIC_KEY,sign_type);
        AlipayTradePrecreateRequest alipayRequest = new AlipayTradePrecreateRequest();
        // 设置支付宝异步通知回调地址 (注意：这个网址必须是可以通过外网访问的网址)
        alipayRequest.setNotifyUrl("");
        alipayRequest.setBizContent ( "{"   +
                "\"out_trade_no\":\"123456\"," + // 商户订单号
                "\"total_amount\":\"88.88\"," +	// 商品价格
                "\"subject\":\"测试\"," +	// 商品标题
                "\"store_id\":\"公司名\"," +	// 组织或公司名
                "\"timeout_express\":\"90m\"}" );	// 订单有效时间
        AlipayTradePrecreateResponse response = alipayClient.execute (alipayRequest);
        // 返回支付宝支付网址，用于生成二维码
        String qr = response.getQrCode();
        System.out.println("qr===>"+qr);
    }



    @Test
    public void test14() throws Exception {
//        byte[] bytes = QrCodeUtil.generatePng("http://192.168.1.122:9314/pay", 300, 200);
//
//        System.out.println();
        BufferedImage qrImageBuffer = QRGenUtils.createQRImageBuffer("http://192.168.1.122:9314/pay", 200, 200);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(qrImageBuffer, "png", os);
        Base64 base64 = new Base64();
        String base64Img = new String(base64.encode(os.toByteArray()));
//        System.out.println(base64Img);
        QRCode qrCode = new QRCode();

    }

    @Test
    public void  test13() throws MessagingException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo("2891517520@qq.com");
        message.setSubject("找回密码");
        message.setText("密码："+"9999");
        javaMailSender.send(message);
//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
//        mimeMessageHelper.setSubject("找回密码");
//        mimeMessageHelper.setText("123123");
//        mimeMessageHelper.setFrom(fromEmail);
//        mimeMessageHelper.setTo("2891517520@qq.com");
//        javaMailSender.send(mimeMessage);

    }

}
