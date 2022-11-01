package com.company.Summative2MontellanoJoshua.repositories;

import com.company.Summative2MontellanoJoshua.models.Author;
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
public class AuthorRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    PublisherRepository publisherRepository;


    @Before
    public void setUp() throws Exception{
        bookRepository.deleteAll();
        authorRepository.deleteAll();
        publisherRepository.deleteAll();
    }
    @Test
    public void addGetDeleteAuthor(){

        //sets up the author
        Author author = new Author();
        author.setFirstName("Josh");
        author.setLastName("Smith");
        author.setEmail("email@gmail.com");
        author.setPhone("1111");
        author.setStreet("street");
        author.setCity("New York");
        author.setState("CA");
        author.setPostalCode("7777");


        author = authorRepository.save(author);

        Optional<Author> newAuthor = authorRepository.findById(author.getId());

        //if the author is present then we know the add works and then
        //we make sure that the author we saved and the author we got back
        //are the same
        if(newAuthor.isPresent()){
            assertEquals(author, newAuthor.get());
        }
        else{
            fail();
        }

        authorRepository.deleteById(author.getId());

        newAuthor = authorRepository.findById(author.getId());

        //after we delete we want to make sure that once we try to get the author again
        //that they will no longer be present
        assertFalse(newAuthor.isPresent());
    }

    @Test
    public void testGetAllAuthors(){
        //sets up the first author
        Author author = new Author();
        author.setFirstName("Josh");
        author.setLastName("Smith");
        author.setEmail("email@gmail.com");
        author.setPhone("1111");
        author.setStreet("street");
        author.setCity("New York");
        author.setState("CA");
        author.setPostalCode("7777");
        author = authorRepository.save(author);

        //sets up the second author
        Author author2 = new Author();
        author2.setFirstName("James");
        author2.setLastName("Wayne");
        author2.setEmail("email@gmail.com");
        author2.setPhone("1111");
        author2.setStreet("street");
        author2.setCity("Los Angeles");
        author2.setState("CA");
        author2.setPostalCode("7777");
        author2 = authorRepository.save(author2);

        List<Author> authors = authorRepository.findAll();

        //we want to make sure that the list size is equal to 2
        assertEquals(2, authors.size());
    }

    @Test
    public void updateAuthorTest(){
        //sets up the author
        Author author = new Author();
        author.setFirstName("Josh");
        author.setLastName("Smith");
        author.setEmail("email@gmail.com");
        author.setPhone("1111");
        author.setStreet("street");
        author.setCity("New York");
        author.setState("CA");
        author.setPostalCode("7777");
        author = authorRepository.save(author);
        Integer id = author.getId();

        //changes the authors name
        author.setFirstName("Luke");
        authorRepository.save(author);
        Optional<Author> foundAuthor = authorRepository.findById(id);

        //makes sure the change goes to the database
        if(foundAuthor.isPresent()){
            assertEquals("Luke", foundAuthor.get().getFirstName());
        }
        else{
            fail();
        }
    }
}
