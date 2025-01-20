package com.project.job_application_form;

import jakarta.persistence.*;
// импортируем классы и аннотации (@Entity, @Id, @GeneratedValue, @OneToMany, @Embedded)

/* аннотации Lombok для сокращения части кода
(создание гетеров, сетеров и конструкторов со всеми параметрами) */
import lombok.*;

import java.time.LocalDate; // хранит дату без времени и часового пояса
import java.util.List;

@Entity // аннотирует (объявляет) класс как сущность, которая будет храниться в БД
@Getter // автоматически создаёт геттеры для всех параметров
@Setter // автоматически создаёт сеттеры для всех параметров
@AllArgsConstructor // автоматически создаёт конструктор с объявлением всех параметров
@NoArgsConstructor /* автоматически создаёт конструктор без параметров
                    если нужно создать сущность без параметров +
                    + необходимо для корректной работы Hibernate,
                    который часто требует наличие конструктора без параметров*/
public class Candidate {

    @Id // первичный ключ (primary key)
    @GeneratedValue (strategy = GenerationType.IDENTITY) // генерация ID автоматически по стратегии=инкремент+1
    private Long id;

    private String iin;
    private String fullName;
    private String previousSurname;
    private LocalDate birthDate;
    private String birthPlace;
    private String nationality;
    private String citizenship;

    // Добавляем (принимаем) объект из класса Document
    @Embedded
    private Document document;

    // связи "один ко многим" с другими разделами анкет

    // Контакты
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    private List<Contacts> contacts;

    // Местожительство
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    private List<Address> addresses;

    private boolean isSameAsPermanentAddress; // Галочка "совпадает с адресом постоянной регистрации"

    public void copyPermanentAddressToActual () {
        if (isSameAsPermanentAddress) {
            Address.copyPermanentAddressToActual(addresses);
        }
    }

    // Образование
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    private List<Education> educations;

    // Курсы
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    private List<Courses> courses;

}
