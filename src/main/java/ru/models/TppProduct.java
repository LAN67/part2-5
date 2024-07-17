package ru.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "tpp_product")
public class TppProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //id serial PRIMARY KEY,
    @Column(name="product_code_id")
    private Long productCodeID; //product_code_id BIGINT,
    @Column(name="client_id")
    private Long clientID; //client_id BIGINT,
    @Column
    private String type; //type VARCHAR(50),
    @Column
    private String number; //number VARCHAR(50),
    @Column
    private Long priority; //priority BIGINT,
    @Column(name="date_of_conclusion")
    private Date dateOfConclusion; //date_of_conclusion TIMESTAMP,
    @Column(name="start_date_time")
    private Date startDateTime; //start_date_time TIMESTAMP,
    @Column(name="end_date_time")
    private Date endDateTime; //end_date_time TIMESTAMP,
    @Column
    private Long days; //days BIGINT,
    @Column(name="penalty_rate")
    private Float penaltyRate; //penalty_rate DECIMAL,
    @Column
    private Float nso; //nso DECIMAL,
    @Column(name="threshold_amount")
    private Float thresholdAmount; //threshold_amount DECIMAL,
    @Column(name="requisite_type")
    private String requisiteType; //requisite_type VARCHAR(50),
    @Column(name="interest_rate_type")
    private String interestRateType; //interest_rate_type VARCHAR(50),
    @Column(name="tax_rate")
    private Float taxRate; //tax_rate DECIMAL,
    @Column(name="reasone_close")
    private String reasoneClose; //reasone_close VARCHAR(100),
    @Column
    private String state; //state VARCHAR(50)

}
