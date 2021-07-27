package com.hoangcongst.sbboilerplate.repository;

import com.hoangcongst.sbboilerplate.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    <T> Page<T> findAllByNameOrderByIdDesc(String name, Class<T> type, Pageable pageable);
}
