package com.capitole.inditex.unit;
import com.capitole.inditex.price.application.port.outbound.PriceRepositoryPort;
import com.capitole.inditex.price.application.service.PriceService;
import com.capitole.inditex.price.domain.model.Price;
import com.capitole.inditex.price.domain.model.PriceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
public class PriceServiceTest {
    @Autowired
    PriceService priceService;
    @MockitoBean
    PriceRepositoryPort priceRepositoryPort;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void findPriceByDate_Found() {
        ArrayList<Price> expectedList = new ArrayList<>();
        Price expectedPrice = Price.builder()
                .productId(355455L)
                .brandId(1)
                .priceList(1)
                .priority(1)
                .startDate(LocalDateTime.parse("2020-06-14 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .endDate(LocalDateTime.parse("2020-12-31 23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .totalPrice(new BigDecimal(35.50))
                .build();
        expectedList.add(expectedPrice);
        doReturn(expectedList).when(priceRepositoryPort).findPriceByDate(any(Long.class), any(Integer.class), any(LocalDateTime.class));
        Price currentPrice = priceService.getPriceByDate(expectedPrice.getProductId(), expectedPrice.getBrandId(), LocalDateTime.parse("2020-06-14 10:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        Assertions.assertEquals(currentPrice.getTotalPrice(), expectedPrice.getTotalPrice());
    }

    @Test
    public void findPriceByDate_Not_Found() {
        doThrow(PriceException.notFound()).when(priceRepositoryPort).findPriceByDate(any(Long.class), any(Integer.class), any(LocalDateTime.class));
        PriceException thrown = Assertions.assertThrows(
                PriceException.class,
                () -> priceService.getPriceByDate(1L, 1, LocalDateTime.parse("2020-06-14 10:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))),
                "Expected doThing() to throw, but it didn't"
        );
        Integer expectedErrorCode = 100;
        Integer currenErrorCode = thrown.getError().getErrorCode();
        Assertions.assertEquals(expectedErrorCode, currenErrorCode);
    }

    @Test
    public void findPriceByDate_Found_Return_Highest_Priority() {
        ArrayList<Price> expectedList = new ArrayList<>();
        Price expectedPriceHigh = Price.builder()
                .productId(355455L)
                .brandId(1)
                .priceList(2)
                .priority(1)
                .startDate(LocalDateTime.parse("2020-06-15 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .endDate(LocalDateTime.parse("2020-06-16 23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .totalPrice(new BigDecimal(45.50))
                .build();
        Price expectedPriceLow = Price.builder()
                .productId(355455L)
                .brandId(1)
                .priceList(1)
                .priority(0)
                .startDate(LocalDateTime.parse("2020-06-14 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .endDate(LocalDateTime.parse("2020-12-31 23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .totalPrice(new BigDecimal(35.50))
                .build();
        expectedList.add(expectedPriceHigh);
        expectedList.add(expectedPriceLow);
        doReturn(expectedList).when(priceRepositoryPort).findPriceByDate(any(Long.class), any(Integer.class), any(LocalDateTime.class));
        Price currentPrice = priceService.getPriceByDate(expectedPriceHigh.getProductId(), expectedPriceHigh.getBrandId(), LocalDateTime.parse("2020-06-14 10:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        Assertions.assertEquals(currentPrice.getTotalPrice(), expectedPriceHigh.getTotalPrice());
    }
}