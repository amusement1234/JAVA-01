package com.example.homwork.service;

public interface StockService {
    void reduce(Integer productId);
    void init(Integer productId,Integer stockQty);
}
