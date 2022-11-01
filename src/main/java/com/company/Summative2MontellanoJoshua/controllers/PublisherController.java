package com.company.Summative2MontellanoJoshua.controllers;

import com.company.Summative2MontellanoJoshua.models.Publisher;
import com.company.Summative2MontellanoJoshua.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PublisherController {

    @Autowired
    private PublisherRepository publisherRepository;

    @PostMapping("/publisher")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPublisher(@RequestBody Publisher newPublisher){
        publisherRepository.save(newPublisher);
    }

    @GetMapping("/publisher/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Publisher getPublisherById(@PathVariable Integer id){
        Optional<Publisher> publisher = publisherRepository.findById(id);

        if(publisher.isPresent()){
            return publisher.get();
        }

        return null;
    }

    @GetMapping("/publisher")
    @ResponseStatus(HttpStatus.OK)
    public List<Publisher> getAllPublishers(){
        return publisherRepository.findAll();
    }

    @PutMapping("/publisher")
    @ResponseStatus(HttpStatus.OK)
    public void updatePublisher(@RequestBody Publisher publisher){
        Optional<Publisher> oldPublisher = publisherRepository.findById(publisher.getId());

        if(oldPublisher.isPresent()){
            oldPublisher.get()
                    .setCity(publisher.getCity())
                    .setEmail(publisher.getEmail())
                    .setName(publisher.getName())
                    .setPhone(publisher.getPhone())
                    .setState(publisher.getState())
                    .setStreet(publisher.getStreet())
                    .setPostalCode(publisher.getPostalCode());

            publisherRepository.save(oldPublisher.get());
        }
    }

    @DeleteMapping("/publisher/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePublisher(@PathVariable Integer id){
        publisherRepository.deleteById(id);
    }





}
