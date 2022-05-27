package com.WebService.Webservice.project.service.impl;

import com.WebService.Webservice.project.dto.ProductOrderDto;
import com.WebService.Webservice.project.entity.ProductOrder;
import com.WebService.Webservice.project.entity.ProductOrderId;
import com.WebService.Webservice.project.exception.ResourceNotFoundException;
import com.WebService.Webservice.project.repository.ProductOrderRepository;
import com.WebService.Webservice.project.service.ProductOrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service //To enable this class for component scanning
public class ProductOrderServiceImpl implements ProductOrderService {

    private ProductOrderRepository ProductOrderRepository;

    public ProductOrderServiceImpl(ProductOrderRepository ProductOrderRepository) {
        this.ProductOrderRepository = ProductOrderRepository;
    }

    @Override
    public ProductOrderDto createProductOrder(ProductOrderDto ProductOrderDto) {

        // convert DTO to entity
        ProductOrder ProductOrder = mapToEntity(ProductOrderDto);
        ProductOrder newProductOrder = ProductOrderRepository.save(ProductOrder);

        // convert entity to DTO
        ProductOrderDto ProductOrderResponse = mapToDTO(newProductOrder);
        return ProductOrderResponse;
    }

    @Override
    public List<ProductOrderDto> getAllProductOrders() {
        List<ProductOrder> categories = ProductOrderRepository.findAll();
        return categories.stream().map(ProductOrder -> mapToDTO(ProductOrder)).collect(Collectors.toList());
    }

    @Override
    public ProductOrderDto getProductOrderById(ProductOrderId id) {
        ProductOrder ProductOrder = ProductOrderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ProductOrder", "id", id));
        return mapToDTO(ProductOrder);
    }

    @Override
    public ProductOrderDto updateProductOrder(ProductOrderDto ProductOrderDto, ProductOrderId id) {
        // get ProductOrder by id from the database
        ProductOrder ProductOrder = ProductOrderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ProductOrder", "id", id));
        ProductOrder.setId(ProductOrderDto.getId());
        ProductOrder.setQuantity(ProductOrderDto.getQuantity());
        ProductOrder.setPrice(ProductOrderDto.getPrice());
        ProductOrder.setVat(ProductOrderDto.getVat());
        ProductOrder updatedProductOrder = ProductOrderRepository.save(ProductOrder);
        return mapToDTO(updatedProductOrder);
    }

    @Override
    public void deleteProductOrderById(ProductOrderId id) {
        // get ProductOrder by id from the database
        ProductOrder ProductOrder = ProductOrderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ProductOrder", "id", id));
        ProductOrderRepository.delete(ProductOrder);
    }

    // convert Entity into DTO
    private ProductOrderDto mapToDTO(ProductOrder ProductOrder) {

        ProductOrderDto ProductOrderDto = new ProductOrderDto();
        ProductOrderDto.setId(ProductOrder.getId());
        ProductOrderDto.setQuantity(ProductOrder.getQuantity());
        ProductOrderDto.setPrice(ProductOrder.getPrice());
        ProductOrderDto.setVat(ProductOrder.getVat());

        return ProductOrderDto;
    }

    // convert DTO to entity
    private ProductOrder mapToEntity(ProductOrderDto ProductOrderDto) {
        ProductOrder ProductOrder = new ProductOrder();
        ProductOrder.setId(ProductOrderDto.getId());
        ProductOrder.setQuantity(ProductOrderDto.getQuantity());
        ProductOrder.setPrice(ProductOrderDto.getPrice());
        ProductOrder.setVat(ProductOrderDto.getVat());
        return ProductOrder;
    }
}
