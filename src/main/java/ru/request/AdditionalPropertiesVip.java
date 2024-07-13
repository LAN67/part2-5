package ru.request;

import lombok.Getter;
import lombok.Setter;

/**
 * массив дополнительных признаков для сегмента КИБ(VIP), добавлять по мере необходимости? на сегодня могут быть переданы 2 пары ключ/значение:
 * "additionalProperties":
 * {
 * "data": [
 * {
 * "key": " RailwayRegionOwn",
 * "value": "ABC",
 * "name": "Регион принадлежности железной дороги"
 * },
 * {
 * "key": "counter",
 * "value": "123",
 * "name": "Счетчик"
 * }
 * ]
 * }
 */

@Getter
@Setter
public class AdditionalPropertiesVip{
    public String key; //-Ключ
    public String value; //-Значение
    public String name;

}
