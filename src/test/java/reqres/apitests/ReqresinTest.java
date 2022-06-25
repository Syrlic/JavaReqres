package reqres.apitests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import reqres.steps.APISteps;
import reqres.utils.Constants;

import java.net.http.HttpResponse;

import static reqres.steps.ValidationSteps.*;

@Tag("API")
@DisplayName("Reqres tests")
public class ReqresinTest {
    @Test
    @DisplayName("Get List of Users")
    public void getListOfUsers() {
        HttpResponse<String> response = APISteps.getListRequestWithoutParameters(Constants.USERS);
        verifyStatusCode(response, 200);
        verifyResponseHasValidStringInside(response, "\"page\":1,\"per_page\":6,\"total\":12,\"total_pages\":2");
        verifyResponseHasValidStringInside(response, "[{\"id\":1,\"email\":\"george.bluth@reqres.in\"");
        verifyResponseHasValidStringInside(response, "{\"id\":6,\"email\":\"tracey.ramos@reqres.in\",");
    }

    @Test
    @DisplayName("Get Single User")
    public void getSingleUser() {
        HttpResponse<String> response = APISteps.getItemRequest(Constants.USERS, "3");
        verifyStatusCode(response, 200);
        verifyResponseHasValidStringInside(response, "\"id\":3,\"email\":\"emma.wong@reqres.in\",\"first_name\":\"Emma\"");
    }

    @Test
    @DisplayName("Single User not found")
    public void getSingleUserNotFound() {
        HttpResponse<String> response = APISteps.getItemRequest(Constants.USERS, "23");
        verifyStatusCode(response, 404);
        verifyResponseHasValidStringInside(response, "");
    }

    @Test
    @DisplayName("Get List of Resources")
    public void getListOfResources() {
        HttpResponse<String> response = APISteps.getRequestWithParameters(Constants.RESOURCE, "2");
        verifyStatusCode(response, 200);
        verifyResponseHasValidStringInside(response, "\"page\":2,\"per_page\":6,\"total\":12,\"total_pages\":2,\"data\":");
        verifyResponseHasValidStringInside(response, "\"id\":7,\"name\":\"sand dollar\"");
        verifyResponseHasValidStringInside(response, "\"id\":12,\"name\":\"honeysuckle\"");
    }

    @Test
    @DisplayName("Get Single Resource")
    public void getSingleResource() {
        HttpResponse<String> response = APISteps.getItemRequest(Constants.RESOURCE, "2");
        verifyStatusCode(response, 200);
        verifyResponseHasValidStringInside(response, "\"id\":2,\"name\":\"fuchsia rose\"");
    }

    @Test
    @DisplayName("Single Resource not found")
    public void getSingleResourceNotFound() {
        HttpResponse<String> response = APISteps.getItemRequest(Constants.RESOURCE, "23");
        verifyStatusCode(response, 404);
        verifyResponseHasValidStringInside(response, "");
    }

    @Test
    @DisplayName("Successful register")
    public void registerSuccessful() {

        String body = "{\"email\": \"eve.holt@reqres.in\",\n" + " \"password\": \"pistol\"}";

        HttpResponse<String> response = APISteps.postRequestWithJsonBody(Constants.REGISTER, body);
        verifyResponseHasValidStringInside(response, "{\"id\":4,\"token\":\"QpwL5tke4Pnpja7X4\"}");
    }

    @Test
    @DisplayName("Unsuccessful register")
    public void registerUnsuccessful() {

        String body = "{\"email\": \"sydney@fife\"}";

        HttpResponse<String> response = APISteps.postRequestWithJsonBody(Constants.REGISTER, body);
        verifyStatusCode(response, 400);
        verifyResponseHasValidStringInside(response, "{\"error\":\"Missing password\"}");
    }

    @Test
    @DisplayName("Update User with PUT method")
    public void updateUserWithPUTMethod() {

        String body = "{\"name\": \"morpheus\",\n" + " \"job\": \"zion resident\"}";

        HttpResponse<String> response = APISteps.putRequestToCertainAddress(Constants.USERS, "2", body);
        verifyStatusCode(response, 200);
        verifyResponseHasValidStringInside(response, "\"name\":\"morpheus\",\"job\":\"zion resident\",\"updatedAt\":");
    }

    @Test
    @DisplayName("Update User with PATCH Method")
    public void updateUserWithPATCHMethod() {

        String body = "{\"name\": \"morpheus\",\n" + " \"job\": \"zion resident\"}";

        HttpResponse<String> response = APISteps.patchRequestToCertainAddress(Constants.USERS, "2", body);
        verifyStatusCode(response, 200);
        verifyResponseHasValidStringInside(response, "\"name\":\"morpheus\",\"job\":\"zion resident\",\"updatedAt\":");
    }

    @Test
    @DisplayName("Delete User")
    public void deleteSomeUser() {
        HttpResponse<String> response = APISteps.deleteRequestToCertainAddress(Constants.USERS, "2");
        verifyStatusCode(response, 204);
        verifyResponseHasNoBody(response);
    }
}
