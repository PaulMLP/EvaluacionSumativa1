package com.distribuida;

import com.distribuida.rest.BookRest;
import com.distribuida.service.IBookService;
import io.helidon.webserver.WebServer;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.inject.Inject;
import org.apache.webbeans.config.WebBeansContext;
import org.apache.webbeans.spi.ContainerLifecycle;

public class Main {
    static ContainerLifecycle lifecycle = null;

    @Inject
    static BookRest servicio;

    public static void main(String[] args) {
        lifecycle = WebBeansContext.currentInstance().getService(ContainerLifecycle.class);
        lifecycle.startApplication(null);


        IBookService servicio = CDI.current().select(IBookService.class)
                .get();

        BookRest bookRest = new BookRest(servicio);

        WebServer.builder()
                .routing(it -> it
                        .get("/books", bookRest::findAll)
                        .get("/books/{id}", bookRest::findById)
                        .post("/books", bookRest::insert)
                        .put("/books", bookRest::update)
                        .delete("/books/{id}", bookRest::delete)
                )
                .port(8080).build().start();
    }
}