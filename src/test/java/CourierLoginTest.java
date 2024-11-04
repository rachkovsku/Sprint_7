import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.Const;
import ru.praktikum.courier.Courier;
import static io.restassured.RestAssured.baseURI;
import static ru.praktikum.courier.CreateCourierSteps.sendPostRequestCreateCourierAccount;
import static ru.praktikum.courier.Credentials.deleteCourierAccount;
import static ru.praktikum.courier.Credentials.courierId;
import static ru.praktikum.courier.Credentials.courierIdForDelete;
import static ru.praktikum.courier.LoginCourierSteps.*;
import static ru.praktikum.courier.ResponseSteps.*;

public class CourierLoginTest {
    @Before
    public void setUp() {
        baseURI = Const.BaseURI;
    }




    @Test
    @DisplayName("Успешная авторизация курьера в системе")
    public void successLoginInCourierTest() {
        Courier courier = Courier.random();
        Response response = sendPostRequestCreateCourierAccount(courier);
        statusCodeAndBodyCreatedCourierAccount(response);

        Response responseLogIn = logInCourierAccount(courier);
        statusCodeAndBodyLogInCourierAccount(responseLogIn);
        courierIdForDelete(courier);

        System.out.println(responseLogIn.body().asString());

    }


    @Test
    @DisplayName("Ошибка авторизации под несуществующим пользователем")
    public void invalidAccountLoginCourierTest() {
        Response response = invalidCourierAccount();
        statusCodeAndBodyLogInCourierAccountWithInvalidData(response);

        System.out.println(response.body().asString());

    }


    @Test
    @DisplayName("Ошибка авторизации c невалидным паролем")
    public void invalidPasswordLoginInCourierTest() {
        Courier courier = Courier.random();
        Response response = sendPostRequestCreateCourierAccount(courier);
        statusCodeAndBodyCreatedCourierAccount(response);

        Response responseInvalidPassword = logInInvalidPasswordCourierAccount(courier);
        statusCodeAndBodyLogInCourierAccountWithInvalidData(responseInvalidPassword);


        System.out.println(responseInvalidPassword.body().asString());
    }

    @Test
    @DisplayName("Ошибка авторизации c невалидным логином")
    public void invalidLoginLoginInCourierTest() {
        Courier courier = Courier.random();
        Response response = sendPostRequestCreateCourierAccount(courier);
        statusCodeAndBodyCreatedCourierAccount(response);

        Response responseInvalidLogin = logInInvalidLoginCourierAccount(courier);
        statusCodeAndBodyLogInCourierAccountWithInvalidData(responseInvalidLogin);


        System.out.println(responseInvalidLogin.body().asString());

    }
    @Test
    @DisplayName("Ошибка авторизации cо значением null в ключе \"login\"")
    public void nullLoginLoginInCourierTest() {
        Courier courier = Courier.random();
        Response response = sendPostRequestCreateCourierAccount(courier);
        statusCodeAndBodyCreatedCourierAccount(response);

        Response responseNullLogin = logInNullLoginCourierAccount(courier);
        statusCodeAndBodyLogInCourierAccountWithOutData(responseNullLogin);


        System.out.println(responseNullLogin.body().asString());

    }

    //При запросе со значением null в поле "пароль" сервер возвращает 504 ошибку, в том числе проверено через Postman.
    @Test
    @DisplayName("Ошибка авторизации cо значением null в ключе \"password\"")
    public void nullPasswordLoginInCourierTest() {
        Courier courier = Courier.random();
        Response response = sendPostRequestCreateCourierAccount(courier);
        statusCodeAndBodyCreatedCourierAccount(response);

        Response responseNullPassword = logInNullPasswordCourierAccount(courier);
        statusCodeAndBodyLogInCourierAccountWithOutData(responseNullPassword);


        System.out.println(responseNullPassword.body().asString());

    }

    @Test
    @DisplayName("Ошибка авторизации при отсутствии в теле запроса пары ключ-значение \"login\"")
    public void bodyWithOutLoginLoginInCourierTest() {
        Courier courier = Courier.random();
        Response response = sendPostRequestCreateCourierAccount(courier);
        statusCodeAndBodyCreatedCourierAccount(response);

        Response responseWithOutLogin = logInWithOutLoginCourierAccount(courier);
        statusCodeAndBodyLogInCourierAccountWithOutData(responseWithOutLogin);




    }

    //При запросе без поля "пароль" сервер возвращает 504 ошибку, в том числе проверено через Postman.
    @Test
    @DisplayName("Ошибка авторизации при отсутствии в теле запроса пары ключ-значение \"password\"")
    public void bodyWithOutPasswordLoginInCourierTest() {
        Courier courier = Courier.random();
        Response response = sendPostRequestCreateCourierAccount(courier);
        statusCodeAndBodyCreatedCourierAccount(response);

        Response responseWithOutPassword = logInWithOutPasswordCourierAccount(courier);
        statusCodeAndBodyLogInCourierAccountWithOutData(responseWithOutPassword);


        System.out.println(responseWithOutPassword.body().asString());

    }
}
