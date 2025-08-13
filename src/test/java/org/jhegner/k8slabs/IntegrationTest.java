package org.jhegner.k8slabs;

import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

@MicronautTest
class IntegrationTest {

    @Inject
    EmbeddedServer server;

    @Inject
    UsersClient usersClient;

    @Inject
    ProductsClient productsClient;


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
