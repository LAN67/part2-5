package ru.verification;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import ru.request.RequestAccount;

// @Service ?? '@Autowired' not applicable to local variable
public class AccountVerification {
    public ResponseEntity<String> verification(RequestAccount acc){
        if(acc.instanceId == null)
            return createResponse("instanceId");
        if(acc.registryTypeCode == null)
            return createResponse("registryTypeCode");
        return null;
    }

    private ResponseEntity<String> createResponse(String name){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.TEXT_PLAIN)
                .body("Имя обязательного параметра " + name + " не заполнено.");
    }
}