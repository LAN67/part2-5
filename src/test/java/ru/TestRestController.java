package ru;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = TestBeans.class)
@AutoConfigureMockMvc
public class TestRestController {
    @Autowired
    MockMvc mockMvc;

    @Test
    void TestAccountController() throws Exception {
        String strURL = "/corporate-settlement-account/create";
        String request;

        //"instanceId": 1, запрос 1
        request = """
                            {
                                "instanceId": 1,
                                "registryTypeCode": "03.012.002_47533_ComSoLd",
                                "accountType": "Клиентский",
                                "currencyCode": "800",
                                "branchCode": "0022",
                                "priorityCode": "00",
                                "mdmCode": "15",
                                "clientCode": null,
                                "trainRegion": null,
                                "counter": null,
                                "salesCode": null
                            }
                            """;

        this.mockMvc.perform(get(strURL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
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

        //"instanceId": 1, запрос 2
        this.mockMvc.perform(get(strURL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpectAll(
                        status().isBadRequest(),
                        content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN),
                        content().string("Параметр registryTypeCode тип регистра 03.012.002_47533_ComSoLd уже существует для ЭП с ИД  1.")
                );

        //"instanceId": 2, запрос 1
        request = """
                            {
                                "instanceId": 2,
                                "registryTypeCode": "03.012.002_47533_ComSoLd",
                                "accountType": "Клиентский",
                                "currencyCode": "800",
                                "branchCode": "0022",
                                "priorityCode": "00",
                                "mdmCode": "15",
                                "clientCode": null,
                                "trainRegion": null,
                                "counter": null,
                                "salesCode": null
                            }
                            """;

        this.mockMvc.perform(get(strURL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
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

        //"instanceId": 2, запрос 2
        this.mockMvc.perform(get(strURL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpectAll(
                        status().isBadRequest(),
                        content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN),
                        content().string("Параметр registryTypeCode тип регистра 03.012.002_47533_ComSoLd уже существует для ЭП с ИД  2.")
                );
    }


    @Test
    void TestInstanceController() throws Exception {
        String strURL = "/corporate-settlement-instance/create";
        String request;

        //договор: number011 и number012, запрос 1
        request = """
                            {
                                "instanceId": 1,
                                "productType": 1,
                                "productCode": "03.012.002",
                                "registerType": 1,
                                "mdmCode": "15",
                                "contractNumber": "number001",
                                "contractDate": "2024-07-17",
                                "priority": 1,
                                "interestRatePenalty": null,
                                "minimalBalance": null,
                                "thresholdAmount": null,
                                "accountingDetails": null,
                                "rateType": null,
                                "taxPercentageRate": null,
                                "technicalOverdraftLimitAmount": null,
                                "contractId": 1,
                                "branchCode": "0022",
                                "isoCurrencyCode": "800",
                                "urgencyCode": 1,
                                "referenceCode": null,
                                "additionalPropertiesVips": [],
                                "instanceArrangements": [
                                    {
                                        "number": "number011",
                                        "openingDate": "2024-07-17",
                                        "status": "1"
                                    },
                                    {
                                        "number": "number012",
                                        "openingDate": "2024-07-17",
                                        "status": "1"
                                    }
                                ]
                            }
                            """;

        this.mockMvc.perform(get(strURL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().json("""
                                            {
                                                "data": {
                                                    "instanceId": "1",
                                                    "registerId": [
                                                        "1"
                                                    ],
                                                    "supplementaryAgreementId": [
                                                        "1",
                                                        "2"
                                                    ]
                                                }
                                            }
                                            """)
                );

        //договор: number011 и number012, запрос 2
        this.mockMvc.perform(get(strURL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpectAll(
                        status().isBadRequest(),
                        content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN),
                        content().string("Параметр № Дополнительного соглашения (сделки) Number 'number011' уже существует для ЭП с ИД  1.")
                );

        ////договор: number001, запрос 1
        request = """
        {
            "instanceId": 1,
                "productType": 1,
                "productCode": "03.012.002",
                "registerType": 1,
                "mdmCode": "15",
                "contractNumber": "number001",
                "contractDate": "2024-07-17",
                "priority": 1,
                "interestRatePenalty": null,
                "minimalBalance": null,
                "thresholdAmount": null,
                "accountingDetails": null,
                "rateType": null,
                "taxPercentageRate": null,
                "technicalOverdraftLimitAmount": null,
                "contractId": 1,
                "branchCode": "0022",
                "isoCurrencyCode": "800",
                "urgencyCode": 1,
                "referenceCode": null,
                "additionalPropertiesVips": [],
            "instanceArrangements": [
            {
                "number": "number001",
                    "openingDate": "2024-07-17",
                    "status": "1"
            }
                                ]
        }
        """;

this.mockMvc.perform(get(strURL)
    .contentType(MediaType.APPLICATION_JSON)
    .content(request))
.andExpectAll(
    status().isOk(),
    content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
    content().json("""
                                {
                                    "data": {
                                        "instanceId": "1",
                                        "registerId": [
                                            "1",
                                            "2"
                                        ],
                                        "supplementaryAgreementId": [
                                            "1",
                                            "2",
                                            "3"
                                        ]
                                    }
                                }
                                """)
                );

        //"instanceId": 2, запрос 2
        this.mockMvc.perform(get(strURL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpectAll(
                        status().isBadRequest(),
                        content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN),
                        content().string("Параметр № Дополнительного соглашения (сделки) Number 'number001' уже существует для ЭП с ИД  1.")
                );

    }


}
