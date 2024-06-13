package com.distribuida.service;

import com.distribuida.db.Book;

import java.util.List;

public interface IBookService {
    List<Book> findAll();

    Book findById(Integer id);

    Book insert(Book book);

    Book update(Book book);

    void delete(Integer id);
}
