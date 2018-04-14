package com.hellokoding.auth.repository;

import com.hellokoding.auth.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Роман on 08.04.2018.
 */
public interface StatusRepository extends JpaRepository<Status, Long> {
}
