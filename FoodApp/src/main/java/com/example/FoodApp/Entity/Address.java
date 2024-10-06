package com.example.FoodApp.Entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Address {
    private ObjectId id;
    private String street;
    private String city;
    private String postalCode;
    private String country;
    private String state;

}
