package com.WebService.Webservice.project.service;

import com.WebService.Webservice.project.dto.StockDto;

import java.util.List;


public interface StockService {
    StockDto createStock(StockDto StockDto) throws Exception;

    List<StockDto> getAllStocks();

    StockDto getStockById(int id);

    StockDto updateStock(StockDto StockDto, int id);

    void deleteStockById(int id);

    StockDto getStockByProductId(int productId);
}
