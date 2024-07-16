package ru.dao;

import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import ru.models.Account;
import ru.models.AccountPool;
import ru.models.TppRefProductRegisterType;
import ru.request.RequestAccount;

import java.util.List;

public class AccountDAO {

    @Transactional
    public ResponseEntity<String> create(RequestAccount acc) {
        List<TppRefProductRegisterType> productRegisterType;

        Configuration configuration = new Configuration()
                .addAnnotatedClass(TppRefProductRegisterType.class)
                .addAnnotatedClass(AccountPool.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        try (sessionFactory) {
            Session session = sessionFactory.openSession();
            //Шаг 3. Взять значение из Request.Body.registryTypeCode и найти соответствующие ему
            // записи в tpp_ref_product_register_type.value.
            productRegisterType = session.createQuery(
                            "FROM TppRefProductRegisterType WHERE value='" + acc.registryTypeCode + "'", TppRefProductRegisterType.class)
                    .getResultList();
            if (productRegisterType.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .contentType(MediaType.TEXT_PLAIN)
                        .body("Код Продукта '" + acc.registryTypeCode + "' не найдено в Каталоге продуктов " + configuration.getProperty("hibernate.connection.username") + ".tpp_ref_product_register_type для данного типа Регистра.");
            }
            //Шаг 4. Найти значение номера счета по параметрам branchCode, currencyCode, mdmCode, priorityCode, registryTypeCode
            // из Request.Body в таблице Пулов счетов (account_pool). Номер счета берется первый из пула.
            List<AccountPool> ap = session.createQuery(
                            "FROM AccountPool WHERE branchCode='"+acc.branchCode +"' and currencyCode='"+ acc.currencyCode+"' and mdmCode='"+ acc.mdmCode+"' and priorityCode='"+ acc.priorityCode+"' and registryTypeCode='"+ acc.registryTypeCode+"'", AccountPool.class)
                    .getResultList();
            System.out.println("====================="+ap.size());
//            for(AccountPool one : ap){
//                System.out.println("1===================================");
//                System.out.println(one);
//                for(Account one1 : one.getAccount()){
//                    System.out.println("2===================================");
//                    System.out.println(one1);
//                }
//            }
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
