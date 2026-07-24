package org.example.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LoginControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getBaseUrl() {
        return "http://localhost:" + port;
    }

    @Test
    void testLoginPageLoading() {
        ResponseEntity<String> response = restTemplate.getForEntity(getBaseUrl() + "/login", String.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        // بررسی می‌کنیم که خروجی HTML حاوی عناصر صفحه لاگین باشد
        assertThat(response.getBody()).contains("صفحه ورود به سیستم");
        assertThat(response.getBody()).contains("name=\"username\"");
    }

    @Test
    void testSuccessfulLoginIntegrationFlow() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", "admin");
        map.add("password", "password123");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        // ارسال درخواست POST واقعی به سرور
        ResponseEntity<String> response = restTemplate.postForEntity(getBaseUrl() + "/login", request, String.class);

        // پس از لاگین موفق، برنامه باید ریدایرکت (302) به /dashboard بدهد
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FOUND);
        assertThat(response.getHeaders().getLocation()).isNotNull();
        assertThat(response.getHeaders().getLocation().getPath()).isEqualTo("/dashboard");
    }
}
