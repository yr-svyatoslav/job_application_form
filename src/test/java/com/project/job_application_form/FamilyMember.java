package com.project.job_application_form;

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

public class FamilyMember {

    @Id // первичный ключ (primary key)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // генерация ID автоматически по стратегии=инкремент+1
    private Long id;

    private String relation;
    private String marriageStatus;
    private String fullName;
    private String dateOfBirth;
    private String workplace;
    private String position;
    private String address;
    private String phone;

    public String getFamilyMember(){
        return String.format("%s, %s, %s, %s, %s, %s, %s",
                            relation, fullName, dateOfBirth, workplace, position, address, phone);
    }

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    public void setFamilyMemberInfo(FamilyMember familyMemberInfo, String typeOfRelation){
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
