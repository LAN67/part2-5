package ru.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.models.*;

import java.util.List;

public class FindAccountNumber {

    public Account get(String branchCode, String currencyCode, String mdmCode, String priorityCode, String registryTypeCode) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(AccountPool.class)
                .addAnnotatedClass(Account.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        try (sessionFactory) {
            Session session = sessionFactory.openSession();


            List<AccountPool> ap = session.createQuery(
                            "FROM AccountPool WHERE branchCode='" + branchCode + "' and currencyCode='" + currencyCode + "' and mdmCode='" + mdmCode + "' and priorityCode='" + priorityCode + "' and registryTypeCode='" + registryTypeCode + "'", AccountPool.class)
                    .getResultList();
            for (AccountPool oneAP : ap) {
                for (Account oneAcc : oneAP.getAccount()) {
                    return oneAcc;
                }
            }
        }
        return null;
    }
}