package ru.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import ru.models.*;
import ru.request.InstanceArrangement;
import ru.request.RequestInstance;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class InstanceDAO {

    public ResponseEntity<String> createProduct(RequestInstance inst) {
        List<TppRefProductClass> productClass;
        FindAccountNumber findAccountNumber = new FindAccountNumber();

        Configuration configuration = new Configuration()
                .addAnnotatedClass(TppProduct.class)
                .addAnnotatedClass(Agreement.class)
                .addAnnotatedClass(TppRefProductClass.class)
                .addAnnotatedClass(TppRefProductRegisterType.class)
                .addAnnotatedClass(TppProductRegister.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        try (sessionFactory) {
            Session session = sessionFactory.openSession();
            if (inst.instanceId == null) {
                //Шаг 1.1. Проверка таблицы ЭП (tpp_product) на дубли.
                List<TppProduct> pr = session.createQuery(
                                "FROM TppProduct WHERE number='" + inst.contractNumber + "'", TppProduct.class)
                        .getResultList();
                for (TppProduct one : pr) {
                    return ResponseEntity
                            .status(HttpStatus.BAD_REQUEST)
                            .contentType(MediaType.TEXT_PLAIN)
                            .body("Параметр ContractNumber № договора '" + inst.contractNumber + "' уже существует для ЭП с ИД  " + one.getId() + ".");
                }
            }
            //Шаг 1.2. Проверка таблицы ДС (agreement) на дубли
            List<Agreement> agree;
            for (InstanceArrangement one : inst.instanceArrangements) {
                agree = session.createQuery(
                                "FROM Agreement WHERE number='" + one.number + "'", Agreement.class)
                        .getResultList();
                if (!agree.isEmpty()) {
                    return ResponseEntity
                            .status(HttpStatus.BAD_REQUEST)
                            .contentType(MediaType.TEXT_PLAIN)
                            .body("Параметр № Дополнительного соглашения (сделки) Number '" + one.number + "' уже существует для ЭП с ИД  " + inst.instanceId + ".");
                }
            }
            //Шаг 1.3. По КодуПродукта найти связные записи в Каталоге Типа регистра
            productClass = session.createQuery(
                            "FROM TppRefProductClass WHERE value='" + inst.productCode + "'", TppRefProductClass.class)
                    .getResultList();
            if (productClass.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .contentType(MediaType.TEXT_PLAIN)
                        .body("КодПродукта '" + inst.productCode + "' не найдено в Каталоге продуктов tpp_ref_product_class");
            }

            //Шаг 1.4. Добавить строку в таблицу tpp_product
            session.beginTransaction();
            TppProduct tppProduct = new TppProduct();
            tppProduct.setId(null);
            tppProduct.setProductCodeID(Long.valueOf(inst.instanceId)); //product_code_id BIGINT,
            tppProduct.setClientID(null); //client_id BIGINT,
            tppProduct.setType(inst.productType); //type VARCHAR(50),
            tppProduct.setNumber(inst.contractNumber); //number VARCHAR(50),
            tppProduct.setPriority(Long.valueOf(inst.priority)); //priority BIGINT,
            tppProduct.setDateOfConclusion(new Date()); //date_of_conclusion TIMESTAMP,
            tppProduct.setStartDateTime(new Date()); //start_date_time TIMESTAMP,
            tppProduct.setEndDateTime(null); //end_date_time TIMESTAMP,
            tppProduct.setDays(null); //days BIGINT,
            tppProduct.setPenaltyRate(null); //penalty_rate DECIMAL,
            tppProduct.setNso(null); //nso DECIMAL,
            tppProduct.setThresholdAmount(null); //threshold_amount DECIMAL,
            tppProduct.setRequisiteType(null); //requisite_type VARCHAR(50),
            tppProduct.setInterestRateType(null); //interest_rate_type VARCHAR(50),
            tppProduct.setTaxRate(null); //tax_rate DECIMAL,
            tppProduct.setReasoneClose(null); //reasone_close VARCHAR(100),
            tppProduct.setState("1"); //state VARCHAR(50)
            session.persist(tppProduct);
            session.getTransaction().commit();

            //Шаг 1.5. Добавить в таблицу ПР (tpp_product_registry) строки
            for (TppRefProductClass one : productClass) {
                for (TppRefProductRegisterType one2 : one.getTppRefProductRegisterType()) {
                    Account newAcc = findAccountNumber.get(inst.branchCode, inst.isoCurrencyCode, inst.mdmCode, "00", one2.getValue());
                    if (newAcc == null) {
                        return ResponseEntity
                                .status(HttpStatus.NOT_FOUND)
                                .contentType(MediaType.TEXT_PLAIN)
                                .body("Счет не найден. branchCode='" + inst.branchCode + "' and currencyCode='" + inst.isoCurrencyCode + "' and mdmCode='" + inst.mdmCode + "' and priorityCode='00' and registryTypeCode='" + one2.getValue() + "'");
                    }
                    session.beginTransaction();
                    TppProductRegister tppProductRegister = new TppProductRegister();
                    tppProductRegister.setId(null);//    id serial PRIMARY KEY ,
                    tppProductRegister.setProductID(Long.valueOf(inst.instanceId)); //    product_id BIGINT,
                    tppProductRegister.setType(one2.getValue());//    type VARCHAR(100) NOT NULL,
                    tppProductRegister.setAccount(newAcc.getId());//    account BIGINT,
                    tppProductRegister.setCurrencyCode(inst.isoCurrencyCode);//    currency_code VARCHAR(30),
                    tppProductRegister.setState("1");//    state VARCHAR(50),
                    tppProductRegister.setAccountNumber(newAcc.getAccountNumber());//    account_number VARCHAR(25)
                    session.persist(tppProductRegister);
                    session.getTransaction().commit();
                    break;
                }
                break;
            }
            //Шаг 2.1. Проверка таблицы ЭП (tpp_product) на существование ЭП.
            List<TppProduct> pr = session.createQuery(
                            "FROM TppProduct WHERE id=" + inst.instanceId, TppProduct.class)
                    .getResultList();
            if (pr.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .contentType(MediaType.TEXT_PLAIN)
                        .body("Экземпляр продукта с параметром instanceId " + inst.instanceId + " не найден.");
            }

            //Шаг 2.2. Проверка таблицы ДС (agreement) на дубли
            for (InstanceArrangement one : inst.instanceArrangements) {
                List<Agreement> ag = session.createQuery(
                                "FROM Agreement WHERE number='" + one.number + "'", Agreement.class)
                        .getResultList();
                for (Agreement oneAG : ag) {
                    return ResponseEntity
                            .status(HttpStatus.BAD_REQUEST)
                            .contentType(MediaType.TEXT_PLAIN)
                            .body("Параметр № Дополнительного соглашения (сделки) Number '" + one.number + "' уже существует для ЭП с ИД " + oneAG.getId() + ".");
                }
            }
            //Шаг 8.

            //Выполняется отправка данных в систему-источник запроса на создание Экземпляра продукта
//            List<TppProductRegister> tpr = session.createQuery(
//                            "FROM TppProductRegister WHERE state<>200", TppProductRegister.class)
//                    .getResultList();
//            List<Agreement> ag = session.createQuery(
//                            "FROM Agreement WHERE status<>200", Agreement.class)
//                    .getResultList();
            String strResponse = "{\n" +
                    "\"data\": {\n" +
                    "\"instanceId\": \"" + inst.instanceId + "\",\n" +
                    "\"registerId\": [\n";
            AtomicReference<String> str= new AtomicReference<>("");
            session.createQuery(
                            "FROM TppProductRegister WHERE state<>'1'", TppProductRegister.class)
                    .getResultList();//.forEach(x-> str.updateAndGet(v -> v + "\""+x.getId().toString()+"\","));
            strResponse += str.get();
            strResponse += "\n" +
            "],\n" +
                    "\"supplementaryAgreementId\": [\n";
            str.set("");
            session.createQuery(
                            "FROM Agreement WHERE status<>'200'", Agreement.class)
                    .getResultList().forEach(x-> str.updateAndGet(v -> v + "\""+x.getId()+"\","));
            strResponse += "\n" +
            "]\n" +
                    "}\n" +
                    "}\n";


            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(strResponse);
        }
        //return null;
    }
}
