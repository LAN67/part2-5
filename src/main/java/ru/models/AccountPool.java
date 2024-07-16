package ru.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "account_pool")
public class AccountPool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //id serial PRIMARY KEY,
    @Column(name = "branch_code")
    private String branchCode; //branch_code VARCHAR(50),
    @Column(name = "currency_code")
    private String currencyCode; //currency_code VARCHAR(30),
    @Column(name = "mdm_code")
    private String mdmCode; //mdm_code VARCHAR(50),
    @Column(name = "priority_code")
    private String priorityCode; //priority_code VARCHAR(30),
    @Column(name = "registry_type_code")
    private String registryTypeCode; //registry_type_code VARCHAR(50)
    @OneToMany(mappedBy = "accountPool", fetch=FetchType.EAGER)
    //@JoinColumn(name = "account_pool_id")
    private List<Account> account;

}
