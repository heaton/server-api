package com.thoughtworks.training.banking.restassured;

import com.thoughtworks.training.banking.Application;
import com.thoughtworks.training.banking.model.Transaction;
import com.thoughtworks.training.banking.model.TransactionAccount;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by yqin on 7/18/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(Application.class)
@IntegrationTest
public class TransferSpec {

  private Transaction getTransaction() {
    TransactionAccount fromAccount = new TransactionAccount("1001", "cny");
    TransactionAccount toAccount = new TransactionAccount("2001", "cny");

    return new Transaction(fromAccount, toAccount, new BigDecimal(500));
  }

  @Test
  public void should_transfer_500_from_1001_to_2001() {
    given().contentType(ContentType.JSON).body(getTransaction()).
        when().post("/transfer").
        then().statusCode(200);

    given().pathParam("username", "heaton").when().get("/{username}/accounts")
        .then().log().ifValidationFails()
        .statusCode(200).content("balances[0][0].amount", is(99500),
        "balances[1][0].amount", is(200500));
  }
}
