package com.capitole.inditex.price.domain.model;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PriceNotFoundException extends RuntimeException {
    private final PriceError error;

    public static PriceNotFoundException notFound() {
        return PriceNotFoundException.builder().error(PriceError.ERROR_PRICE_NOT_FOUND).build();
    }

    public static PriceNotFoundException gateway() {
        return PriceNotFoundException.builder().error(PriceError.ERROR_GATEWAY_PRICE_SERVICE).build();
    }
}
