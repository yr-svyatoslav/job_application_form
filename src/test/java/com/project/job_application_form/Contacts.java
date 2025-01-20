package com.project.job_application_form;

import lombok.*;
/* аннотации Lombok для сокращения части кода
(создание гетеров, сетеров и конструкторов со всеми параметрами) */

// импортируем классы и аннотации (@Entity, @Id, @GeneratedValue, @Embedded, @ManyToOne)
import jakarta.persistence.*;


@Entity // аннотирует (объявляет) класс как сущность, которая будет храниться в БД
@Getter // автоматически создаёт геттеры для всех параметров
@Setter // автоматически создаёт сеттеры для всех параметров
@AllArgsConstructor // автоматически создаёт конструктор с объявлением всех параметров
@NoArgsConstructor /* автоматически создаёт конструктор без параметров
                    если нужно создать сущность без параметров +
                    + необходимо для корректной работы Hibernate,
                    который часто требует наличие конструктора без параметров*/
public class Contacts {
    @Id // первичный ключ (primary key)
    @GeneratedValue (strategy = GenerationType.IDENTITY) // генерация ID автоматически по стратегии=инкремент+1
    private Long id;

    private String numberHome;
    private String numberBusiness;
    private String numberMobile;
    private String e_mail;

    // Добавляем (принимаем) объект из класса Document
    @Embedded
    private RelativesContacts relativesContacts;

    // связь "многие к одному" для связи с основным разделом Кандидата, возможно удалю
    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

}
