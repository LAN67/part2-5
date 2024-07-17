package ru.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "tpp_product_register")
public class TppProductRegister {

    //0, Закрыт/1, Открыт/2, Зарезервирован/3, Удалён
    public enum State {
        CLOSE,
        OPEN,
        RESERVED,
        DELETED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//    id serial PRIMARY KEY ,
    @Column(name="product_id")
    private Long productID; //    product_id BIGINT,
    @Column(nullable = false)
    private String type;//    type VARCHAR(100) NOT NULL,
    @Column
    private Long account;//    account BIGINT,
    @Column(name="currency_code")
    private String currencyCode;//    currency_code VARCHAR(30),
    @Column
    private State state;//    state VARCHAR(50),
    @Column(name="account_number")
    private String accountNumber;//    account_number VARCHAR(25)
}
