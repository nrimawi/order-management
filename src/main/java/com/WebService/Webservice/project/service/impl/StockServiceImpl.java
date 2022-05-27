package com.WebService.Webservice.project.service.impl;


import com.WebService.Webservice.project.dto.StockDto;
import com.WebService.Webservice.project.entity.Product;
import com.WebService.Webservice.project.entity.Stock;
import com.WebService.Webservice.project.exception.ResourceNotFoundException;
import com.WebService.Webservice.project.repository.StockRepository;
import com.WebService.Webservice.project.service.StockService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service //To enable this class for component scanning
public class StockServiceImpl implements StockService {

    private StockRepository StockRepository;

    public StockServiceImpl(StockRepository StockRepository) {
        this.StockRepository = StockRepository;
    }

    @Override
    public StockDto createStock(StockDto StockDto) {
        try {
            // convert DTO to entity
            Stock Stock = mapToEntity(StockDto);
            Stock newStock = StockRepository.save(Stock);

            // convert entity to DTO
            StockDto StockResponse = mapToDTO(newStock);
            return StockResponse;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<StockDto> getAllStocks() {
        try {
            List<Stock> categories = StockRepository.findAll();
            return categories.stream().map(Stock -> mapToDTO(Stock)).collect(Collectors.toList());
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public StockDto getStockById(int id) {
        try {
            Stock Stock = StockRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stock", "id", id));
            return mapToDTO(Stock);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public StockDto updateStock(StockDto StockDto, int id) {
        try {
            // get Stock by id from the database
            Stock Stock = StockRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stock", "id", id));
            Stock.setId(Stock.getId());
            Product product = new Product();
            product.setId(StockDto.getProductId());
            Stock.setProduct(product);
            Stock.setQuantity(Stock.getQuantity());
            Stock.setUpdateAt(Stock.getUpdateAt());

            Stock updatedStock = StockRepository.save(Stock);
            return mapToDTO(updatedStock);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void deleteStockById(int id) {
        try {
            // get Stock by id from the database
            Stock Stock = StockRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stock", "id", id));
            StockRepository.delete(Stock);
        } catch (Exception e) {
            throw e;
        }
    }

    // convert Entity into DTO
    private StockDto mapToDTO(Stock Stock) {
        try {
            StockDto StockDto = new StockDto();
            StockDto.setId(Stock.getId());
            StockDto.setProductId(Stock.getProduct().getId());
            StockDto.setQuantity(Stock.getQuantity());
            StockDto.setUpdateAt(Stock.getUpdateAt());

            return StockDto;
        } catch (Exception e) {
            throw e;
        }
    }

    // convert DTO to entity
    private Stock mapToEntity(StockDto StockDto) {
        try {
            Stock Stock = new Stock();
            Stock.setId(Stock.getId());
            Product product = new Product();
            product.setId(StockDto.getProductId());
            Stock.setProduct(product);
            Stock.setQuantity(Stock.getQuantity());
            Stock.setUpdateAt(Stock.getUpdateAt());
            return Stock;
        } catch (Exception e) {
            throw e;
        }
    }
}
