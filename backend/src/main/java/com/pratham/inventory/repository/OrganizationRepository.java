package com.pratham.inventory.repository;

import com.pratham.inventory.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrganizationRepository
                extends JpaRepository<Organization, UUID> {

        boolean existsByEmail(String email);

        boolean existsByPhone(String phone);
}