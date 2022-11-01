package com.company.Summative2MontellanoJoshua.controllers;


import com.company.Summative2MontellanoJoshua.models.Book;
import com.company.Summative2MontellanoJoshua.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/book")
    @ResponseStatus(HttpStatus.CREATED)
    public void createBook(@RequestBody Book newBook){
        bookRepository.save(newBook);
    }

    @GetMapping("/book/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book getBookById(@PathVariable Integer id){
        Optional<Book> book = bookRepository.findById(id);

        if(book.isPresent()){
            return book.get();
        }
        return  null;
    }


    @GetMapping("/book")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getAllBook(){
        return bookRepository.findAll();
    }


    @PutMapping("/book")
    @ResponseStatus(HttpStatus.OK)
    public void updateBook(@RequestBody Book newBook){
        Optional<Book> oldBook = bookRepository.findById(newBook.getId());
        if(oldBook.isPresent()){
            oldBook.get()
                    .setAuthorId(newBook.getAuthorId())
                    .setIsbn(newBook.getIsbn())
                    .setPrice(newBook.getPrice())
                    .setPublishDate(newBook.getPublishDate())
                    .setPublisherId(newBook.getPublisherId())
                    .setTitle(newBook.getTitle());
            bookRepository.save(oldBook.get());
        }

    }

    @DeleteMapping("/book/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@PathVariable Integer id){
        bookRepository.deleteById(id);
    }

    @GetMapping("book/author/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getBookByAuthorId(@PathVariable Integer id){
        List<Book> books = bookRepository.findBookByAuthorId(id);

        return books;
    }
}
