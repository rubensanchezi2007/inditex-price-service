package com.capitole.inditex.price.application.service;
import com.capitole.inditex.price.application.port.inbound.PriceServicePort;
import com.capitole.inditex.price.application.port.outbound.PriceRepositoryPort;
import com.capitole.inditex.price.domain.model.Price;
import com.capitole.inditex.price.domain.model.PriceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceService implements PriceServicePort {
    private final PriceRepositoryPort priceRepositoryPort;

    @Override
    public Price getPriceByDate(Long productId, Integer brandId, LocalDateTime date) {
        List<Price> listPrices = priceRepositoryPort.findPriceByDate(productId, brandId, date);
        return listPrices.stream().max(Comparator.comparing(Price::getPriority))
                .orElseThrow(PriceException::notFound);
    }
}
