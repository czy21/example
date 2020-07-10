package com.team.application.model.automap;

import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.response.AlipayTradePayResponse;
import com.team.application.model.dto.PayRequest;
import com.team.application.model.dto.PayResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CentralConfig.class)
public interface PayAutoMap {

    @Mapping(source = "outTradeNo", target = "tradeNo")
    PayRequest mapToDto(AlipayTradePayModel model);

    @Mapping(source = "tradeNo", target = "outTradeNo")
    AlipayTradePayModel mapToModel(PayRequest dto);

    @Mapping(source = "outTradeNo", target = "tradeNo")
    @Mapping(source = "tradeNo", target = "tradePlatformNo")
    PayResponse mapToResponse(AlipayTradePayResponse response);

    @Mapping(source = "tradeNo", target = "outTradeNo")
    @Mapping(source = "tradePlatformNo", target = "tradeNo")
    AlipayTradePayResponse mapToResponse(PayResponse response);

}
