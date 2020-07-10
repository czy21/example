package com.team.application.model.automap;

import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.response.AlipayTradePayResponse;
import com.team.application.model.dto.PayDTO;
import com.team.application.model.dto.PayResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CentralConfig.class)
public interface PayAutoMap {

    @Mapping(source = "outTradeNo", target = "tradeNo")
    PayDTO mapToDto(AlipayTradePayModel model);

    @Mapping(source = "tradeNo", target = "outTradeNo")
    AlipayTradePayModel mapToModel(PayDTO dto);

    @Mapping(source = "outTradeNo", target = "tradeNo")
    @Mapping(source = "tradeNo", target = "tradePlatformNo")
    PayResult mapToResponse(AlipayTradePayResponse response);

    @Mapping(source = "tradeNo", target = "outTradeNo")
    @Mapping(source = "tradePlatformNo", target = "tradeNo")
    AlipayTradePayResponse mapToResponse(PayResult response);

}
