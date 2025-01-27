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

public class FamilyStatus {
    @Id // первичный ключ (primary key)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // генерация ID автоматически по стратегии=инкремент+1
    @JsonIgnore
    private Long id;
    @JsonProperty("Родство")
    private String relation;
    @JsonProperty("Семейный статус")
    private String marriageStatus;
    @JsonProperty("ФИО")
    private String fullName;
    @JsonProperty("Дата рождения")
    private String dateOfBirth;
    @JsonProperty("Место работы")
    private String workplace;
    @JsonProperty("Должность")
    private String position;
    @JsonProperty("Адрес")
    private String address;
    @JsonProperty("Телефон")
    private String phone;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "candidate_id")
    @JsonBackReference
    private Candidate candidate;

    public void setFamilyMemberInfo(FamilyStatus familyMemberInfo, String typeOfRelation){
        // Методы для распределения информации по типу их связи
        if(typeOfRelation.equalsIgnoreCase("PARTNER")){
            this.fullName = familyMemberInfo.getFullName();
            this.workplace = familyMemberInfo.getWorkplace();
            this.position = familyMemberInfo.getPosition();
            this.address = familyMemberInfo.getAddress();
            this.phone = familyMemberInfo.getPhone();
        } else if(typeOfRelation.equalsIgnoreCase("CHILDREN")){
            this.fullName = familyMemberInfo.getFullName();
            this.dateOfBirth = familyMemberInfo.getDateOfBirth();
            this.phone = familyMemberInfo.getPhone();
            this.workplace = familyMemberInfo.getWorkplace();
        } else if(typeOfRelation.equalsIgnoreCase("RELATIONS")){
            this.relation = familyMemberInfo.getRelation();
            this.fullName = familyMemberInfo.getFullName();
            this.dateOfBirth = familyMemberInfo.getDateOfBirth();
            this.workplace = familyMemberInfo.getWorkplace();
            this.position = familyMemberInfo.getPosition();
            this.address = familyMemberInfo.getAddress();
            this.phone = familyMemberInfo.getPhone();
        }
    }
}
