package com.cibertec.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.entity.Branch;

public interface IBranchRepository extends JpaRepository<Branch, Integer> {
    Page<Branch> findByIsDeleteFalseAndIsEnabledTrue(Pageable pageable);
}
