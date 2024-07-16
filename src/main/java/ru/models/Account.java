package ru.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // id serial PRIMARY KEY,
//    @Column(name="account_pool_id")
//    private Integer accountPoolID; // account_pool_id integer,
    @Column(name="account_number")
    private String accountNumber; // account_number VARCHAR(25),`
    @Column
    private Boolean bussy;// bussy BOOLEAN
    @OneToOne(fetch = FetchType.EAGER, optional = false)
//    @JoinColumn(name="account_pool_id", nullable = false)
    @JoinColumn(name = "account_pool_id", referencedColumnName = "id")
    private AccountPool accountPool;

}
