package com.capitole.inditex.price.infrastructure.inbound.mapper;
import com.capitole.inditex.price.domain.model.Price;
import com.capitole.inditex.price.infrastructure.outbound.entities.PriceEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PriceMapper {
    List<Price> map(List<PriceEntity> priceList);
    default Price map(PriceEntity price) {
        return Price.builder().brandId(price.getBrand().getBrandId())
                .productId(price.getProduct_id())
                .priceList(price.getPrice_list())
                .startDate(price.getStart_date())
                .endDate(price.getEnd_date())
                .totalPrice(price.getTotal_price())
                .priority(price.getPriority())
                .build();
    }
}
