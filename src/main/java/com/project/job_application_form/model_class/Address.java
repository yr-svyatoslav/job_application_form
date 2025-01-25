package com.project.job_application_form.model_class;

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
    private Long id;

    private String city;
    private String area;
    private String district;
    private String street;
    private String house;
    private String block;
    private String flat;

    private String addressType; // Тип адреса: "PERMANENT" (постоянный) или "ACTUAL" (фактический)

    // связь "многие к одному" для связи с основным разделом Candidate
    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    // Метод для копирования данных из постоянного адреса в фактический
    public static void copyPermanentAddressToActual (List<Address> addresses) {
        // Создаем карту адресов с типами адресов в качестве ключей
        Map<String, Address> addressMap = addresses.stream()
                .collect(Collectors.toMap(Address::getAddressType, address -> address));

        Address permanentAddress = addressMap.get("PERMANENT");
        Address actualAddress = addressMap.get("ACTUAL");


        // если есть постоянный, но нет фактического, то создаём его и добавляем в список
            if (permanentAddress!=null) {
                if (actualAddress==null) {
                    actualAddress = new Address();
                    actualAddress.setAddressType("ACTUAL");
                    addresses.add(actualAddress);
                }
                // копируем все параметры из постоянного в фактический
                BeanUtils.copyProperties(permanentAddress, actualAddress);
            }
        }
    }