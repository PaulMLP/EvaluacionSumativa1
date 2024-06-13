package com.distribuida.repo;

import com.distribuida.db.Book;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class BookRepoImpl implements IBookRepo {

    @Inject
    private EntityManager entityManager;

    @Override
    public List<Book> findAll() {
        return entityManager.createQuery("Select b from Book b", Book.class).getResultList();
    }

    @Override
    public Book findById(Integer id) {
        return entityManager.find(Book.class, id);
    }

    @Override
    public Book insert(Book book) {
        entityManager.getTransaction().begin();
        entityManager.persist(book);
        entityManager.getTransaction().commit();
        return book;
    }

    @Override
    public Book update(Book book) {
        entityManager.getTransaction().begin();
        entityManager.merge(book);
        entityManager.getTransaction().commit();
        return book;
    }

    @Override
    public void delete(Integer id) {
        entityManager.getTransaction().begin();
        entityManager.remove(this.findById(id));
        entityManager.getTransaction().commit();
    }
}
