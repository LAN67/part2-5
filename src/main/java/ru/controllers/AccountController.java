package ru.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.dao.CheckProductReqister;
import ru.request.RequestAccount;
import ru.verification.AccountVerification;

@RestController
public class AccountController {

    @GetMapping("/corporate-settlement-account/create")
    public ResponseEntity<String> create(@RequestBody RequestAccount acc) {
//        @Autowired ??? '@Autowired' not applicable to local variable
        AccountVerification accountVerification = new AccountVerification();
//        @Autowired ??? '@Autowired' not applicable to local variable
        CheckProductReqister checkProductReqister = new CheckProductReqister();
        String str;

        //Шаг 1. Проверка Request.Body на обязательность.
        str = accountVerification.verification(acc);
        if (str != null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(str);
        }

        //Шаг 2. Проверка таблицы ПР (таблица tpp_product_register) на дубли.
        str = checkProductReqister.checkProductReqister(acc);
        if (str != null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(str);
        }





        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\n" +
                        "\"data\": {\n" +
                        "\"accountId\": \"string\"\n" +
                        "}\n" +
                        "}\n");
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
