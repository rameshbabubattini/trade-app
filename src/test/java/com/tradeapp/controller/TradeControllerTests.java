package com.tradeapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradeapp.constants.TradeConstants;
import com.tradeapp.dao.TradeDao;
import com.tradeapp.dto.TradeDTO;
import com.tradeapp.entity.Trade;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class TradeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TradeDao tradeDao;

    @Test
    public void testSendTrade_NewTrade_ShouldInsertIntoDB() throws Exception {
        log.info("testSendTrade_NewTrade_ShouldInsertIntoDB..");

        TradeDTO tradeDTO = TradeDTO.builder()
                .tradeId("T6")
                .counterPartyId("CP-6")
                .bookId("B6")
                .version(1)
                .maturityDate(LocalDate.parse("20/10/2022", DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .build();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/sendTrade")
                .content(asJsonString(tradeDTO))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.TEXT_PLAIN_VALUE);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals("Trade processed successfully", response.getContentAsString());

        // Fetching newly inserted trade from db and asserting all fields
        Optional<Trade> optionalTrade = tradeDao.findById(tradeDTO.getTradeId());
        Assertions.assertTrue(optionalTrade.isPresent());
        Assertions.assertEquals("20/10/2022", DateTimeFormatter.ofPattern(TradeConstants.DATE_FORMAT).format(optionalTrade.get().getMaturityDate()));
        Assertions.assertEquals(1, optionalTrade.get().getVersion());
        Assertions.assertEquals("T6", optionalTrade.get().getTradeId());
        Assertions.assertEquals("N", optionalTrade.get().getExpired());
        Assertions.assertEquals("B6", optionalTrade.get().getBookId());
        Assertions.assertEquals("CP-6", optionalTrade.get().getCounterPartyId());
        Assertions.assertEquals(LocalDate.now(), optionalTrade.get().getCreatedDate());
    }

    @Test
    public void testSendTrade_WithHigherVersionAndLaterMaturityDate_ShouldOverrideExistingTrade() throws Exception {
        log.info("testSendTrade..");

        TradeDTO tradeDTO = TradeDTO.builder()
                .tradeId("T1")
                .counterPartyId("CP-1")
                .bookId("B1")
                .version(2)
                .maturityDate(LocalDate.parse("20/09/2022", DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .build();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/sendTrade")
                .content(asJsonString(tradeDTO))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.TEXT_PLAIN_VALUE);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals("Trade processed successfully", response.getContentAsString());

        Optional<Trade> optionalTrade = tradeDao.findById(tradeDTO.getTradeId());
        Assertions.assertTrue(optionalTrade.isPresent());
        Assertions.assertEquals("20/09/2022", DateTimeFormatter.ofPattern(TradeConstants.DATE_FORMAT).format(optionalTrade.get().getMaturityDate()));
        Assertions.assertEquals(2, optionalTrade.get().getVersion());
    }

    @Test
    public void testSendTrade_WithVersionLessThanExisting_ResponseWithCONFLICTStatusCode() throws Exception {
        log.info("testSendTrade_WithVersionLessThanExisting_ResponseWithCONFLICTStatusCode..");

        TradeDTO tradeDTO = TradeDTO.builder()
                .tradeId("T2")
                .counterPartyId("CP-2")
                .bookId("B2")
                .version(1)
                .maturityDate(LocalDate.parse("20/08/2022", DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .build();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/sendTrade")
                .content(asJsonString(tradeDTO))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.TEXT_PLAIN_VALUE);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        Assertions.assertEquals(HttpStatus.CONFLICT.value(), response.getStatus());
        Assertions.assertEquals("Exception occurred.Version received is lower than the existing version for tradeId: T2", response.getContentAsString());
    }

    @Test
    public void testSendTrade_WithOlderMaturityDate_ShouldIgnoreTheTrade() throws Exception {
        log.info("testSendTrade_WithOlderMaturityDate..");

        TradeDTO tradeDTO = TradeDTO.builder()
                .tradeId("T3")
                .counterPartyId("CP-1")
                .bookId("B1")
                .version(1)
                .maturityDate(LocalDate.parse("20/05/2022", DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .build();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/sendTrade")
                .content(asJsonString(tradeDTO))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.TEXT_PLAIN_VALUE);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals("Trade processed successfully", response.getContentAsString());

        Optional<Trade> optionalTrade = tradeDao.findById(tradeDTO.getTradeId());
        Assertions.assertTrue(optionalTrade.isPresent());
        Assertions.assertEquals("31/08/2022", DateTimeFormatter.ofPattern(TradeConstants.DATE_FORMAT).format(optionalTrade.get().getMaturityDate()));
    }

    private String asJsonString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Error occurred while converting to json.", e);
        }
        return null;
    }
}
