package com.thoughtworks.training.banking.restassured;

import com.thoughtworks.training.banking.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(Application.class)
@IntegrationTest
public class GreetingSpec {

  @Test
  public void should_get_greeting() {
    expect().body("content", equalTo("Hello, World!")).when().get("/greeting");
  }

  @Test
  public void get_greeting_with_name() {
    given().queryParam("name", "Heaton")
      .when().get("/greeting")
      .then().body("content", equalTo("Hello, Heaton!"));
  }

}
