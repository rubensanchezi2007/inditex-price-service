package com.capitole.inditex.e2e;
import com.capitole.inditex.domain.GetPriceResponse;
import com.capitole.inditex.price.infrastructure.outbound.repository.PriceJPARepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.MountableFile;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@Testcontainers
class PriceControllerE2ETest {
    @Autowired
    WebTestClient webTestClient;
    @Autowired
    private PriceJPARepository priceJPARepository;
    public static MySQLContainer<?> MY_SQL_CONTAINER = new MySQLContainer<>("mysql:9.2.0");
    static {
        MY_SQL_CONTAINER
                .withDatabaseName("testDB")
                .withCopyFileToContainer(MountableFile.forClasspathResource("mySQLData.sql"), "/docker-entrypoint-initdb.d/schema.sql");
        MY_SQL_CONTAINER.start();
    }
    @DynamicPropertySource
    static void configureTestProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", () -> MY_SQL_CONTAINER.getJdbcUrl());
        registry.add("spring.datasource.username", () -> MY_SQL_CONTAINER.getUsername());
        registry.add("spring.datasource.password", () -> MY_SQL_CONTAINER.getPassword());
    }

    @Test
    void getPriceTestCase1() {
        webTestClient.get()
                .uri("/api/price?productId=35455&brandId=1&date=2020-06-14 10:00:00")
                .exchange()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(GetPriceResponse.class)
                .consumeWith(listOfObject -> {
                    var list = listOfObject.getResponseBody();
                    Assertions.assertTrue(list.size() == 1);
                    Assertions.assertTrue(list.get(0).getTotalPrice().doubleValue() == 35.50);
                });
    }
}
