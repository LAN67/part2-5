package ru.verification;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import ru.request.RequestAccount;

// @Service ?? '@Autowired' not applicable to local variable
public class AccountVerification {
    public ResponseEntity<String> verification(RequestAccount acc){
        if(acc.instanceId == null)
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body("Имя обязательного параметра instanceId не заполнено.");
        if(acc.registryTypeCode == null)
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body("Имя обязательного параметра accountType не заполнено.");
        return null;
    }
}