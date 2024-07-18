package ru;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import ru.request.RequestAccount;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = TestBeans.class)
//@SpringBootTest
@AutoConfigureMockMvc
public class TestRestController {
    @Autowired
    MockMvc mockMvc;


















    @Test
    void TestAccountController() throws Exception {
        String strURL = "/corporate-settlement-account/create";

        RequestAccount acc = new RequestAccount();
        acc.instanceId = 1L;
        acc.registryTypeCode = "03.012.002_47533_ComSoLd";
        acc.accountType = "Клиентский";
        acc.currencyCode = "800";
        acc.branchCode = "0022";
        acc.priorityCode = "00";
        acc.mdmCode = "15";
        acc.clientCode = null;
        acc.trainRegion = null;
        acc.counter = null;
        acc.salesCode = null;


        this.mockMvc.perform(get(strURL)
                        .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(acc))
//                        .content("{\n" +
//                        "    \"instanceId\": 1,\n" +
//                        "    \"registryTypeCode\": \"03.012.002_47533_ComSoLd\",\n" +
//                        "    \"accountType\": \"Клиентский\",\n" +
//                        "    \"currencyCode\": \"800\",\n" +
//                        "    \"branchCode\": \"0022\",\n" +
//                        "    \"priorityCode\": \"00\",\n" +
//                        "    \"mdmCode\": \"15\",\n" +
//                        "    \"clientCode\": null,\n" +
//                        "    \"trainRegion\": null,\n" +
//                        "    \"counter\": null,\n" +
//                        "    \"salesCode\": null\n" +
//                        "}")
                        )
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().json("""
                                {
                                "data": {
                                "accountId": "1"
                                }
                                }
                                """)
                );
    }
}
