package ru.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tpp_ref_product_class")
public class TppRefProductClass {
    @Id
    @Column(name = "internal_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long internalID; //    internal_id serial PRIMARY KEY ,
    @Column
    private String value; //    value VARCHAR(100) UNIQUE NOT NULL,
    @OneToMany(mappedBy = "tppRefProductClass", fetch = FetchType.LAZY)
    private List<TppRefProductRegisterType> tppRefProductRegisterType;
    @Column(name = "gbi_code")
    private String gbiCode; //    gbi_code VARCHAR(50),
    @Column(name = "gbi_name")
    private String gbiName; //    gbi_name VARCHAR(100),
    @Column(name = "product_row_code")
    private String productRowCode; //    product_row_code VARCHAR(50),
    @Column(name = "product_row_name")
    private String productRowName; //    product_row_name VARCHAR(100),
    @Column(name = "subclass_code")
    private String subclassCode; //    subclass_code VARCHAR(50),
    @Column(name = "subclass_name")
    private String subclassName; //    subclass_name VARCHAR(100)

}
