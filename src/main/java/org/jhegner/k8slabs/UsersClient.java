package org.jhegner.k8slabs;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import reactor.core.publisher.Flux;

@Client("${mockapi.base-url}")
public interface UsersClient {

    @Get("/users")
    Flux<Users> getUsers();
}
