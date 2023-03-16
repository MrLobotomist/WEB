package com.example.testsecurity2dbthymeleaf.repository;

import com.example.testsecurity2dbthymeleaf.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
