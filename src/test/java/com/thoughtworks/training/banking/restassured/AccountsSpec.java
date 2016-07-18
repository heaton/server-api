package com.thoughtworks.training.banking.restassured;

import com.thoughtworks.training.banking.Application;
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
    public void should_create_new_account() {
        given().queryParam("user", "qinyu").queryParam("name", "Yu's account")
                .when().post("/accounts")
                .then().statusCode(200)
                .content("userName", is("qinyu"), "name", is("Yu's account"));
    }

    @Test
    public void should_get_account_by_user_name() {
        given().queryParam("user", "qinyu").queryParam("name", "Yu's account").post("/accounts");
        given().queryParam("user", "qinyu")
                .when().get("/accounts")
                .then().log().ifValidationFails()
                .statusCode(200).content("size()", is(1),
                "userName[0]", is("qinyu"),
                "name[0]", is("Yu's account"));
    }

    @Test
    public void should_get_multiple_accounts_by_user_name() {
        given().queryParam("user", "heaton").queryParam("name", "Heaton's 1st account").post("/accounts");
        given().queryParam("user", "heaton").queryParam("name", "Heaton's 2nd account").post("/accounts");

        given().queryParam("user", "heaton")
                .when().get("/accounts")
                .then().log().ifValidationFails()
                .statusCode(200).content("size()", is(2),
                "userName", hasItems(is("heaton"), is("heaton")),
                "name", hasItems("Heaton's 1st account", "Heaton's 2nd account"));
    }
}
