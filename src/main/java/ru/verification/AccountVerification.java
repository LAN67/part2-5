package ru.verification;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.request.RequestAccount;

@Service
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
                .header("Accept-Language:\"ru-ru,ru\"")
                .body("Имя обязательного параметра " + name + " не заполнено.");
    }
}