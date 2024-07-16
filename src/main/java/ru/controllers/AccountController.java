package ru.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.dao.CheckProductReqister;
import ru.dao.AccountDAO;
import ru.models.TppRefProductRegisterType;
import ru.request.RequestAccount;
import ru.verification.AccountVerification;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AccountController {

    @GetMapping("/corporate-settlement-account/create")
    public ResponseEntity<String> create(@RequestBody RequestAccount acc) {
        ResponseEntity<String> response;
//        @Autowired ??? '@Autowired' not applicable to local variable
        AccountVerification accountVerification = new AccountVerification();
//        @Autowired ??? '@Autowired' not applicable to local variable
        CheckProductReqister checkProductReqister = new CheckProductReqister();
        AccountDAO accountDAO = new AccountDAO();

        //Шаг 1. Проверка Request.Body на обязательность.
        response = accountVerification.verification(acc);
        if (response != null) {
            return response;
        }

        //Шаг 2. Проверка таблицы ПР (таблица tpp_product_register) на дубли.
        response = checkProductReqister.check(acc);
        if (response != null) {
            return response;
        }

        //Шаг 3 и 4.
        return accountDAO.create(acc);
    }
}

/*

{
    "instanceId": null,
    "registryTypeCode": null,
    "accountType": null,
    "currencyCode": null,
    "branchCode": null,
    "priorityCode": null,
    "mdmCode": null,
    "clientCode": null,
    "trainRegion": null,
    "counter": null,
    "salesCode": null
}

 */
