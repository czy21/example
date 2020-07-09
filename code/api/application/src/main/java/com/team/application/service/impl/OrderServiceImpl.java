package com.team.application.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayOpenPublicTemplateMessageIndustryModifyRequest;
import com.alipay.api.response.AlipayOpenPublicTemplateMessageIndustryModifyResponse;
import com.team.application.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private static final String APP_ID = "2016102900777119";
    private static final String APP_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDFpZEHFXKO3OH382pHR/KbVicFAaDRmrXaYGbOpWys12EKoUyt2Cnb8EyJtKfoLvuaqaqbmWgEsSe0VYGKgNtwubyy0ZsBjdMOFXBQuVlkZCq3in0kLtc0GsWL9WnNPFRCaOZSvXY0rPssY295bpRxvqS5Tk3MOH7meJ9xC/ob/4cD42M8qi+3cvPNGfcoY5RXVKTXoK2sV2bedOmK8fRHsY9Z7v11m6kW9BN8yHQ9K1SKTstrSJV3HGk3HkLTVrCK+aJQHCJ10bVfVphq8+RzPIr/a47M//w8CaNxMQQ8opYWGLr6eGDm1+n+7Pzm44/Gzd+VIXYgFbDyMQSs1yQBAgMBAAECggEAX+cOkBlgFOIo419BWoUWuzj6hftcRViElQnh2LN8IAICHF5YDqKs7QOUn90A/DCP9WQOXKH7TwuG8IHs8X/E+tWlMj9yrzZaCqNsdD77mTLhZ13TixMPaUD0Sg0y4Vs8p1yvJca47KZi2g8FsC5v6SFQXHoaVdWz/bDZ7Mia+6oO08ZLZWxfikhr9fHVrAszA6QwFweZUsXIu8Z+GcIs4J5FDPG1E7eiAOKZA9OwUpE2hBYRz6WF/nvjW5EMqJxITjKk/IjVvmJy/eNKoADv7zSHJP5t208IH0U/+a7evnIAH7d8XWazMzh+fQRLDYQ+lsngQ5OX91D2zQb8tOnigQKBgQDw1TeB1cdtgjNQGlaLF8tlnxlZeV5i1/Csaa0Pfs2Fw2Ukq8KFQRBhO++Jl/fBRmu+27o04C3ZlKJK9OcZGlJ9kt9TymC39VkAIbbrNZ/TSuvY83NIOLLFiCttBQiCovm5eZglbtXnG63oVpWGHgazeP1BRO2TuHs3MwZV9FWzRQKBgQDSGBbcF0k/WlhBXmnwG2g10DIehKU2M6jn6Wt7wpeopleTvkjKVtUrMTc1SBYG9XKt60ZrmvPrDzi8HRWLBYTeMgqRHwcSFV5bNAqlIagtXVjS0L0yFdcSZKyQdz6oQ2ss+MtSl4ZDOq30FrSGa3wYTX05izqNwaFyOlJ4I/C7jQKBgDlgsy7NvgvLH2qQO9o7uwvlEecNUX1/MSoCq2arsU+TM/++pEOXMs3Xt8QyXqa/nmAEfaKnXoUeMmRlYMHMUab1XRvbdZueaqvrXGdBiWiPyw0PQ4ILcBw7ywzYTCTDq3eTDrqsY24CXDakmk8vzCzqQHxSbb/qDgEiuPm1ks15AoGAeXFZTXHcclWL7IVh4VxGWcz2G7P7OCCWgy/nZIuwVNmKiCmXM/cLk8MIMbFwoichWi1E8JCKbJbI6dw6Brly+2mbuBViZe6aM0ga2ydgapCp9nqY0qnTP5uYMI3tVyBZhI5wIVbbUmNKCG1TftwEyJuKRkgX2bdS1o+l+jJ0RkUCgYByjstxwxdAXYj1R2b+D9l7/V67NsRRnD6ACKJcv8Rpy2l8F9EOXcQUT3rRKeUS/SrMYy0t28cM36C5M5FJFrhG/+78y8/Pny3WuU+raNVpVPqDrjKdGWuiJtU4iyVKd45dnKEtUrxqNdp6J8bWn6WGKRN/FmPJiZwbsCWa12s/Vw==";
    private static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAn7MbnuJcPuYIVSxZMiqxJc+4tyKdh4rGNe73gKEpoZpxq/xQFzmmvysANZcPcda/c0zkO9No8A3rzX4BU+15pr1Ee7ceXCz2u/PJq9uimZ2AVy5VjzUAC2u4oZP09PRJetK/ztwn+mXmSKfE8eo3ZrqRKbpEacio6SQhcmjZbh9ENmWcA8m92KnZEDFwKkV/WInqXw4yQ949BpmNPavyg4nf+8bTWo0PjTqzYMSH6zMWOQJHJBYpwIZXXJjHC8trRKebBSJ24AdwWszPpZrNSOF5BkQwjuAAos8kmxK3y3tS/NtAyDp0k+GPqQLOfWNg6tf6mmnxfIMIrbSwdDuhWwIDAQAB";

    @Override
    public AlipayOpenPublicTemplateMessageIndustryModifyResponse pay() throws AlipayApiException {

        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do", APP_ID, APP_PRIVATE_KEY, "json", "utf-8", ALIPAY_PUBLIC_KEY, "RSA2");

//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.open.public.template.message.industry.modify
        AlipayOpenPublicTemplateMessageIndustryModifyRequest request = new AlipayOpenPublicTemplateMessageIndustryModifyRequest();
//SDK已经封装掉了公共参数，这里只需要传入业务参数
//此次只是参数展示，未进行字符串转义，实际情况下请转义
        request.setBizContent("  {" +
                "    \"primary_industry_name\":\"IT科技/IT软件与服务\"," +
                "    \"primary_industry_code\":\"10001/20102\"," +
                "    \"secondary_industry_code\":\"10001/20102\"," +
                "    \"secondary_industry_name\":\"IT科技/IT软件与服务\"" +
                " }");
        return alipayClient.execute(request);
    }
}
