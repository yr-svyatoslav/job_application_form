package com.project.job_application_form.controller;

import com.project.job_application_form.model_class.*;
import com.project.job_application_form.repository.CandidateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Объявляет класс как контроллер Spring REST
@RequestMapping ("/candidates") // Указывает базовый URL для всех маршрутов в этом контроллере
public class CandidateController {

    @Autowired // авто подключение объекта (репозиторий)
    private CandidateRepository candidateRepository; // Поле для работы с БД через репозиторий CandidateRepository

    // Эндпоинт для получения пустого шаблона кандидата

    // Метод для инициализации всех связанных объектов
    private Candidate initializeCandidateTemplate() {
        Candidate candidate = new Candidate();
        // Инициализация коллекций и связанных сущностей
        candidate.setContacts(initContacts());
//        candidate.setEducations(initEducation());
//        candidate.setCourses(initCourses());
        return candidate;
    }

    // Вспомогательные методы для инициализации коллекций
    private List<Contacts> initContacts() {
        Contacts emptyContact = new Contacts();
        emptyContact.setRelativesContacts(new RelativesContacts());
        return List.of(emptyContact);
    }
//    private List<Education> initEducation() {
//        Education emptyEducation = new Education();
//        return List.of(emptyEducation);
//    }
//    private List<Courses> initCourses() {
//        Courses emptyCourses = new Courses();
//        return List.of(emptyCourses);
//    }

    @GetMapping("/template")
    public ResponseEntity<Candidate> getCandidateTemplate() {
        // Инициализация шаблона кандидата с дефолтными значениями для связанных объектов
        Candidate candidate = initializeCandidateTemplate();
        // Возвращаем пустой объект, с дефолтными значениями (null или пустые строки)
        return new ResponseEntity<>(candidate, HttpStatus.OK);
    }

    // создание новой анкеты
    @PostMapping
    private ResponseEntity<Candidate> createCandidate (@RequestBody Candidate candidate) {
        Candidate savedCandidate = candidateRepository.save(candidate);
        return new ResponseEntity<>(savedCandidate, HttpStatus.CREATED);
    }
        /*
        ResponseEntity<> - объект в виде HTTP-ответа, содержит тело ответа и HTTP-статус
        @RequestBody - указывает, что тело запроса (JSON) автоматически преобразуется в объект
         */

    // поиск анкеты
    @GetMapping ("/{id}")
    public ResponseEntity<Candidate> getCandidate (@PathVariable Long id) {
        Optional<Candidate> candidate = candidateRepository.findById(id);
        if (candidate.isPresent()) {
            return new ResponseEntity<>(candidate.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
        /*
        @PathVariable - извлекает из URL (маршрута) конкретную переменную
        Optional — обёртка для значения, которое может быть null. Позволяет избежать явной проверки на null через if
        далее если найден (isPresent) возвращает его либо возвращает кандидат не найден (NOT_FOUND)
         */

    // обновление анкеты
    @PutMapping ("/{id}")
    public ResponseEntity<Candidate> updateCandidate (@PathVariable Long id, @RequestBody Candidate candidate) {
        if (candidateRepository.existsById(id)) {
            candidate.setId(id); // установить id кандидата
            Candidate updatedCandidate = candidateRepository.save(candidate);
            return new ResponseEntity<>(updatedCandidate, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Удаление кандидата
    @DeleteMapping ("/{id}")
    public ResponseEntity<Candidate> deleteCandidate (@PathVariable Long id) {
        if (candidateRepository.existsById(id)) {
            candidateRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Возвращает статус 204 (No Content) для успешного удаления
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
