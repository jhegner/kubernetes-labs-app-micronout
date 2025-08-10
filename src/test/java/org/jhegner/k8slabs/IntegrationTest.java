package org.jhegner.k8slabs;

import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.util.List;

@MicronautTest
class IntegrationTest {

    @Inject
    EmbeddedApplication<?> application;

    @Inject
    EmbeddedServer server;

    @Inject
    UsersClient usersClient;

    @Inject
    ProductsClient productsClient;

    @Test
    void testaSeAplicacaoExecutaComSucesso() {
        Assertions.assertTrue(application.isRunning());
    }

    @Test
    void testaRequisicaoEndpointUsuarios() {
        List<Users> response = usersClient.getUsers()
                .collectList().block();
        Assertions.assertNotNull(response);
        Assertions.assertFalse(response.isEmpty());
    }

    @Test
    void testaRequisicaoEndpointProdutos() {
        List<Product> response = productsClient.getProducts()
                .collectList().block();
        Assertions.assertNotNull(response);
        Assertions.assertFalse(response.isEmpty());
    }

}
