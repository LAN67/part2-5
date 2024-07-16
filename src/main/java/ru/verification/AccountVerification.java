package ru.verification;

import ru.request.RequestAccount;

// @Service ?? '@Autowired' not applicable to local variable
public class AccountVerification {
    public String verification(RequestAccount acc){
        if(acc.instanceId == null)
            return "Имя обязательного параметра instanceId не заполнено.";
        if(acc.registryTypeCode == null)
            return "Имя обязательного параметра accountType не заполнено.";
        return null;
    }
}