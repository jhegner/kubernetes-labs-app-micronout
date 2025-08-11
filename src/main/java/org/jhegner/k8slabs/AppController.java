package org.jhegner.k8slabs;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import reactor.core.publisher.Flux;

import static io.micronaut.http.MediaType.APPLICATION_JSON;

@Controller("/kubernetes-labs/api/v1")
public class AppController {

    private final UsersClient usersClient;
    private final ProductsClient productsClient;

    public AppController(final UsersClient usersClient,
                         final ProductsClient productsClient) {
        this.usersClient = usersClient;
        this.productsClient = productsClient;
    }

    @Get(uri = "usuarios", processes = APPLICATION_JSON)
    public Flux<Users> getUsuarios() {
        return usersClient.getUsers();
    }

    @Get(uri = "produtos", processes = APPLICATION_JSON)
    public Flux<Product> getProdutos() {
        return productsClient.getProducts();
    }

}
