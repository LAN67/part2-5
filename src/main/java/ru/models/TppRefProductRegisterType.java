package ru.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@Entity
@Table(name = "tpp_ref_product_register_type")
public class TppRefProductRegisterType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "internal_id")
    private Long internalID; //    internal_id serial PRIMARY KEY ,
    @Column(nullable = false)
    private String value; //    value VARCHAR(100) UNIQUE NOT NULL,
    @Column(name = "register_type_name", nullable = false)
    private String registerTypeName;//    register_type_name VARCHAR(100) NOT NULL,
//    @Column(name = "product_class_code", nullable = false)
//    private String productClassCode;//    product_class_code VARCHAR(100) NOT NULL,
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_class_code", referencedColumnName = "value")
    private TppRefProductClass tppRefProductClass;
    @Column(name = "register_type_start_date")
    private Date registerTypeStartDate;//    register_type_start_date TIMESTAMP,
    @Column(name = "register_type_end_date")
    private Date registerTypeEndDate;//    register_type_end_date TIMESTAMP,
    @Column(name = "account_type")
    private String accountType;//    account_type VARCHAR(100)
}
