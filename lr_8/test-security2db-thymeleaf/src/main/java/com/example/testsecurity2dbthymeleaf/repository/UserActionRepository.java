package com.example.testsecurity2dbthymeleaf.repository;

import com.example.testsecurity2dbthymeleaf.entity.UserAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserActionRepository  extends JpaRepository<UserAction, Long> {

}
