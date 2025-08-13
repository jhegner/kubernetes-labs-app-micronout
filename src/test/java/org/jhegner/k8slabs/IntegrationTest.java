package org.jhegner.k8slabs;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import reactor.core.publisher.Mono;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

@MicronautTest
class IntegrationTest {

    @Inject
    EmbeddedServer server;

    @Inject
    UsersClient usersClient;

    @Inject
    ProductsClient productsClient;

    @Client("${controller.uri}") 
    @Inject HttpClient httpClient;

    @Test
    void testEmbeddedServerIsRunning(){
        assertTrue(this.server.isRunning());
    }

    @Test
    void testaEndpointAppControllerUsuarios(){

        List<Users> usuarios = Mono.from(
            this.httpClient.retrieve(HttpRequest.GET("/usuarios"), Argument.listOf(Users.class)))
            .block();

        assertNotNull(usuarios);
        assertEquals(2, usuarios.size());
    }

    @Test
    void testaEndpointAppControllerProdutos(){
        
        List<Product> products = Mono.from(
            this.httpClient.retrieve(HttpRequest.GET("/produtos"), Argument.listOf(Product.class)))
            .block();

        assertNotNull(products);
        assertEquals(2, products.size());
    }

    @Test
    void testaRequisicaoUsersEndpoint() {

        List<Users> users = usersClient.getUsers()
                .collectList().block();

        Assertions.assertNotNull(users);
        Assertions.assertEquals(2, users.size());
        Assertions.assertEquals("User 1", users.getFirst().getName());

    }

    @Test
    void testaRequisicaoProductsEndpoint() {

        List<Product> products = productsClient.getProducts()
                .collectList().block();

        Assertions.assertNotNull(products);
        Assertions.assertEquals(2, products.size());
        Assertions.assertEquals("Product 1", products.getFirst().getName());


    } 

}
