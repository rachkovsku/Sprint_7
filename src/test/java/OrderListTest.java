import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.Const;
import static io.restassured.RestAssured.baseURI;
import static ru.praktikum.order.OrdersListSteps.*;

public class OrderListTest {
    @Before
    public void setUp() {
        baseURI = Const.BaseURI;
    }

    @Test
    @DisplayName("Успешное получение списка заказов без courierId")
    public void orderListTest() {
        Response response = getListOrdersWithoutCourierId();
        statusCodeAndBodygetListOrders(response);
        int amount = getAmountOrdersBody(response);

        System.out.println(response.body().asString());
        System.out.println(amount);
    }
}
