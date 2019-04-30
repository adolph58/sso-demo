package com.artech.personal.domain.repository;

import com.artech.personal.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 程江涛 on 2019/4/13.
 */

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByName(String name);
}
