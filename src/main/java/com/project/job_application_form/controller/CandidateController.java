package com.project.job_application_form.controller;

import com.project.job_application_form.model_class.*;
import com.project.job_application_form.repository.CandidateRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController // Объявляет класс как контроллер Spring REST
@RequestMapping ("/candidates") // Указывает базовый URL для всех маршрутов в этом контроллере
public class CandidateController {

    //статический логер с использованием библиотеки SLF4J и LoggerFactory
    private static final Logger logger = LoggerFactory.getLogger(CandidateController.class);

    @Autowired // авто подключение объекта (репозиторий)
    private CandidateRepository candidateRepository; // Поле для работы с БД через репозиторий CandidateRepository

    // Метод для создания шаблона ответа с правильным порядком
    private Map<String, Object> formatCandidateResponse(Candidate candidate) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("ИИН", candidate.getIin());
        response.put("ФИО", candidate.getFullName());
        response.put("Дата рождения", candidate.getBirthDate());
        response.put("Место рождения", candidate.getBirthPlace());
        response.put("Национальность", candidate.getNationality());
        response.put("Гражданство", candidate.getCitizenship());
        response.put("Документ", candidate.getDocument());
        response.put("Контакты", candidate.getContacts());
        response.put("Адрес", candidate.getAddresses());
        response.put("Фактический адрес совпадает с адресом постоянной регистрации", candidate.isSameAsPermanentAddress());
        response.put("Образование", candidate.getEducations());
        response.put("Курсы", candidate.getCourses());
        response.put("Место работы",candidate.getWorkExperiences());
        response.put("Рекомендации",candidate.getRecommendations());
        response.put("Семейное положение",candidate.getFamilyStatuses());
        response.put("Дополнительная информация",candidate.getAdditionalInfos());
        return response;
    }

    // Метод для инициализации всех связанных объектов
    private Candidate initializeCandidateTemplate() {
        Candidate candidate = new Candidate();
        candidate.setDocument(new Document());
        // Инициализация коллекций и связанных сущностей
        candidate.setContacts(initContacts());
        candidate.setAddresses(initAddress());
        candidate.setEducations(initEducation());
        candidate.setCourses(initCourses());
        candidate.setWorkExperiences(initWorkExperience());
        candidate.setRecommendations(initRecommendations());
        candidate.setFamilyStatuses(initFamilyStatus());
        candidate.setAdditionalInfos(initAdditionalInfo());
        return candidate;
    }

    // Вспомогательные методы для инициализации коллекций
    private List<Contacts> initContacts() {
        Contacts emptyContact = new Contacts();
        emptyContact.setRelativesContacts(new RelativesContacts());
        return List.of(emptyContact);
    }
    private List<Address> initAddress() {
        Address emptyAddress = new Address();
        return List.of(emptyAddress);
    }
    private List<Education> initEducation() {
        Education emptyEducation = new Education();
        return List.of(emptyEducation);
    }
    private List<Courses> initCourses() {
        Courses emptyCourses = new Courses();
        return List.of(emptyCourses);
    }
    private List<WorkExperience> initWorkExperience() {
        WorkExperience emptyWorkExperience = new WorkExperience();
        return List.of(emptyWorkExperience);
    }
    private List<Recommendation> initRecommendations() {
        Recommendation emptyRecommendation = new Recommendation();
        return List.of(emptyRecommendation);
    }
    private List<FamilyStatus> initFamilyStatus() {
        FamilyStatus emptyFamilyStatus = new FamilyStatus();
        return List.of(emptyFamilyStatus);
    }
    private List<AdditionalInfo> initAdditionalInfo() {
        AdditionalInfo emptyAdditionalInfo = new AdditionalInfo();
        return List.of(emptyAdditionalInfo);
    }

    // Эндпоинт для получения пустого шаблона кандидата
    @GetMapping("/template")
    public ResponseEntity<Map<String, Object>> getCandidateTemplate() {
        // Инициализация шаблона кандидата с дефолтными значениями для связанных объектов
        logger.info("Запрос на получение шаблона кандидата");
        Candidate candidate = initializeCandidateTemplate();
        Map <String, Object> response = formatCandidateResponse(candidate);
        // Возвращаем пустой объект, с дефолтными значениями (null или пустые строки)
        logger.info("Шаблон кандидата успешно возвращен");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // создание новой анкеты
    @PostMapping ("/create")
    private ResponseEntity<Map<String, Object>> createCandidate (@RequestBody Candidate candidate) {
        // Установить связь между Candidate и Contacts
        logger.info("Запрос на создание нового кандидата");
        for (Contacts contact : candidate.getContacts()) {
            contact.setCandidate(candidate);
        }
        Candidate savedCandidate = candidateRepository.save(candidate);
        Map <String, Object> response = formatCandidateResponse(savedCandidate);
        logger.info("Новая анкета кандидата с id {} успешно создана", savedCandidate.getId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
        /*
        ResponseEntity<> - объект в виде HTTP-ответа, содержит тело ответа и HTTP-статус
        @RequestBody - указывает, что тело запроса (JSON) автоматически преобразуется в объект
         */

    // поиск анкеты
    @GetMapping ("/{id}")
    public ResponseEntity<Map<String, Object>> getCandidate (@PathVariable Long id) {
        logger.info("Запрос на получение кандидата с id: {}", id);
        Optional<Candidate> candidateOptional = candidateRepository.findById(id);
        if (candidateOptional.isPresent()) {
            Candidate candidate = candidateOptional.get();
            Map <String, Object> response = formatCandidateResponse(candidate);
            logger.info("Кандидат с id {} найден", id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            logger.warn("Кандидат с id {} не найден", id);
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
    public ResponseEntity<Map<String, Object>> updateCandidate (@PathVariable Long id, @RequestBody Candidate candidate) {
        logger.info("Запрос на обновление кандидата с id: {}", id);
        if (candidateRepository.existsById(id)) {
            candidate.setId(id); // установить id кандидата
            Candidate updatedCandidate = candidateRepository.save(candidate);
            Map <String, Object> response = formatCandidateResponse(updatedCandidate);
            logger.info("Анкета кандидата с id {} успешно обновлена", id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            logger.warn("Кандидат с id {} не найден для обновления", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Удаление кандидата
    @DeleteMapping ("/{id}")
    public ResponseEntity<Candidate> deleteCandidate (@PathVariable Long id) {
        logger.info("Запрос на удаление кандидата с id: {}", id);
        if (candidateRepository.existsById(id)) {
            candidateRepository.deleteById(id);
            logger.info("Кандидат с id {} успешно удален", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Возвращает статус 204 (No Content) для успешного удаления
        } else {
            logger.warn("Кандидат с id {} не найден для удаления", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
