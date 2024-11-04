package ru.praktikum.courier;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.praktikum.Const;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;



public class CreateCourierSteps {


    @Step("Запрос POST - создание учетной записи курьера")
    public static Response sendPostRequestCreateCourierAccount(Courier random) {
        Response response = given().log().all()
                .header("Content-type", "application/json")
                .baseUri(baseURI)
                .body(random)
                .when()
                .post(Const.API_COURIER_CREATE);
        return response;
    }




    @Step("Создание аккаунта курьера с пустым полем \"Login\"")
    public static Response sendPostRequestCreateAccountWithoutLogin() {
        Response response = given().log().all()
                .header("Content-type", "application/json")
                .baseUri(baseURI)
                .body(Courier.withOutLogin())
                .when()
                .post(Const.API_COURIER_CREATE);
        return response;
    }

    @Step("Создание аккаунта курьера с пустым полем \"Password\"")
    public static Response sendPostRequestCreateAccountWithoutPassword() {
        Response response = given().log().all()
                .header("Content-type", "application/json")
                .baseUri(baseURI)
                .body(Courier.withOutPassword())
                .when()
                .post(Const.API_COURIER_CREATE);
        return response;
    }

    @Step("Создание аккаунта курьера с пустым полем \"firstName\"")
    public static Response sendPostRequestCreateAccountWithoutFirstName() {
        Response response = given().log().all()
                .header("Content-type", "application/json")
                .baseUri(baseURI)
                .body(Courier.withOutFirstName())
                .when()
                .post(Const.API_COURIER_CREATE);
        return response;
    }


}
