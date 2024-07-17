package ru.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "agreement")
public class Agreement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //    id serial PRIMARY KEY,
    @Column(name="product_id")
    private Long productID; //    product_id integer,
    @Column(name="general_agreement_id")
    private String generalAgreementID; //    general_agreement_id VARCHAR(50),
    @Column(name="supplementary_agreement_id")
    private String supplementaryAgreementID; //    supplementary_agreement_id VARCHAR(50),
    @Column(name="arrangement_type")
    private String arrangementType; //    arrangement_type VARCHAR(50),
    @Column(name="sheduler_job_id")
    private Long shedulerJobID; //    sheduler_job_id BIGINT,
    @Column
    private String number; //    number VARCHAR(50),
    @Column(name="opening_date")
    private Date openingDate; //    opening_date TIMESTAMP,
    @Column(name="closing_date")
    private Date closingDate; //    closing_date TIMESTAMP,
    @Column(name="cancel_date")
    private Date cancelDate; //    cancel_date TIMESTAMP,
    @Column(name="validity_duration")
    private Long validityDuration; //    validity_duration BIGINT,
    @Column(name="cancellation_reason")
    private String cancellationReason; //    cancellation_reason VARCHAR(100),
    @Column
    private String status; //    status VARCHAR(50),
    @Column(name="interest_calculation_date")
    private Date interestCalculationDate; //    interest_calculation_date TIMESTAMP,
    @Column(name="interest_rate")
    private Float interestRate; //    interest_rate DECIMAL,
    @Column
    private Float coefficient; //    coefficient DECIMAL,
    @Column(name="coefficient_action")
    private String coefficientAction; //    coefficient_action VARCHAR(50),
    @Column(name="minimum_interest_rate")
    private Float minimumInterestRate; //    minimum_interest_rate DECIMAL,
    @Column(name="minimum_interest_rate_coefficient")
    private Float minimumInterestRateCoefficient; //    minimum_interest_rate_coefficient DECIMAL,
    @Column(name="minimum_interest_rate_coefficient_action")
    private String minimumInterestRateCoefficientAction; //    minimum_interest_rate_coefficient_action VARCHAR(50),
    @Column(name="maximal_interest_rate")
    private Float maximalInterestRate; //    maximal_interest_rate DECIMAL,
    @Column(name="maximal_interest_rate_coefficient")
    private Float maximalInterestRateCoefficient; //    maximal_interest_rate_coefficient DECIMAL,
    @Column(name="maximal_interest_rate_coefficient_action")
    private String maximalInterestRateCoefficientAction; //    maximal_interest_rate_coefficient_action VARCHAR(50)

}
