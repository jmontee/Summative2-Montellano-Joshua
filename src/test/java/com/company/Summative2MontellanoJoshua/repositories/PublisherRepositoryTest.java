package com.company.Summative2MontellanoJoshua.repositories;

import com.company.Summative2MontellanoJoshua.models.Publisher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PublisherRepositoryTest {
    @Autowired
    PublisherRepository publisherRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Before
    public void setUp(){
        bookRepository.deleteAll();
        publisherRepository.deleteAll();
        authorRepository.deleteAll();
    }

    @Test
    public void testAddGetDelete(){
        //sets up publisher
        Publisher publisher = new Publisher();
        publisher.setName("TOR");
        publisher.setEmail("email@gmail.com");
        publisher.setPhone("1111");
        publisher.setStreet("street");
        publisher.setCity("New York");
        publisher.setState("CA");
        publisher.setPostalCode("7777");
        publisher = publisherRepository.save(publisher);

        Optional<Publisher> foundPublisher = publisherRepository.findById(publisher.getId());

        if(foundPublisher.isPresent()){
            assertEquals(publisher, foundPublisher.get());
        }
        else{
            fail();
        }

        //deletes the publisher
        publisherRepository.deleteById(publisher.getId());

        //tries to receive the old publisher
        foundPublisher = publisherRepository.findById(publisher.getId());

        //make sure the pub
        assertFalse(foundPublisher.isPresent());

    }

    @Test
    public void testGetAll(){
        //sets up publisher
        Publisher publisher = new Publisher();
        publisher.setName("TOR");
        publisher.setEmail("email@gmail.com");
        publisher.setPhone("1111");
        publisher.setStreet("street");
        publisher.setCity("New York");
        publisher.setState("CA");
        publisher.setPostalCode("7777");
        publisher = publisherRepository.save(publisher);


        //sets up the second publisher
        Publisher publisher2 = new Publisher();
        publisher2.setName("Simon & Schuster");
        publisher2.setEmail("email@gmail.com");
        publisher2.setPhone("1111");
        publisher2.setStreet("street");
        publisher2.setCity("Los Angeles");
        publisher2.setState("CA");
        publisher2.setPostalCode("7777");
        publisher2 = publisherRepository.save(publisher2);

        List<Publisher> publisherList = publisherRepository.findAll();

        //tests to make sure that the list is of size 2
        assertEquals(2, publisherList.size());
    }

    @Test
    public void testUpdate(){
        //sets up publisher
        Publisher publisher = new Publisher();
        publisher.setName("TOR");
        publisher.setEmail("email@gmail.com");
        publisher.setPhone("1111");
        publisher.setStreet("street");
        publisher.setCity("New York");
        publisher.setState("CA");
        publisher.setPostalCode("7777");
        publisher = publisherRepository.save(publisher);

        Integer id = publisher.getId();

        //sets a new name for the publisher
        publisher.setName("New publisher Name");
        publisherRepository.save(publisher);


        Optional<Publisher> foundPublisher = publisherRepository.findById(id);

        if(foundPublisher.isPresent()){
            //Tests to make sure that the name was updated in the database
            assertEquals("New publisher Name", foundPublisher.get().getName());
        }
        else{
            fail();
        }

    }
}
