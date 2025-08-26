package org.jhegner.k8slabs.mock;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import reactor.core.publisher.Mono;

import java.util.List;

import org.jhegner.k8slabs.Product;
import org.jhegner.k8slabs.Users;

import static io.micronaut.http.MediaType.APPLICATION_JSON;

@Controller("/mockapi.io/api/v1")
public class MockApi {

    @Get(uri = "/users", processes = APPLICATION_JSON)
    public List<Users> getUsers() {
        return Mono.just(List.of(
                new Users("2025-08-11", "User 1", "https://example.com/avatar1.png", "1"),
                new Users("2025-08-11", "User 2", "https://example.com/avatar2.png", "2")
        )).block();
    }

    @Get(uri = "/products", processes = APPLICATION_JSON)
    public List<Product> getProducts() {
        return Mono.just(List.of(
                new Product("2025-08-11", "Product 1", "https://example.com/avatar1.png",
                        "1", "Description of Product 1", "100.00"),
                new Product("2025-08-11", "Product 2", "https://example.com/avatar2.png",
                        "2", "Description of Product 2", "200.00")
        )).block();
    }

}
