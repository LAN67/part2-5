package ru.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class InstanceArrangement {
    public String generalAgreementId; // -ID доп.Ген.соглашения ID доп.Ген.соглашения
    public String supplementaryAgreementId; // -ID доп.соглашения ID доп.соглашения
    public String arrangementType; // -Тип соглашения Enum, НСО/ЕЖО/СМО/ДБДС и тд, см. актуальную ЛМД
    public Long shedulerJobId; // -Идентификатор периодичности учета Идентификатор задания/расписанияпериодичность учета/расчета/выплаты. sequence фиксируется в поле name
    public String number; // +Номер ДС Номер ДС
    public Date openingDate; // +Дата начала сделки Дата заключения сделки (НСО/ЕЖО/СМО/ДБДС)
    public Date closingDate; // -Дата окончания сделки
    public Date cancelDate; // -Дата отзыва сделки
    public Long validityDuration; // -Срок действия сделки
    public String cancellationReason; // -Причина расторжения
    public String status; // -Состояние/статус Статус ДС: закрыт, открыт
    public Date interestCalculationDate; // -Начисление начинается с (дата) Начисление начинается с (дата)
    public Float interestRate; // -Процент начисления на остаток Процент начисления на остаток
    public Float coefficient; // -Коэффициент  Показатель управления ставкой
    public String coefficientAction; // -Действие коэффициента Повышающий/понижающий enum +/-
    public Float minimumInterestRate; // -Минимум по ставке Минимум по ставке
    public String minimumInterestRateCoefficient; // -Коэффициент по минимальной ставке Коэффициент по минимальной ставке
    public String minimumInterestRateCoefficientAction; // -Действие коэффициента по минимальной ставке Повышающий/понижающий enum +/-
    public Float maximalnterestRate; // -Decimal Максимум по ставке Максимум по ставке
    public Float maximalnterestRateCoefficient; // -Decimal Коэффициент по максимальной ставке Коэффициент по максимальной ставке
    public String maximalnterestRateCoefficientAction; // -Действие коэффициента по максимальной ставке Повышающий/понижающий enum +/-

}
