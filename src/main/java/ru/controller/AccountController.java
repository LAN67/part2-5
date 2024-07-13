package ru.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.request.RequestAccount;

@RestController
public class AccountController {

    @GetMapping("/corporate-settlement-account/create")
    public RequestAccount create( RequestAccount acc) {
        //@RequestBody
        return acc;

    }
}
/**
 * {
 *     "instanceId": null,
 *     "registryTypeCode": null,
 *     "accountType": null,
 *     "currencyCode": null,
 *     "branchCode": null,
 *     "priorityCode": null,
 *     "mdmCode": null,
 *     "clientCode": null,
 *     "trainRegion": null,
 *     "counter": null,
 *     "salesCode": null
 * }
 */