package com.team.application.model.automap;

import com.alipay.api.domain.AlipayTradePayModel;
import com.team.application.model.dto.PayDTO;
import org.mapstruct.Mapper;

@Mapper(config = CentralConfig.class)
public interface PayAutoMap {

    PayDTO mapToDto(AlipayTradePayModel model);

    AlipayTradePayModel mapToModel(PayDTO dto);

}
