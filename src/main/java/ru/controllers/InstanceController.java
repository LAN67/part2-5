package ru.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.request.RequestInstance;

@RestController
public class InstanceController {


    @GetMapping("/corporate-settlement-instance/create")
    public RequestInstance create(@RequestBody RequestInstance inst) {
        //@RequestBody
        return inst;

    }
}

/*

{
    "instanceId": null,
    "productType": null,
    "productCode": null,
    "registerType": null,
    "mdmCode": null,
    "contractNumber": null,
    "contractDate": null,
    "priority": null,
    "interestRatePenalty": null,
    "minimalBalance": null,
    "thresholdAmount": null,
    "accountingDetails": null,
    "rateType": null,
    "taxPercentageRate": null,
    "technicalOverdraftLimitAmount": null,
    "contractId": null,
    "branchCode": null,
    "isoCurrencyCode": null,
    "urgencyCode": null,
    "referenceCode": null,
    "additionalPropertiesVips": [],
    "instanceArrangements": []
}

 */