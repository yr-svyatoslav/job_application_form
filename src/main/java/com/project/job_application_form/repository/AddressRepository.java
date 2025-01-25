package com.project.job_application_form.repository;

import com.project.job_application_form.model_class.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}

