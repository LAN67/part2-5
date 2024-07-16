package ru.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "tpp_product_register")
public class TppProductRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//    id serial PRIMARY KEY ,
    @Column(name="product_id")
    private Long productID; //    product_id BIGINT,
    @Column(name="type")
    private String type;//    type VARCHAR(100) NOT NULL,
    @Column(name="account")
    private Long account;//    account BIGINT,
    @Column(name="currency_code")
    private String currencyCode;//    currency_code VARCHAR(30),
    @Column(name="state")
    private String state;//    state VARCHAR(50),
    @Column(name="account_number")
    private String accountNumber;//    account_number VARCHAR(25)

    public TppProductRegister() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TppProductRegister that = (TppProductRegister) o;
        return Objects.equals(productID, that.productID) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productID, type);
    }
}
