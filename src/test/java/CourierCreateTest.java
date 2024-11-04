import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.Const;
import ru.praktikum.courier.Courier;
import static io.restassured.RestAssured.baseURI;
import static ru.praktikum.courier.CreateCourierSteps.*;
import static ru.praktikum.courier.Credentials.*;
import static ru.praktikum.courier.ResponseSteps.*;

public class CourierCreateTest {
    @Before
    public void setUp() {
        baseURI = Const.BaseURI;
    }



    @Test
    @DisplayName("Успешное создание учетной записи курьера")
    public void successCreatedCourierTest () {
        Courier courier = Courier.random();
        Response response = sendPostRequestCreateCourierAccount(courier);
        statusCodeAndBodyCreatedCourierAccount(response);
        courierIdForDelete(courier);

        System.out.println(response.body().asString());
    }



    @Test
    @DisplayName("Ошибка при создании 2-х учетных записей с одинаковыми логинами")
    public void errorCreateIdenticalAccountsCourierTest () {
        Courier courier = Courier.random();
        Response response = sendPostRequestCreateCourierAccount(courier);
        statusCodeAndBodyCreatedCourierAccount(response);

        Response responseDouble = sendPostRequestCreateCourierAccount(courier);
        statusCodeAndBodyConflictIdenticalCourierAccount(responseDouble);
        courierIdForDelete(courier);

        System.out.println(responseDouble.body().asString());
    }

    @Test
    @DisplayName("Ошибка при создании учетной записи курьера с пустым полем \"Login\"")
    public void errorCreateAccountWithoutLoginTest () {
        Response response = sendPostRequestCreateAccountWithoutLogin();
        statusCodeAndBodyBadRequestCreateAccountWithoutData(response);


    }

    @Test
    @DisplayName("Ошибка при создании учетной записи курьера с пустым полем \"Password\"")
    public void errorCreateAccountWithoutPasswordTest () {
        Response response = sendPostRequestCreateAccountWithoutPassword();
        statusCodeAndBodyBadRequestCreateAccountWithoutData(response);

        System.out.println(response.body().asString());
    }
    @Test
    @DisplayName("Ошибка при создании учетной записи курьера с пустым полем \"firstName\"")
    public void errorCreateAccountWithoutFirstNameTest () {
        Response response = sendPostRequestCreateAccountWithoutFirstName();
        statusCodeAndBodyBadRequestCreateAccountWithoutData(response);
        deleteCourierAccount();
        System.out.println(response.body().asString());
    }
}
