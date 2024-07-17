package ru.verification;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.request.InstanceArrangement;
import ru.request.RequestAccount;
import ru.request.RequestInstance;

// @Service  @Autowired ??? '@Autowired' not applicable to local variable
public class InstanceVerification {
    public ResponseEntity<String> verification(RequestInstance inst) {
        if (inst.productType == null)
            return createResponse("productType");
        if (inst.productCode == null)
            return createResponse("productCode");
        if (inst.registerType == null)
            return createResponse("registerType");
        if (inst.mdmCode == null)
            return createResponse("mdmCode");
        if (inst.contractNumber == null)
            return createResponse("contractNumber");
        if (inst.contractDate == null)
            return createResponse("contractDate");
        if (inst.priority == null)
            return createResponse("priority");
        if (inst.contractId == null)
            return createResponse("contractId");
        if (inst.branchCode == null)
            return createResponse("branchCode");
        if (inst.isoCurrencyCode == null)
            return createResponse("isoCurrencyCode");
        if (inst.urgencyCode == null)
            return createResponse("urgencyCode");
        // instanceArrangements
        if (inst.instanceArrangements.isEmpty())
            return createResponse("instanceArrangements");
        for (InstanceArrangement one : inst.instanceArrangements) {
            if (one.number == null)
                return createResponse("instanceArrangement.number");
            if (one.openingDate == null)
                return createResponse("instanceArrangement.openingDate");
        }
        return null;
    }

    private ResponseEntity<String> createResponse(String name) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.TEXT_PLAIN)
                .body("Имя обязательного параметра " + name + " не заполнено.");
    }
}
