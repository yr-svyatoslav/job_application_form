package com.project.job_application_form.repository;

import com.project.job_application_form.model_class.Education;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepository extends JpaRepository<Education, Long> {
}
