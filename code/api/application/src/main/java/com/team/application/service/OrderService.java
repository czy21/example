package com.team.application.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.response.AlipayOpenPublicTemplateMessageIndustryModifyResponse;

public interface OrderService {

    AlipayOpenPublicTemplateMessageIndustryModifyResponse pay() throws AlipayApiException;
}
