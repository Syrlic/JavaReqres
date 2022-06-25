package reqres.steps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import java.net.http.HttpResponse;

public class ValidationSteps {
    @Step("Verify status code {expectedStatusCode}")
    public static void verifyStatusCode(HttpResponse<String> response, int expectedStatusCode) {
        Assertions.assertEquals(expectedStatusCode, response.statusCode());
    }

    @Step("Verify response body contains following string {validationString}")
    public static void verifyResponseHasValidStringInside(HttpResponse<String> response, String validationString) {
        Assertions.assertTrue(response.body().contains(validationString),
                String.format("expecting that response body %s contains the following string %s ", response.body(), validationString));
    }

    @Step("Verify response body is empty")
    public static void verifyResponseHasNoBody(HttpResponse<String> response) {
        Assertions.assertTrue(response.body().isEmpty());
    }
}
