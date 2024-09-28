package com.example.FoodApp.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    private ObjectId id;

    @DBRef
    private List<OrderItem> items = new ArrayList<>();

    private User user;

    @JsonIgnore
    private Restaurant restaurant;
    private Long totalAmount;
    private String orderStatus;
    private Date createdAt;
    private Address deliveryAddress;

    private int totalItems;
//    private Payment

}
