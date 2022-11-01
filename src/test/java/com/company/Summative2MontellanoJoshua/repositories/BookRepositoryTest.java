package com.company.Summative2MontellanoJoshua.repositories;

import com.company.Summative2MontellanoJoshua.models.Author;
import com.company.Summative2MontellanoJoshua.models.Book;
import com.company.Summative2MontellanoJoshua.models.Publisher;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepo;
    @Autowired
    AuthorRepository authorRepo;
    @Autowired
    PublisherRepository publisherRepo;


    @Before
    public void setUp() throws Exception{
        bookRepo.deleteAll();
        authorRepo.deleteAll();
        publisherRepo.deleteAll();
    }


    @Test
    public void testAddingGettingDeletingBook() throws ParseException {
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
        author = authorRepo.save(author);

        //sets up the publisher
        Publisher publisher = new Publisher();
        publisher.setName("TOR");
        publisher.setEmail("email@gmail.com");
        publisher.setPhone("1111");
        publisher.setStreet("street");
        publisher.setCity("New York");
        publisher.setState("CA");
        publisher.setPostalCode("7777");
        publisher = publisherRepo.save(publisher);

        //sets up the book
        Book newBook = new Book();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = formatter.parse("26-10-2022");
        newBook.setTitle("A Storm of Swords");
        newBook.setPrice(BigDecimal.valueOf(16.66));
        newBook.setAuthorId(author.getId());
        newBook.setPublisherId(publisher.getId());
        newBook.setIsbn("1111111");
        newBook.setPublishDate(date);



        newBook = bookRepo.save(newBook);

        Optional<Book> newBook2 = bookRepo.findById(newBook.getId());

        //checks to make sure the correct book was added
        assertEquals(newBook, newBook2.get());


        //deletes the book from the repo
        bookRepo.deleteById(newBook.getId());

        newBook2 = bookRepo.findById(newBook.getId());

        //makes sure the book was deleted
        assertFalse(newBook2.isPresent());

    }

    @Test
    public void testGetAll(){

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
        author = authorRepo.save(author);

        //sets up the publisher
        Publisher publisher = new Publisher();
        publisher.setName("TOR");
        publisher.setEmail("email@gmail.com");
        publisher.setPhone("1111");
        publisher.setStreet("street");
        publisher.setCity("New York");
        publisher.setState("CA");
        publisher.setPostalCode("7777");
        publisher = publisherRepo.save(publisher);

        //sets up two books
        Book newBook = new Book();
        Book newBook2 = new Book();

        newBook.setTitle("A Storm of Swords");
        newBook.setPublisherId(publisher.getId());
        newBook.setAuthorId(author.getId());
        newBook.setPrice(BigDecimal.valueOf(16.66));
        newBook.setIsbn("1111111");
        newBook.setPublishDate(Date.from(Instant.now()));
        newBook2.setTitle("Mistborn the Final Empire");
        newBook2.setPublisherId(publisher.getId());
        newBook2.setAuthorId(author.getId());
        newBook2.setPrice(BigDecimal.valueOf(16.66));
        newBook2.setIsbn("1111111");
        newBook2.setPublishDate(Date.from(Instant.now()));

        bookRepo.save(newBook);
        bookRepo.save(newBook2);

        List<Book> books = bookRepo.findAll();

        //checks to make sure that the books in the repo is equal to 2
        assertEquals(2, books.size());
    }

    @Test
    public void updateBook(){
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
        author = authorRepo.save(author);

        //sets up the publisher
        Publisher publisher = new Publisher();
        publisher.setName("TOR");
        publisher.setEmail("email@gmail.com");
        publisher.setPhone("1111");
        publisher.setStreet("street");
        publisher.setCity("New York");
        publisher.setState("CA");
        publisher.setPostalCode("7777");
        publisher = publisherRepo.save(publisher);

        //sets up the book
        Book book = new Book();
        book.setTitle("Return of the King");
        book.setPublisherId(publisher.getId());
        book.setAuthorId(author.getId());
        book.setPrice(BigDecimal.valueOf(16.66));
        book.setIsbn("1111111");
        book.setPublishDate(Date.from(Instant.now()));

        //we save the book onto the repo
        book = bookRepo.save(book);
        Integer id = book.getId();

        //we change the books title
        book.setTitle("Fellowship of the Ring");

        //we save the book with the new title
        bookRepo.save(book);

        Optional<Book> foundBook = bookRepo.findById(id);


        //we make sure that the tittle was correctly updated
        if(foundBook.isPresent()){
            assertEquals("Fellowship of the Ring", foundBook.get().getTitle());
        }else{
            fail();
        }


    }

    @Test
    public void findBookByAuthorId() throws ParseException {

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
        author = authorRepo.save(author);

        //sets up the publisher
        Publisher publisher = new Publisher();
        publisher.setName("TOR");
        publisher.setEmail("email@gmail.com");
        publisher.setPhone("1111");
        publisher.setStreet("street");
        publisher.setCity("New York");
        publisher.setState("CA");
        publisher.setPostalCode("7777");
        publisher = publisherRepo.save(publisher);

        //sets up the book
        Book newBook = new Book();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = formatter.parse("26-10-2022");
        newBook.setTitle("A Storm of Swords");
        newBook.setPrice(BigDecimal.valueOf(16.66));
        newBook.setAuthorId(author.getId());
        newBook.setPublisherId(publisher.getId());
        newBook.setIsbn("1111111");
        newBook.setPublishDate(date);


        newBook = bookRepo.save(newBook);

        //we try to find the books by its author id
        List<Book> books = bookRepo.findBookByAuthorId(author.getId());

        //we make sure that the number of books returned is the number that the author has in our repo
        assertEquals(1, books.size());
    }
}
