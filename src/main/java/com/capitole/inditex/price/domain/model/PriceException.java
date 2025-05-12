package com.capitole.inditex.price.domain.model;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PriceException extends RuntimeException {
    private final PriceError error;

    public static PriceException notFound() {
        return PriceException.builder().error(PriceError.ERROR_PRICE_NOT_FOUND).build();
    }

    public static PriceException gateway() {
        return PriceException.builder().error(PriceError.ERROR_GATEWAY_PRICE_SERVICE).build();
    }
}
