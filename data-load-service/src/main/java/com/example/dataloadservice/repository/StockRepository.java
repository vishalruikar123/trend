package com.example.dataloadservice.repository;

import com.example.dataloadservice.model.Stock;
import org.springframework.data.repository.CrudRepository;

public interface StockRepository extends CrudRepository<Stock,String> {

}
