package com.project.job_application_form.model_class;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
// импортируем классы и аннотации (@Entity, @Id, @GeneratedValue, @OneToMany, @Embedded)

/* аннотации Lombok для сокращения части кода
(создание гетеров, сетеров и конструкторов со всеми параметрами) */
import lombok.*;

import java.time.LocalDate; // хранит дату без времени и часового пояса
import java.util.List;

@Entity // аннотирует (объявляет) класс как сущность, которая будет храниться в БД
@Table(name = "Candidate") // создаёт таблицу в БД
@Getter // автоматически создаёт геттеры для всех параметров
@Setter // автоматически создаёт сеттеры для всех параметров
@AllArgsConstructor // автоматически создаёт конструктор с объявлением всех параметров
@NoArgsConstructor /* автоматически создаёт конструктор без параметров
                    если нужно создать сущность без параметров +
                    + необходимо для корректной работы Hibernate,
                    который часто требует наличие конструктора без параметров*/
public class Candidate {

    @Id // первичный ключ (primary key)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // генерация ID автоматически по стратегии=инкремент+1
    @JsonIgnore
    private Long id;

    private String iin;
    private String fullName;
    private LocalDate birthDate;
    private String birthPlace;
    private String nationality;
    private String citizenship;

    // Добавляем (принимаем) объект из класса Document
    @Embedded
    private Document document;

    // Контакты
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Contacts> contacts;

    // Местожительство
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Address> addresses;

    private boolean isSameAsPermanentAddress; // Галочка "совпадает с адресом постоянной регистрации"

    public void copyPermanentAddressToActual () {
        if (isSameAsPermanentAddress) {
            Address.copyPermanentAddressToActual(addresses);
        }
    }

    // Образование
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Education> educations;

    // Курсы
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Courses> courses;

    //Места работы
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<WorkExperience> workExperiences;

    //Рекомендации
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Recommendation> recommendations;

    //Семейное положение
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<FamilyStatus> familyStatuses;

    //Дополнительная информация
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<AdditionalInfo> additionalInfos;

}
