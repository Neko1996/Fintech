package com.example.fintech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class FintechApplication {

    public static void main(String[] args) {
        SpringApplication.run(FintechApplication.class, args);
    }


//    CommandLineRunner commandLineRunner(
//            StockRepository stockRepository) {
//            return args -> {
//                List<Stock> stocks = Arrays.asList(
//                        new Stock("2021-8-11",
//                                "arm",
//                                12.22,
//                                12.22,
//                                12.22,
//                                12.22),
//                        new Stock("2021-11-11",
//                                "arm2",
//                                12.22,
//                                12.22,
//                                12.22,
//                                12.22)
//                );
//
//                List<Stock> saveStocks = stockRepository.saveAll(stocks);
//            };
//    }

}
