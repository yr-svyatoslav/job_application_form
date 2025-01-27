package com.project.job_application_form.model_class;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;

/* аннотации Lombok для сокращения части кода
(создание гетеров, сетеров и конструкторов со всеми параметрами) */
import lombok.*;

import java.time.LocalDate; // хранит дату без времени и часового пояса


@Embeddable // позволит передать объект этого класса в другой
@Table(name = "Document") // создаёт таблицу в БД
@Getter // автоматически создаёт геттеры для всех параметров
@Setter // автоматически создаёт сеттеры для всех параметров
@AllArgsConstructor //  автоматически создаёт конструктор с объявлением всех параметров
@NoArgsConstructor /* автоматически создаёт конструктор без параметров
                    если нужно создать сущность без параметров +
                    + необходимо для корректной работы Hibernate,
                    который часто требует наличие конструктора без параметров*/
public class Document {
    @JsonProperty("Наименование")
    private String documentName;
    @JsonProperty("Номер")
    private String documentNumber;
    @JsonProperty("Кем выдан")
    private String issuedBy;
    @JsonProperty("Когда выдан")
    private LocalDate issuedDate;
}
