package com.project.job_application_form.model_class;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
// импортируем классы и аннотации (@Entity, @Id, @GeneratedValue, @ManyToOne)

/* аннотации Lombok для сокращения части кода
(создание гетеров, сетеров и конструкторов со всеми параметрами) */
import lombok.*;

@Entity // аннотирует (объявляет) класс как сущность, которая будет храниться в БД
@Table(name = "Courses") // создаёт таблицу в БД
@Getter // автоматически создаёт геттеры для всех параметров
@Setter // автоматически создаёт сеттеры для всех параметров
@AllArgsConstructor // автоматически создаёт конструктор с объявлением всех параметров
@NoArgsConstructor /* автоматически создаёт конструктор без параметров
                    если нужно создать сущность без параметров +
                    + необходимо для корректной работы Hibernate,
                    который часто требует наличие конструктора без параметров*/

public class WorkExperience {
    @Id // первичный ключ (primary key)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // генерация ID автоматически по стратегии=инкремент+1
    @JsonIgnore
    private Long id;

    @JsonProperty("Дата начала")
    private String experienceStartDate;
    @JsonProperty("Дата окончания")
    private String experienceEndDate;
    @JsonProperty("Название организации")
    private String experienceOrganizationName;
    @JsonProperty("Наименование должности")
    private String experiencePosition;
    @JsonProperty("ФИО руководителя")
    private String experienceManagerName;
    @JsonProperty("Телефон руководителя")
    private String experienceManagerPhone;
    @JsonProperty("Причина увольнения")
    private String experienceReasonForLeaving;

    // связь "многие к одному" для связи с основным разделом Candidate
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "candidate_id")
    @JsonBackReference
    private Candidate candidate;
}
