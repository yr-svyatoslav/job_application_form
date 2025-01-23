package com.project.job_application_form;
import jakarta.persistence.*;
import lombok.*;

@Embeddable // позволит передать объект этого класса в другой
@Entity // аннотирует (объявляет) класс как сущность, которая будет храниться в БД
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
    private Long id;

    private String experienceStartDate;
    private String experienceEndDate;
    private String experienceOrganizationName;
    private String experiencePosition;
    private String experienceManagerName;
    private String experienceManagerPhone;
    private String experienceReasonForLeaving;

    // Форматирование вывода данных
    public String getExperience(){
       return String.format("%s-%s, %s, %s, %s, %s, %s",
                     experienceStartDate, experienceEndDate, experienceOrganizationName, experiencePosition, experienceManagerName, experienceManagerPhone, experienceReasonForLeaving);
    }

    // связь "многие к одному" для связи с основным разделом Candidate
    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

}
