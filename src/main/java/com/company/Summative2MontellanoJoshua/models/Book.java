package com.company.Summative2MontellanoJoshua.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="book")
public class Book implements Serializable {

    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String isbn;
    private Date publishDate;
    private Integer authorId;
    private String title;
    private Integer publisherId;
    private BigDecimal price;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "bookId")
    private Set<Author> authors = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "bookId")
    private Set<Publisher> publishers = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public Book setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public Book setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public Book setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
        return this;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public Book setAuthorId(Integer authorId) {
        this.authorId = authorId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    public Integer getPublisherId() {
        return publisherId;
    }

    public Book setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Book setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public Book setAuthors(Set<Author> authors) {
        this.authors = authors;
        return this;
    }

    public Set<Publisher> getPublishers() {
        return publishers;
    }

    public Book setPublishers(Set<Publisher> publishers) {
        this.publishers = publishers;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(getId(), book.getId()) && Objects.equals(getIsbn(), book.getIsbn()) && Objects.equals(getPublishDate(), book.getPublishDate()) && Objects.equals(getAuthorId(), book.getAuthorId()) && Objects.equals(getTitle(), book.getTitle()) && Objects.equals(getPublisherId(), book.getPublisherId()) && Objects.equals(getPrice(), book.getPrice()) && Objects.equals(getAuthors(), book.getAuthors()) && Objects.equals(getPublishers(), book.getPublishers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIsbn(), getPublishDate(), getAuthorId(), getTitle(), getPublisherId(), getPrice(), getAuthors(), getPublishers());
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", publishDate=" + publishDate +
                ", authorId=" + authorId +
                ", title='" + title + '\'' +
                ", publisherId=" + publisherId +
                ", price=" + price +
                ", authors=" + authors +
                ", publishers=" + publishers +
                '}';
    }
}
