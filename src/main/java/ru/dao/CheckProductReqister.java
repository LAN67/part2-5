package ru.dao;

import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;
import ru.models.TppProductRegister;
import ru.request.RequestAccount;

import java.util.List;

//@Service '@Autowired' not applicable to local variable
@Transactional
public class CheckProductReqister {

    public String checkProductReqister(RequestAccount acc) {

        Configuration configuration = new Configuration()
                .addAnnotatedClass(TppProductRegister.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        try (sessionFactory) {
            Session session = sessionFactory.openSession();
            System.out.println("FROM tpp_product_register WHERE product_id=" + acc.instanceId + " and type='" + acc.registryTypeCode + "'");
            List<TppProductRegister> pr = session.createQuery(
                            "FROM TppProductRegister WHERE productID=" + acc.instanceId + " and type='" + acc.registryTypeCode + "'", TppProductRegister.class)
                    .getResultList();
            session.close();
            if (!pr.isEmpty()) {
                return "Параметр registryTypeCode тип регистра " + acc.registryTypeCode + " уже существует для ЭП с ИД  " + acc.instanceId + ".";
            }
            return null;
        }
    }
}
