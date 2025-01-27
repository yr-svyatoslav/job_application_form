package com.project.job_application_form.model_class;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;


/* аннотации Lombok для сокращения части кода
(создание гетеров, сетеров и конструкторов со всеми параметрами) */
import lombok.*;

@Embeddable // позволит передать объект этого класса в другой
@Table(name = "RelativesContacts") // создаёт таблицу в БД
@Getter // автоматически создаёт геттеры для всех параметров
@Setter // автоматически создаёт сеттеры для всех параметров
@AllArgsConstructor //  автоматически создаёт конструктор с объявлением всех параметров
@NoArgsConstructor /* автоматически создаёт конструктор без параметров
                    если нужно создать сущность без параметров +
                    + необходимо для корректной работы Hibernate,
                    который часто требует наличие конструктора без параметров*/
public class RelativesContacts {
    @JsonProperty("Номер")
    private String numberRelativ;
    @JsonProperty("ФИО")
    private String fullNameRelativ;
    @JsonProperty("Степень родства")
    private String relationship;

}
