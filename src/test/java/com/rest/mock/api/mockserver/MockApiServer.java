package com.rest.mock.api.mockserver;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

public class MockApiServer {

    public static void main(String[] args) {
        WireMockServer wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(9090));
        wireMockServer.start();

        // User Registration
        wireMockServer.stubFor(WireMock.post(WireMock.urlEqualTo("/users/register"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"userId\":\"12345\", \"message\":\"User registered successfully\"}")
                        .withStatus(200)));

        // User Login
        wireMockServer.stubFor(WireMock.post(WireMock.urlEqualTo("/users/login"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"token\":\"abcde\", \"message\":\"Login successful\"}")
                        .withStatus(200)));

        // Search Books
        wireMockServer.stubFor(WireMock.get(WireMock.urlMatching("/books\\?search=.*"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("[{\"bookId\":\"1\",\"title\":\"Book 1\",\"author\":\"Author 1\",\"price\":10.0}]")
                        .withStatus(200)));

        // Add to Cart
        wireMockServer.stubFor(WireMock.post(WireMock.urlMatching("/users/\\d+/cart"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"message\":\"Book added to cart\"}")
                        .withStatus(200)));

        // Checkout
        wireMockServer.stubFor(WireMock.post(WireMock.urlMatching("/users/\\d+/checkout"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"message\":\"Checkout successful\", \"orderId\":\"67890\"}")
                        .withStatus(200)));
    }
}

