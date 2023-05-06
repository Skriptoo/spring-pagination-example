package com.example.demo.service;

import com.example.demo.model.Water;
import com.example.demo.repository.WaterRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;


@Service
public class WaterService {
    @Autowired
    private WaterRepository repository;


    @PostConstruct
    public void initDB() {
        List<Water> waterList = IntStream.rangeClosed(1, 50).mapToObj(i -> new Water(i, "water"+i, (double)Math.round(new Random().nextDouble(10)*100)/100)).toList();

        repository.saveAll(waterList);
    }

    public List<Water> findAllWaters() {
        return repository.findAll();
    }
    public List<Water> findWatersWithSorting(String field){
        return repository.findAll(Sort.by(Sort.Direction.DESC,field));
    }
    public Page<Water> findWatersWithPagination(int offset, int pageSize){
        Page<Water> waters =  repository.findAll(PageRequest.of(offset, pageSize));
        return waters;
    }
    public Page<Water> findWatersWithPaginationAndSorting(int offset, int pageSize, String field){
        Page<Water> waters =  repository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(Sort.Direction.DESC,field)));
        return waters;
    }
}
