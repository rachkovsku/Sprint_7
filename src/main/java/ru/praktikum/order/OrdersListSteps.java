package ru.praktikum.order;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static ru.praktikum.Const.API_ORDERS;
import static ru.praktikum.Const.OK200;

public class OrdersListSteps {
    @Step("Сравнение количества заказов в теле ответа с количеством заказов, отображаемых в ключе\"limit\"")
    public static int getAmountOrdersBody(Response response) {
        List<String> amountOrders = response.then().extract().path("orders.id");
        int amount = amountOrders.size();
        response.then().assertThat().body("pageInfo.limit",is(amount));
        return amount;
    }

    @Step("Код и статус ответа 200 Ok. Тело ответа содержит номер \"id\" заказа в системе")
    public static void statusCodeAndBodygetListOrders(Response response) {
        response.then().statusCode(OK200)
                .and().assertThat().body("orders.id", notNullValue());
    }

    @Step("Get запрос списка заказов без указания id курьера")
    public static Response getListOrdersWithoutCourierId() {
        Response response = given().log().all()
                .header("Content-type", "application/json")
                .baseUri(baseURI)
                .when()
                .get(API_ORDERS);
        return response;
    }
}
