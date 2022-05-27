package com.WebService.Webservice.project.service;

import com.WebService.Webservice.project.dto.StockDto;
import com.WebService.Webservice.project.dto.StockDto;

import java.util.List;


public interface StockService {
    StockDto createStock(StockDto StockDto);

    List<StockDto> getAllStocks();

    StockDto getStockById(long id);

    StockDto updateStock(StockDto StockDto, long id);

    void deleteStockById(long id);
}
