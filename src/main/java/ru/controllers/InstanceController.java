package ru.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.dao.InstanceDAO;
import ru.request.RequestInstance;
import ru.verification.InstanceVerification;



@RestController
public class InstanceController {
    @Autowired
    InstanceVerification instanceVerification;
    @Autowired
    InstanceDAO instanceDAO;


    @GetMapping("/corporate-settlement-instance/create")
    public ResponseEntity<String> create(@RequestBody RequestInstance inst) {
        ResponseEntity<String> response;

        //Шаг 1. Проверка Request.Body на обязательность.
        response = instanceVerification.verification(inst);
        if (response != null) {
            return response;
        }
        //Шаг 2. Если ИД ЭП в поле Request.Body.instanceId не задано (NULL/Пусто), то выполняется процесс создания нового экземпляра
        response = instanceDAO.createProduct(inst);
        if (response != null) {
            return response;
        }



        return null;
    }
}

/*
"contractDate": "2024-07-17T10:19:42.148+00:00",
{
    "instanceId": 1,
    "productType": 1,
    "productCode": "03.012.002",
    "registerType": 1,
    "mdmCode": "15",
    "contractNumber": "number001",
    "contractDate": "2024-07-17",
    "priority": 1,
    "interestRatePenalty": null,
    "minimalBalance": null,
    "thresholdAmount": null,
    "accountingDetails": null,
    "rateType": null,
    "taxPercentageRate": null,
    "technicalOverdraftLimitAmount": null,
    "contractId": 1,
    "branchCode": "0022",
    "isoCurrencyCode": "800",
    "urgencyCode": 1,
    "referenceCode": null,
    "additionalPropertiesVips": [],
    "instanceArrangements": [
        {
            "number": "number001",
            "openingDate": "2024-07-17"
        }
    ]
}
 */