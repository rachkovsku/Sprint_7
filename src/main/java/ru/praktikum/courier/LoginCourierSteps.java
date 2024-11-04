package ru.praktikum.courier;

import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static ru.praktikum.Const.API_COURIER_LOGIN;

public class LoginCourierSteps {
    @Step("Вход в существующий аккаунт курьера")
    public static Response logInCourierAccount(Courier courier) {
        Gson gson = new Gson();
        String json = gson.toJson(courier);
        Response response = given().log().all()
                .header("Content-type", "application/json")
                .baseUri(baseURI)
                .body(json)
                .when()
                .post(API_COURIER_LOGIN);
        return response;
    }

    @Step("Авторизация несуществующего аккаунта")
    public static Response invalidCourierAccount() {
        CourierLogin courierLogin = new CourierLogin("netShustrik", "555555");
        Response response = given().log().all()
                .header("Content-type", "application/json")
                .baseUri(baseURI)
                .body(courierLogin)
                .when()
                .post(API_COURIER_LOGIN);
        return response;
    }

    @Step("Авторизация курьера с невалидным паролем")
    public static Response logInInvalidPasswordCourierAccount(Courier courier) {
        CourierLogin invalidPassword = new CourierLogin(courier.getLogin(), "741852");
        Response response = given().log().all()
                .header("Content-type", "application/json")
                .baseUri(baseURI)
                .body(invalidPassword)
                .when()
                .post(API_COURIER_LOGIN);
        return response;
    }

    @Step("Авторизация курьера с невалидным логином")
    public static Response logInInvalidLoginCourierAccount(Courier courier) {
        CourierLogin invalidLogin = new CourierLogin("netShustrika", courier.getPassword());
        Response response1 = given().log().all()
                .header("Content-type", "application/json")
                .baseUri(baseURI)
                .body(invalidLogin)
                .when()
                .post(API_COURIER_LOGIN);
        return response1;
    }

    @Step("Авторизация курьера со значением null в поле \"Login\"")
    public static Response logInNullLoginCourierAccount(Courier courier) {
        CourierLogin nullLogin = new CourierLogin(null, courier.getPassword());
        Response response1 = given().log().all()
                .header("Content-type", "application/json")
                .baseUri(baseURI)
                .body(nullLogin)
                .when()
                .post(API_COURIER_LOGIN);
        return response1;
    }
    @Step("Авторизация курьера со значением null в поле \"Password\"")
    public static Response logInNullPasswordCourierAccount(Courier courier) {
        CourierLogin nullPassword = new CourierLogin(courier.getLogin(), null);
        Response response1 = given().log().all()
                .header("Content-type", "application/json")
                .baseUri(baseURI)
                .body(nullPassword)
                .when()
                .post(API_COURIER_LOGIN);
        return response1;
    }

    @Step("Авторизация курьера при отсутствии в теле запроса пары ключ-значение \"Login\"")
    public static Response logInWithOutLoginCourierAccount(Courier courier) {
        CourierLogin withoutLogin = new CourierLogin("", courier.getPassword());
        Response response = given().log().all()
                .header("Content-type", "application/json")
                .baseUri(baseURI)
                .body(withoutLogin)
                .when()
                .post(API_COURIER_LOGIN);
        return response;
    }

    @Step("Авторизация курьера при отсутствии в теле запроса пары ключ-значение \"Password\"")
    public static Response logInWithOutPasswordCourierAccount(Courier courier) {
        CourierLogin withoutPassword = new CourierLogin(courier.getLogin(), "");
        Response response1 = given().log().all()
                .header("Content-type", "application/json")
                .baseUri(baseURI)
                .body(withoutPassword)
                .when()
                .post(API_COURIER_LOGIN);
        return response1;
    }
}
