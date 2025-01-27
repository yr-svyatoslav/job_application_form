package com.project.job_application_form.model_class;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
@Entity // аннотирует (объявляет) класс как сущность, которая будет храниться в БД
@Getter // автоматически создаёт геттеры для всех параметров
@Setter // автоматически создаёт сеттеры для всех параметров
@AllArgsConstructor // автоматически создаёт конструктор с объявлением всех параметров
@NoArgsConstructor /* автоматически создаёт конструктор без параметров
                    если нужно создать сущность без параметров +
                    + необходимо для корректной работы Hibernate,
                    который часто требует наличие конструктора без параметров*/

public class AdditionalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @JsonProperty("Наличие авто")
    private boolean hasCar;
    @JsonProperty("Модель авто")
    private String carModel;
    @JsonProperty("Год выпуска авто")
    private int carYear;
    @JsonProperty("Номер авто")
    private String carLicensePlate;
    @JsonProperty("Военнообязанный?")
    private boolean isMilitaryObligated;
    @JsonProperty("Льготник?")
    private boolean hasBenefits;
    @JsonProperty("Имеется просроченный заем?")
    private boolean hasDebts;
    @JsonProperty("Административная ответственность")
    private boolean hasAdministrativeCases;
    @JsonProperty("Уголовная ответственность?")
    private boolean hasCriminalRecord;
    @JsonProperty("Плательщик алиментов?")
    private boolean paysAlimony;

    // связь "многие к одному" для связи с основным разделом Candidate
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "candidate_id")
    @JsonBackReference
    private Candidate candidate;
}
