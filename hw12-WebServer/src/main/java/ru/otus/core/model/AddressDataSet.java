package ru.otus.core.model;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class AddressDataSet {


    @Id
    @Column(name = "street")
    private String street;

    public AddressDataSet() {
    }

    public AddressDataSet(String street) {
        this.street = street;
    }
}
