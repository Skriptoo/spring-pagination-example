package com.example.demo.controller;


import com.example.demo.dto.ApiResponseDTO;
import com.example.demo.model.Water;
import com.example.demo.service.WaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/waters")
public class WaterController {
    @Autowired
    private WaterService waterService;

    @GetMapping
    private ApiResponseDTO<List<Water>> getWaters(){
        List<Water> allWaters = waterService.findAllWaters();
        return new ApiResponseDTO<>(allWaters.size(), allWaters);
    }
    @GetMapping("/{field}")
    private ApiResponseDTO<List<Water>> getWatersWithSort(@PathVariable String field){
        List<Water> allWaters = waterService.findWatersWithSorting(field);
        return new ApiResponseDTO<>(allWaters.size(), allWaters);
    }
    @GetMapping("/pagination/{offset}/{pageSize}")
    private ApiResponseDTO<Page<Water>> getWatersWithPagination(@PathVariable int offset, @PathVariable int pageSize){
       Page<Water> allWatersWithPagination = waterService.findWatersWithPagination(offset, pageSize);
        return new ApiResponseDTO<>(allWatersWithPagination.getSize(), allWatersWithPagination);
    }
    @GetMapping("/pagination/{offset}/{pageSize}/{field}")
    private ApiResponseDTO<Page<Water>> getWatersWithPaginationAndSort(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field){
        Page<Water> allWatersWithPagination = waterService.findWatersWithPaginationAndSorting(offset, pageSize, field);
        return new ApiResponseDTO<>(allWatersWithPagination.getSize(), allWatersWithPagination);
    }

}
