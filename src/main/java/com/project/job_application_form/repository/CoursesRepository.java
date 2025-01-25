package com.project.job_application_form.repository;

import com.project.job_application_form.model_class.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursesRepository extends JpaRepository<Courses, Long> {
}