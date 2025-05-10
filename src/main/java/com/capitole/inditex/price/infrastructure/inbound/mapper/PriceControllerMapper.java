package com.capitole.inditex.price.infrastructure.inbound.mapper;
import com.capitole.inditex.domain.GetPriceResponse;
import com.capitole.inditex.price.domain.model.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PriceControllerMapper {
    @Mapping(target = "startDate", source = "price", qualifiedByName = "startDate")
    @Mapping(target = "endDate", source = "price", qualifiedByName = "endDate")
    GetPriceResponse toDTO(Price price);
    @Named("startDate")
    default OffsetDateTime mapStartDate(Price p) {
        ZonedDateTime zonedDateTime = p.getStartDate().atZone(ZoneId.systemDefault());
        return zonedDateTime.toOffsetDateTime();
    }
    @Named("endDate")
    default OffsetDateTime mapEndDate(Price p) {
        ZonedDateTime zonedDateTime = p.getStartDate().atZone(ZoneId.systemDefault());
        return zonedDateTime.toOffsetDateTime();
    }
    List<GetPriceResponse> toDTO(List<Price> price);
}
