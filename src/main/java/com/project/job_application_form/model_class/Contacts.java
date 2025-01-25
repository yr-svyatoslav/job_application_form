package com.project.job_application_form.model_class;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
/* аннотации Lombok для сокращения части кода
(создание гетеров, сетеров и конструкторов со всеми параметрами) */

// импортируем классы и аннотации (@Entity, @Id, @GeneratedValue, @Embedded, @ManyToOne)
import jakarta.persistence.*;


@Entity // аннотирует (объявляет) класс как сущность, которая будет храниться в БД
@Table(name = "Contacts") // создаёт таблицу в БД
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
    @JsonIgnore
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
    @JsonIgnore
    private Candidate candidate;

}
