package ru.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.models.*;
import ru.request.RequestAccount;

import java.util.List;

@Service
public class AccountDAO {
    @Autowired
    FindAccountNumber findAccountNumber;

    public ResponseEntity<String> check(RequestAccount acc) {

        Configuration configuration = new Configuration()
                .addAnnotatedClass(TppProductRegister.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        try (sessionFactory) {
            Session session = sessionFactory.openSession();
            List<TppProductRegister> pr = session.createQuery(
                            "FROM TppProductRegister WHERE productID=" + acc.instanceId + " and type='" + acc.registryTypeCode + "'", TppProductRegister.class)
                    .getResultList();
            session.close();
            if (!pr.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
//                        .contentType(MediaType.TEXT_PLAIN)
                        .header("Accept-Language:\"ru-ru,ru\"")
                        .body("Параметр registryTypeCode тип регистра " + acc.registryTypeCode + " уже существует для ЭП с ИД  " + acc.instanceId + ".");
            }
            return null;
        }
    }

    public ResponseEntity<String> create(RequestAccount acc) {
        List<TppRefProductRegisterType> productRegisterType;
        TppProductRegister tppProductRegister;
        Long accountId = null;

        Configuration configuration = new Configuration()
                .addAnnotatedClass(TppRefProductRegisterType.class)
                .addAnnotatedClass(AccountPool.class)
                .addAnnotatedClass(Account.class)
                .addAnnotatedClass(TppProductRegister.class)
                .addAnnotatedClass(TppRefProductClass.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        try (sessionFactory) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            //Шаг 3. Взять значение из Request.Body.registryTypeCode и найти соответствующие ему
            // записи в tpp_ref_product_register_type.value.
            productRegisterType = session.createQuery(
                            "FROM TppRefProductRegisterType WHERE value='" + acc.registryTypeCode + "'", TppRefProductRegisterType.class)
                    .getResultList();
            if (productRegisterType.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
//                        .contentType(MediaType.TEXT_PLAIN)
                        .header("Accept-Language:\"ru-ru,ru\"")
                        .body("Код Продукта '" + acc.registryTypeCode + "' не найдено в Каталоге продуктов " + configuration.getProperty("hibernate.connection.username") + ".tpp_ref_product_register_type для данного типа Регистра.");
            }
            //Шаг 4. Найти значение номера счета по параметрам branchCode, currencyCode, mdmCode, priorityCode, registryTypeCode
            // из Request.Body в таблице Пулов счетов (account_pool). Номер счета берется первый из пула.
            Account newAcc = findAccountNumber.get(acc.branchCode, acc.currencyCode, acc.mdmCode, acc.priorityCode, acc.registryTypeCode);

            tppProductRegister = new TppProductRegister();

            tppProductRegister.setId(null);

            tppProductRegister.setProductID(acc.instanceId);

            String strTemp = null;
            for (TppRefProductRegisterType x : productRegisterType) {
                strTemp = x.getValue();
                break;
            }
            tppProductRegister.setType(strTemp);

            tppProductRegister.setAccount(newAcc.getId());
            accountId = newAcc.getId();

            tppProductRegister.setCurrencyCode(acc.getCurrencyCode());

            tppProductRegister.setState("1");

            tppProductRegister.setAccountNumber(newAcc.getAccountNumber());

            session.persist(tppProductRegister);
            session.getTransaction().commit();
        }

        if (accountId == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
//                    .contentType(MediaType.TEXT_PLAIN)
                    .header("Accept-Language:\"ru-ru,ru\"")
                    .body("Счет не найден. branchCode='" + acc.branchCode + "' and currencyCode='" + acc.currencyCode + "' and mdmCode='" + acc.mdmCode + "' and priorityCode='" + acc.priorityCode + "' and registryTypeCode='" + acc.registryTypeCode + "'");
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\n" +
                            "\"data\": {\n" +
                            "\"accountId\": \"" + accountId + "\"\n" +
                            "}\n" +
                            "}\n");
        }
    }
}
