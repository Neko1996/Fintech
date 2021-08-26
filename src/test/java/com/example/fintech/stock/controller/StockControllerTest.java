package com.example.fintech.stock.controller;

import com.example.fintech.stock.model.Stock;
import com.example.fintech.stock.repository.StockRepository;
import com.example.fintech.stock.service.StockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@ExtendWith(SpringExtension.class)
@WebMvcTest(StockController.class)
class StockControllerTest {

    @MockBean
    StockService stockService;

    @MockBean
    StockRepository stockRepository;

    @Autowired
    MockMvc mockMvc;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Long latest_id= Long.valueOf(1);

    @BeforeEach
    void get_latest_id() throws ParseException {
        Date date = simpleDateFormat.parse("2021-08-25");
        Stock stock0=new Stock(date,21.25,23.56,20.31,20.73,20.73,Long.valueOf(1200),"Today");
        stockRepository.save(stock0);
//        List<Stock> indexs=stockRepository.findAll();
//        latest_id=indexs.get(0).getId();
//        System.out.println("latest_id_is:"+latest_id);
    }




//    stocks.add(stock1);

    StockControllerTest() throws ParseException {
    }

    @Test
    void getStockByDate() {
    }

    @Test
    void getStockByDateInterval() {
    }

    @Test
    void getStockByName() {
//        when(stockService.findByName("Today")).thenReturn(defaultUsers.get(0));
//
//        mockMvc.perform(
//                        MockMvcRequestBuilders.get("/api/v1/users/1"))
//                .andExpect(status().isOk());
    }

    @Test
    void getStockByYear() {
    }

    @Test
    void getStockDiffAdjclose() {
    }

    @Test
    void addStock() {
    }
}