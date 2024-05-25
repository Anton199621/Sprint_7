package courier;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;

public class CreateCourierTest {
    private String courierId;
    private Courier courier;
    private final CourierMethods courierMethods = new CourierMethods();
    private final CourierChecks check = new CourierChecks();

    @Test
    @DisplayName("create courier")
    @Description("positive test")
    public void createCourier() {
        courier = Courier.random();
        ValidatableResponse createResponse = courierMethods.createCourier(courier);
        check.checkCreateSuccessfully(createResponse);

        CourierCredentials creds = CourierCredentials.from(courier);
        ValidatableResponse loginResponse = courierMethods.loginCourier(creds);
        courierId = String.valueOf(check.checkLoginSuccessfully(loginResponse));
    }

    @Test
    @DisplayName("create courier again")
    @Description("negative test")
    public void createCourierTwin() {
        courier = Courier.random();
        courierMethods.createCourier(courier);

        CourierCredentials creds = CourierCredentials.from(courier);
        ValidatableResponse loginResponse = courierMethods.loginCourier(creds);
        courierId = String.valueOf(check.checkLoginSuccessfully(loginResponse));

        Courier courierTwin = Courier.from(courier);
        ValidatableResponse createResponse = courierMethods.createCourier(courierTwin);
        check.checkCreateTwin(createResponse);


    }


    @Test
    @DisplayName("create courier without Password")
    @Description("negative test")
    public void createCourierWithoutPassword() {
        courier = Courier.random();
        Courier courierWithoutPassword = Courier.from(courier);
        courierWithoutPassword.setPassword(null);

        ValidatableResponse createResponse = courierMethods.createCourier(courierWithoutPassword);
        check.checkCreateUnSuccessfully(createResponse);
    }


    @After
    @DisplayName("delete courier after test")
    @Description("clear data")
    public void deleteCourier() {
        if (courierId != null) {
            CourierRemoval courierRemoval = new CourierRemoval(courierId);

            ValidatableResponse removalResponse = courierMethods.deleteCourier(courierRemoval);
            check.checkRemovalSuccessfully(removalResponse);
        }
    }


}