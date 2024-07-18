package ru.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.dao.AccountDAO;
import ru.request.RequestAccount;
import ru.verification.AccountVerification;


@RestController
public class AccountController {
    @Autowired
    AccountVerification accountVerification;
    @Autowired
    AccountDAO accountDAO;

    @GetMapping("/corporate-settlement-account/create")
    public ResponseEntity<String> create(@RequestBody RequestAccount acc) {
        ResponseEntity<String> response;

        //Шаг 1. Проверка Request.Body на обязательность.
        response = accountVerification.verification(acc);
        if (response != null) {
            return response;
        }

        //Шаг 2. Проверка таблицы ПР (таблица tpp_product_register) на дубли.
        response = accountDAO.check(acc);
        if (response != null) {
            return response;
        }

        //Шаг 3 и 4.
        return accountDAO.create(acc);
    }
}

/*
{
    "instanceId": 1,
    "registryTypeCode": "03.012.002_47533_ComSoLd",
    "accountType": "Клиентский",
    "currencyCode": "800",
    "branchCode": "0022",
    "priorityCode": "00",
    "mdmCode": "15",
    "clientCode": null,
    "trainRegion": null,
    "counter": null,
    "salesCode": null
}
*/
