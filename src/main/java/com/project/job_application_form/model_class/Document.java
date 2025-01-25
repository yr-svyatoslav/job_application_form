package com.project.job_application_form.model_class;

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
    private String documentName;
    private String documentNumber;
    private String issuedBy;  // Кем выдан
    private LocalDate issuedDate;  // Когда выдан

/*//возможно нужен toString для вывода данных объекта:
    @Override
    public String toString() {
        return "Document{" +
                "documentName='" + documentName + '\'' +
                ", documentNumber='" + documentNumber + '\'' +
                ", issuedBy='" + issuedBy + '\'' +
                ", issuedDate=" + issuedDate +
                '}';
    }*/

}
