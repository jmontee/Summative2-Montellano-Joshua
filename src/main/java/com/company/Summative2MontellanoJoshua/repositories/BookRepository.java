package com.company.Summative2MontellanoJoshua.repositories;

import com.company.Summative2MontellanoJoshua.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    public List<Book> findBookByAuthorId(Integer authorId);
}
