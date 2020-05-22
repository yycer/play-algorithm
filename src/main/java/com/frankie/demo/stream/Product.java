package com.frankie.demo.stream;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author: Yao Frankie
 * @date: 2020/5/22 11:27
 */
@Setter
@Getter
public class Product {

    private String name;
    private LocalDateTime buyTime;
    private String amount;

    public Product(String name, LocalDateTime buyTime, String amount) {
        this.name = name;
        this.buyTime = buyTime;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", buyTime=" + buyTime +
                ", amount='" + amount + '\'' +
                '}';
    }
}
