package ru.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestAccount {
    public Long instanceId; // +Идентификатор экземпляра продукта Идентификатор ЭП, к которому привязывается продуктовый регистр. sequence
    public String registryTypeCode; // -Тип регистра Тип создаваемого продуктового регистра
    public String accountType; // -Тип счета Клиентский или внутрибанковский
    public String currencyCode; // -Код валюты 3-х значный код валюты
    public String branchCode; // -Код филиала Код филиала
    public String priorityCode; // -Код срочности Всегда «00» для ПП РО ЮЛ
    public String mdmCode; // -Id клиента МДМ код клиента (КЮЛ)
    public String clientCode; // -Код клиента Только для ВИП (РЖД, ФПК). Обсуждается с клиентом (есть выбор).
    public String trainRegion; // -Регион принадлежности железной дороги Только для ВИП (РЖД, ФПК)
    public String counter; // -Счетчик Только для ВИП (РЖД, ФПК)
    public String salesCode; // -Код точки продаж Код точки продаж

}
