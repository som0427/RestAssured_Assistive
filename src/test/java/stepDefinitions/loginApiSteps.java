package stepDefinitions;

import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.payload.Login_Payload;
import resources.testUtils.CommonUtils;
import resources.testUtils.Endpoints;
import resources.testUtils.GetApiResponseObject;

import java.io.FileNotFoundException;
import static io.restassured.RestAssured.given;
public class loginApiSteps extends CommonUtils {

    RequestSpecification reqspec;
    ResponseSpecification respec;
    Login_Payload payload = new Login_Payload();
    static Response response;
    private final GetApiResponseObject getApiResponseObject;

    //This will ensure single CommonUtils instance will be created throughout the execution.
    //Calling getInstance() ensures you are interacting with that single shared instance.
    //This is particularly useful if CommonUtils maintains state that should be consistent across different parts of your application.i.e. Logging.txt
    private final CommonUtils commonUtils = CommonUtils.getInstance();

    public loginApiSteps() {
        this.getApiResponseObject = GetApiResponseObject.getInstance();
    }

    @When("user submit {string} with {string} request for login")
    public void userSubmitWithPOSTRequestForLogin(String endpoint, String POST) throws FileNotFoundException {

        reqspec = given().spec(commonUtils.requestSpec()).body(payload.loginPayload());
        respec = responseSpec();

        Endpoints ep = Endpoints.valueOf(endpoint);
        response = reqspec.when().post(ep.getValOfEndpoint()).then().spec(respec).extract().response();
        getApiResponseObject.setResponse(response);
    }

}