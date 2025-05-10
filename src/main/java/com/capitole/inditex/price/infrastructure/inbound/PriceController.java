package com.capitole.inditex.price.infrastructure.inbound;
import com.capitole.inditex.api.PriceApi;
import com.capitole.inditex.domain.GetPriceResponse;
import com.capitole.inditex.price.application.port.inbound.PriceServicePort;
import com.capitole.inditex.price.infrastructure.inbound.mapper.PriceControllerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequiredArgsConstructor
public class PriceController implements PriceApi {
    private final PriceServicePort priceService;
    private final PriceControllerMapper priceControllerMapper;

    @Override
    public ResponseEntity<GetPriceResponse> getPriceByProduct(String productId, String brandId, String date) {
        return ResponseEntity.ok(
                priceControllerMapper.toDTO(priceService.getPriceByDate(Long.valueOf(productId), Integer.valueOf(brandId), LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))))
        );
    }
}
