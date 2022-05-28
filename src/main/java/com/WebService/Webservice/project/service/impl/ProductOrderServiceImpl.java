package com.WebService.Webservice.project.service.impl;

import com.WebService.Webservice.project.dto.ProductOrderDto;
import com.WebService.Webservice.project.entity.Order;
import com.WebService.Webservice.project.entity.ProductOrder;
import com.WebService.Webservice.project.entity.ProductOrderId;
import com.WebService.Webservice.project.exception.ResourceNotFoundException;
import com.WebService.Webservice.project.repository.ProductOrderRepository;
import com.WebService.Webservice.project.service.ProductOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service //To enable this class for component scanning
public class ProductOrderServiceImpl implements ProductOrderService {

    private ProductOrderRepository ProductOrderRepository;
    private ModelMapper mapper;
    public ProductOrderServiceImpl(ProductOrderRepository ProductOrderRepository, ModelMapper mapper) {
        this.ProductOrderRepository = ProductOrderRepository;
        this.mapper=mapper;
    }

    @Override
    public ProductOrderDto createProductOrder(ProductOrderDto ProductOrderDto) {
        try {


            // convert DTO to entity
            ProductOrder ProductOrder = mapToEntity(ProductOrderDto);
            ProductOrder newProductOrder = ProductOrderRepository.save(ProductOrder);

            // convert entity to DTO
            ProductOrderDto ProductOrderResponse = mapToDTO(newProductOrder);
            return ProductOrderResponse;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<ProductOrderDto> getAllProductOrders() {
        try {
            List<ProductOrder> categories = ProductOrderRepository.findAll();
            return categories.stream().map(ProductOrder -> mapToDTO(ProductOrder)).collect(Collectors.toList());
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ProductOrderDto getProductOrderById(ProductOrderId id) {
        try {
            ProductOrder ProductOrder = ProductOrderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ProductOrder", "id", id));
            return mapToDTO(ProductOrder);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ProductOrderDto updateProductOrder(ProductOrderDto ProductOrderDto, ProductOrderId id) {
        try {
            // get ProductOrder by id from the database
            ProductOrder ProductOrder = ProductOrderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ProductOrder", "id", id));
            ProductOrder.setId(ProductOrderDto.getId());
            ProductOrder.setQuantity(ProductOrderDto.getQuantity());
            ProductOrder.setPrice(ProductOrderDto.getPrice());
            ProductOrder.setVat(ProductOrderDto.getVat());
            ProductOrder updatedProductOrder = ProductOrderRepository.save(ProductOrder);
            return mapToDTO(updatedProductOrder);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void deleteProductOrderById(ProductOrderId id) {
        try {
            // get ProductOrder by id from the database
            ProductOrder ProductOrder = ProductOrderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ProductOrder", "id", id));
            ProductOrderRepository.delete(ProductOrder);
        } catch (Exception e) {
            throw e;
        }
    }

    // convert Entity into DTO
    private ProductOrderDto mapToDTO(ProductOrder ProductOrder) {
        try {

            ProductOrderDto ProductOrderDto = mapper.map(ProductOrder, ProductOrderDto.class);

            return ProductOrderDto;
        } catch (Exception e) {
            throw e;
        }
    }

    // convert DTO to entity
    private ProductOrder mapToEntity(ProductOrderDto ProductOrderDto) {
        try {
            ProductOrder ProductOrder = mapper.map(ProductOrderDto, ProductOrder.class);

            return ProductOrder;
        } catch (Exception e) {
            throw e;
        }
    }
}
