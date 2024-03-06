package com.andoliver46.ms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andoliver46.ms.models.BookModel;

public interface BookRepository extends JpaRepository<BookModel, Long> {

}
