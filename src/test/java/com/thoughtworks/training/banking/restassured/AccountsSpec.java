package com.thoughtworks.training.banking.restassured;

import com.thoughtworks.training.banking.Application;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.core.Is.is;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(Application.class)
@IntegrationTest
public class AccountsSpec {

  @Test
  public void should_return_1_balance_for_qinyu() {
    given().pathParam("username", "qinyu").when().get("/{username}/accounts")
        .then().log().ifValidationFails()
        .statusCode(200).content("size()", is(1),
        "number", hasItems("1002"),
        "balances[0].size()", is(2),
        "balances[0].currency", hasItems("cny", "usd"),
        "balances[0].amount", hasItems(80000, 0));
  }

  @Test
  public void should_return_3_balances_for_heaton() {
    given().pathParam("username", "heaton").when().get("/{username}/accounts")
        .then().log().ifValidationFails()
        .statusCode(200).content("size()", is(2),
        "number", hasItems("1001", "2001"),
        "balances[0].size()", is(2),
        "balances[0].currency", hasItems("cny", "usd"),
        "balances[0].amount", hasItems(100000, 30000),
        "balances[1].size()", is(1),
        "balances[1].currency", hasItems("cny"),
        "balances[1].amount", hasItems(200000));
  }
}
