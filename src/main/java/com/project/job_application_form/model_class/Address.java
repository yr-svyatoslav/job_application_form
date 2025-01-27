package com.project.job_application_form.model_class;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
/* аннотации Lombok для сокращения части кода
(создание гетеров, сетеров и конструкторов со всеми параметрами) */

// импортируем классы и аннотации (@Entity, @Id, @GeneratedValue, @ManyToOne)
import jakarta.persistence.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils; // метод для копирования всех свойств из одного объекта в другой


@Entity // аннотирует (объявляет) класс как сущность, которая будет храниться в БД
@Table(name = "Address") // создаёт таблицу в БД
@Getter // автоматически создаёт геттеры для всех параметров
@Setter // автоматически создаёт сеттеры для всех параметров
@AllArgsConstructor // автоматически создаёт конструктор с объявлением всех параметров
@NoArgsConstructor /* автоматически создаёт конструктор без параметров
                    если нужно создать сущность без параметров +
                    + необходимо для корректной работы Hibernate,
                    который часто требует наличие конструктора без параметров*/
public class Address {
    @Id // первичный ключ (primary key)
    @GeneratedValue (strategy = GenerationType.IDENTITY) // генерация ID автоматически по стратегии=инкремент+1
    @JsonIgnore
    private Long id;
    @JsonProperty("Город")
    private String city;
    @JsonProperty("Область")
    private String area;
    @JsonProperty("Район")
    private String district;
    @JsonProperty("Улица")
    private String street;
    @JsonProperty("Дом")
    private String house;
    @JsonProperty("Корпус")
    private String block;
    @JsonProperty("Квартира")
    private String flat;

    @JsonProperty("Тип адреса")
    private String addressType; // Тип адреса: "PERMANENT" (постоянный) или "ACTUAL" (фактический)

    // связь "многие к одному" для связи с основным разделом Candidate
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "candidate_id")
    @JsonBackReference
    private Candidate candidate;

    // Метод для копирования данных из постоянного адреса в фактический
    public static void copyPermanentAddressToActual (List<Address> addresses) {
        // Создаем карту адресов с типами адресов в качестве ключей
        Map<String, Address> addressMap = addresses.stream()
                .collect(Collectors.toMap(Address::getAddressType, address -> address));

        Address permanentAddress = addressMap.get("РЕГИСТРАЦИЯ");
        Address actualAddress = addressMap.get("ФАКТИЧЕСКИЙ");


        // если есть постоянный, но нет фактического, то создаём его и добавляем в список
            if (permanentAddress!=null) {
                if (actualAddress==null) {
                    actualAddress = new Address();
                    actualAddress.setAddressType("ФАКТИЧЕСКИЙ");
                    addresses.add(actualAddress);
                }
                // копируем все параметры из постоянного в фактический
                BeanUtils.copyProperties(permanentAddress, actualAddress);
            }
        }
    }