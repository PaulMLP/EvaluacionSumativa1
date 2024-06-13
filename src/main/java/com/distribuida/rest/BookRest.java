package com.distribuida.rest;

import com.distribuida.db.Book;
import com.distribuida.service.IBookService;
import com.google.gson.Gson;
import io.helidon.webserver.http.ServerRequest;
import io.helidon.webserver.http.ServerResponse;
import jakarta.inject.Inject;

public class BookRest {

    static Gson gson = new Gson();

    IBookService servicio;

    public BookRest(IBookService servicio) {
        this.servicio = servicio;
    }

    public void findAll(ServerRequest req, ServerResponse res) {
        res.send(gson.toJson(servicio.findAll()));
    }

    public void findById(ServerRequest req, ServerResponse res) {
        res.send(gson.toJson(servicio.findById(Integer.valueOf(req.path().pathParameters().get("id")))));
    }

    public void insert(ServerRequest req, ServerResponse res) {

        Book nuevoBook = gson.fromJson(req.content().as(String.class), Book.class);

        if (nuevoBook == null) {
            res.send("Datos vacios");
        }

        res.send(gson.toJson(servicio.insert(nuevoBook)));
 }

    public void update(ServerRequest req, ServerResponse res) {
        var bookActualizado = gson.fromJson(req.content().as(String.class), Book.class);

        var book = servicio.findById(bookActualizado.getId());

        if (book != null) {
            book.setIsbn(bookActualizado.getIsbn());
            book.setTitle(bookActualizado.getTitle());
            book.setAuthor(bookActualizado.getAuthor());
            book.setPrice(bookActualizado.getPrice());
            res.send(gson.toJson(servicio.update(book)));
        }
    }

    public void delete(ServerRequest req, ServerResponse res) {
        servicio.delete(Integer.valueOf(req.path().pathParameters().get("id")));
        res.send("Se eliminó");
    }

}
