package com.project.job_application_form;

import jakarta.persistence.*;
// импортируем классы и аннотации (@Entity, @Id, @GeneratedValue, @ManyToOne)

/* аннотации Lombok для сокращения части кода
(создание гетеров, сетеров и конструкторов со всеми параметрами) */
import lombok.*;

@Entity // аннотирует (объявляет) класс как сущность, которая будет храниться в БД
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
    private Long id;

    private String educationStartYear;
    private String educationEndYear;
    private String educationInstitutionName;
    private String educationSpecialization;
    private String educationFormEducation;
    private String educationQualification;

    // Форматирование вывода данных
    public String getEducation() {
        String StartYear = educationStartYear != null ? educationStartYear : "";
        String EndYear = educationEndYear != null ? educationEndYear : "";

        return String.format("%s-%s, %s, %s, %s, %s",
                StartYear, EndYear, educationInstitutionName, educationSpecialization, educationFormEducation, educationQualification);
    }

    // связь "многие к одному" для связи с основным разделом Candidate
    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;
}
