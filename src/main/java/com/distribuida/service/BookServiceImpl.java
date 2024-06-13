package com.distribuida.service;

import com.distribuida.db.Book;
import com.distribuida.repo.IBookRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class BookServiceImpl implements IBookService {

    @Inject
    private IBookRepo iBookRepo;

    @Override
    public List<Book> findAll() {
        return this.iBookRepo.findAll();
    }

    @Override
    public Book findById(Integer id) {
        return this.iBookRepo.findById(id);
    }

    @Override
    public Book insert(Book book) {
        return this.iBookRepo.insert(book);
    }

    @Override
    public Book update(Book book) {
        return this.iBookRepo.update(book);
    }

    @Override
    public void delete(Integer id) {
        this.iBookRepo.delete(id);
    }
}
