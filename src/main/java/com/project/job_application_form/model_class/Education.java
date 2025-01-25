package com.project.job_application_form.model_class;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
// импортируем классы и аннотации (@Entity, @Id, @GeneratedValue, @ManyToOne)

/* аннотации Lombok для сокращения части кода
(создание гетеров, сетеров и конструкторов со всеми параметрами) */
import lombok.*;

@Entity // аннотирует (объявляет) класс как сущность, которая будет храниться в БД
@Table(name = "Education") // создаёт таблицу в БД
@Getter // автоматически создаёт геттеры для всех параметров
@Setter // автоматически создаёт сеттеры для всех параметров
@AllArgsConstructor // автоматически создаёт конструктор с объявлением всех параметров
@NoArgsConstructor /* автоматически создаёт конструктор без параметров
                    если нужно создать сущность без параметров +
                    + необходимо для корректной работы Hibernate,
                    который часто требует наличие конструктора без параметров*/
public class Education {
    @Id // первичный ключ (primary key)
    @GeneratedValue (strategy = GenerationType.IDENTITY) // генерация ID автоматически по стратегии=инкремент+1
    @JsonIgnore
    private Long id;

    private String educationStartYear;
    private String educationEndYear;
    private String educationInstitutionName;
    private String educationSpecialization;
    private String educationFormEducation;
    private String educationQualification;

    // Форматирование вывода данных
    public String getEducation () {
        return String.format("%s-%s, %s, %s, %s, %s",
                formatField(educationStartYear),
                formatField(educationEndYear),
                formatField(educationInstitutionName),
                formatField(educationSpecialization),
                formatField(educationFormEducation),
                formatField(educationQualification));
    }

    private String formatField(String field) {
        return field != null ? field : "не указано";
    }

    // связь "многие к одному" для связи с основным разделом Candidate
    @ManyToOne
    @JoinColumn(name = "candidate_id")
    @JsonIgnore
    private Candidate candidate;
}
