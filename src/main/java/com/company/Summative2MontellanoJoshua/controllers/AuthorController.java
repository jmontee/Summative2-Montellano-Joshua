package com.company.Summative2MontellanoJoshua.controllers;


import com.company.Summative2MontellanoJoshua.models.Author;
import com.company.Summative2MontellanoJoshua.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;

    @PostMapping("/author")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAuthor(@RequestBody Author newAuthor){
        authorRepository.save(newAuthor);
    }

    @GetMapping("/author/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Author getAuthorById(@PathVariable Integer id){
        Optional<Author> author = authorRepository.findById(id);

        if(author.isPresent()){
            return author.get();
        }
        else{
            return null;
        }

    }

    @GetMapping("/author")
    @ResponseStatus(HttpStatus.OK)
    public List<Author> getAllAuthor(){
        return authorRepository.findAll();
    }

    @PutMapping("/author")
    @ResponseStatus(HttpStatus.OK)
    public void updateAuthor(@RequestBody Author author){
        Optional<Author> oldAuthor = authorRepository.findById(author.getId());
        if (oldAuthor.isPresent()){
            oldAuthor.get()
                    .setCity(author.getCity())
                    .setEmail(author.getEmail())
                    .setFirstName(author.getFirstName())
                    .setLastName(author.getLastName())
                    .setPhone(author.getPhone())
                    .setPostalCode(author.getPostalCode())
                    .setState(author.getState())
                    .setStreet(author.getStreet());
            authorRepository.save(oldAuthor.get());
        }
    }

    @DeleteMapping("/author/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAuthor(@PathVariable Integer id){
        authorRepository.deleteById(id);
    }


}
