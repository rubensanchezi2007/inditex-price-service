package com.capitole.inditex.integration;
import com.capitole.inditex.domain.GetPriceResponse;
import com.capitole.inditex.price.domain.model.PriceError;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
@AutoConfigureMockMvc
public class PriceControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGetFoundPricebyDate_testCase1() throws Exception {
        MvcResult currentResponse = mockMvc.perform(MockMvcRequestBuilders.get("/api/price")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .param("date", "2020-06-14 10:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new JavaTimeModule());
        GetPriceResponse getPriceResponse = objectMapper.readValue(currentResponse.getResponse().getContentAsString(), GetPriceResponse.class);
        Assertions.assertEquals(getPriceResponse.getTotalPrice().doubleValue(), 35.50);
    }

    @Test
    public void testGetFoundPricebyDate_testCase2() throws Exception {
        MvcResult currentResponse = mockMvc.perform(MockMvcRequestBuilders.get("/api/price")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .param("date", "2020-06-14 16:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new JavaTimeModule());
        GetPriceResponse getPriceResponse = objectMapper.readValue(currentResponse.getResponse().getContentAsString(), GetPriceResponse.class);
        Assertions.assertEquals(getPriceResponse.getTotalPrice().doubleValue(), 25.45);
    }

    @Test
    public void testGetFoundPricebyDate_testCase3() throws Exception {
        MvcResult currentResponse = mockMvc.perform(MockMvcRequestBuilders.get("/api/price")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .param("date", "2020-06-14 21:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new JavaTimeModule());
        GetPriceResponse getPriceResponse = objectMapper.readValue(currentResponse.getResponse().getContentAsString(), GetPriceResponse.class);
        Assertions.assertEquals(getPriceResponse.getTotalPrice().doubleValue(), 35.50);
    }

    @Test
    public void testGetFoundPricebyDate_testCase4() throws Exception {
        MvcResult currentResponse = mockMvc.perform(MockMvcRequestBuilders.get("/api/price")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .param("date", "2020-06-15 10:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new JavaTimeModule());
        GetPriceResponse getPriceResponse = objectMapper.readValue(currentResponse.getResponse().getContentAsString(), GetPriceResponse.class);
        Assertions.assertEquals(getPriceResponse.getTotalPrice().doubleValue(), 30.50);
    }

    @Test
    public void testGetFoundPricebyDate_testCase5() throws Exception {
        MvcResult currentResponse = mockMvc.perform(MockMvcRequestBuilders.get("/api/price")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .param("date", "2020-06-16 21:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new JavaTimeModule());
        GetPriceResponse getPriceResponse = objectMapper.readValue(currentResponse.getResponse().getContentAsString(), GetPriceResponse.class);
        Assertions.assertEquals(getPriceResponse.getTotalPrice().doubleValue(), 38.95);
    }

    @Test
    public void testGetNot_Found_PricebyDate() throws Exception {
        MvcResult currentResponse = mockMvc.perform(MockMvcRequestBuilders.get("/api/price")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .param("date", "2025-06-14 10:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        ObjectMapper objectMapper = new ObjectMapper();
        PriceError priceError = objectMapper.readValue(currentResponse.getResponse().getContentAsString(), PriceError.class);
        Assertions.assertEquals(priceError.getErrorCode(), 100);
    }
}
