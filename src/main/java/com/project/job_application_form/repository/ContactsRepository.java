package com.project.job_application_form.repository;

import com.project.job_application_form.model_class.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactsRepository extends JpaRepository<Contacts, Long> {
}