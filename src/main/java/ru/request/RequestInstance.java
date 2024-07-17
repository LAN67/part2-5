package ru.request;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class RequestInstance {
    public Integer instanceId; // -Идентификатор экземпляра продукта Если есть, то создаётся ДопСоглашение к ЭП (договору). Если NULL/пусто, то создаётся новый ЭП. sequence
    public String productType; // +Тип Экземпляра Продукта Тип создаваемого экземпляра продукта/договора, нужен для определения типа создаваемого продукта (договор или сделка). Enum (НСО, СМО, ЕЖО, ДБДС, договор)
    public String productCode; // +Код продукта в каталоге продуктов Сущность - Вид договора. Ссылка на запись в каталоге продуктов. Нужен для определения типов и количества открываемых ПР и типов ЭП (договор ДКО, например). Соответствует в 172_Справочник классов продуктов Банка, поле  value
    public String registerType; // +Тип регистра Соответствует InternalId, ID типа регистра в Справочнике 167_Справочник Тип регистра
    public String mdmCode; // +Код Клиента (mdm) Ссылка на клиента, с которым заключен договор
    public String contractNumber; // +Номер договора Номер договора обслуживания
    public Date contractDate; // +Дата заключения договора обслуживания Дата заключения договора обслуживания
    public Integer priority; // +Приоритет Числовое значение, определяющее последовательность расчета %%
    public Float interestRatePenalty; // -Штрафная процентная ставка  Штрафная процентная ставка
    public Float minimalBalance; // -Неснижаемый остаток Неснижаемый остаток
    public Float thresholdAmount; // -Пороговая сумма Пороговая сумма
    public String accountingDetails; // -Реквизиты выплаты Из логической модели. Реквизиты выплаты
    public String rateType; // -Выбор ставки в зависимости от суммы enum, дифференцированная 0 /прогрессивная 1
    public Float taxPercentageRate; // -Ставка налогообложения  Ставка налогообложения
    public Float technicalOverdraftLimitAmount; // -Сумма лимита технического овердрафта Сумма лимита технического овердрафта
    public Integer contractId; // +ID Договора  Ссылка на договор обслуживания счета
    public String branchCode; // +Код филиала Код отделения, не БИК
    public String isoCurrencyCode; // +Код валюты Трехсимвольный код валюты счета в стандарте ISO
    public String urgencyCode; // +Код срочности договора Всегда “00”
    public Integer referenceCode; // -Код точки продаж идентификатор точки продаж, где можно осуществлять операции внесения
    List<AdditionalPropertiesVip> additionalPropertiesVips;
    List<InstanceArrangement> instanceArrangements;
}

