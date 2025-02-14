package com.project.job_application_form.model_class;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("ИИН")
    private String iin;
    @JsonProperty("ФИО")
    private String fullName;
    @JsonProperty("Дата рождения")
    private LocalDate birthDate;
    @JsonProperty("Место рождения")
    private String birthPlace;
    @JsonProperty("Национальность")
    private String nationality;
    @JsonProperty("Гражданство")
    private String citizenship;

    // Добавляем (принимаем) объект из класса Document
    @Embedded
    @JsonProperty("Документ")
    private Document document;

    // Контакты
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    @JsonProperty("Контакты")
    private List<Contacts> contacts;

    // Местожительство
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    @JsonProperty("Адрес")
    private List<Address> addresses;

    @JsonProperty("Фактический адрес совпадает с адресом постоянной регистрации")
    private boolean isSameAsPermanentAddress; // Галочка "совпадает с адресом постоянной регистрации"

    public void copyPermanentAddressToActual () {
        if (isSameAsPermanentAddress) {
            Address.copyPermanentAddressToActual(addresses);
        }
    }

    // Образование
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    @JsonProperty("Образование")
    private List<Education> educations;

    // Курсы
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    @JsonProperty("Курсы")
    private List<Courses> courses;

    //Места работы
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    @JsonProperty("Место работы")
    private List<WorkExperience> workExperiences;

    //Рекомендации
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    @JsonProperty("Рекомендации")
    private List<Recommendation> recommendations;

    //Семейное положение
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    @JsonProperty("Семейное положение")
    private List<FamilyStatus> familyStatuses;

    //Дополнительная информация
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    @JsonProperty("Дополнительная информация")
    private List<AdditionalInfo> additionalInfos;

}
