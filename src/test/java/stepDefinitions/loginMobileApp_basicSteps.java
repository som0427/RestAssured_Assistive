package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import pojo.ResponsePojo.LoginResponse_pojo;
import pojo.ResponsePojo.MobileOTPResponse_pojo;
import resources.payload.Login_Payload;
import resources.testUtils.CommonUtils;
import resources.testUtils.Endpoints;
import resources.testUtils.GetApiResponseObject;
import utilities.GetProperty;

import java.io.FileNotFoundException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class loginMobileApp_basicSteps extends CommonUtils {

    RequestSpecification reqspec;
    ResponseSpecification resspec;
    Login_Payload payload = new Login_Payload();
    JsonPath json;
    String bearerToken=null;
    Response response;
    LoginResponse_pojo response_token;
    MobileOTPResponse_pojo OTPresponseToken;
    private final GetApiResponseObject getApiResponseObject;

    public loginMobileApp_basicSteps() {
        this.getApiResponseObject = GetApiResponseObject.getInstance();
    }


    //1st Scenario
    @Given("user submit {string} with {string} and {string}")
    public void userSubmitWithAnd(String endpoint, String username, String password) throws FileNotFoundException {
        username = GetProperty.value("login_username");
        password = GetProperty.value("login_password");
        String login_Payload = String.format("{ \"username\": \"%s\", \"password\": \"%s\" }", username, password);
        Endpoints ep = Endpoints.valueOf(endpoint);
        response_token = given().spec(requestSpec()).body(login_Payload)
                .post(ep.getValOfEndpoint()).then().extract().as(LoginResponse_pojo.class);
        bearerToken = response_token.getToken();
    }
    @When("add requestOTP payload")
    public void addRequestOTPPayload() throws FileNotFoundException {
            reqspec = given().spec(requestSpec()).body(payload.loginMobilegetOTPPayload());
            resspec = responseSpec();
    }

    @Then("user submit {string} with {string} request with {string} for requestOTP")
    public void userSubmitWithRequestWithForRequestOTP(String endpoint, String httpMethod, String token) throws FileNotFoundException {
        //Constructor will be called with the value of 'endpoint' which will be passed in feature file (i.e. here passed loginAPI)
        Endpoints ep = Endpoints.valueOf(endpoint);
        if (httpMethod.equalsIgnoreCase("POST")) {
            response = reqspec.when().header("Authorization", "Bearer " + bearerToken).queryParam("projectId", GetProperty.value("OTPProjectId"))
                    .post(ep.getValOfEndpoint()).then().extract().response();
        }
    }

    @Then("get ApiResponse for validation")
    public void getApiResponseForValidation() {
        getApiResponseObject.setResponse(response);
        OTPresponseToken = response.as(MobileOTPResponse_pojo.class);
    }
    @And("verify {string} is {int} for requestOTP")
    public void verifyIsForRequestOTP(String statusCode, int expCode) {
        expCode = Integer.parseInt(OTPresponseToken.getStatusCode());
        json = new JsonPath(response.asString());
        assertEquals(json.getString(statusCode), String.valueOf(expCode));
    }

    @Then("validate {string} and {string} for requestOTP")
    public void validateAndForRequestOTP(String customerId, String customerName) {
        String exp_customerId = OTPresponseToken.getResponseData().getCustomerId();
        String exp_customerName = OTPresponseToken.getResponseData().getCustomerName();
        Assert.assertEquals(customerId, exp_customerId);
        Assert.assertEquals(customerName, exp_customerName);
    }

    @Then("verify {string} and {string} are generated for requestOTP")
    public void verifyAndAreGeneratedForRequestOTP(String OTP, String Token) {
        OTP = OTPresponseToken.getResponseData().getOTP();
        Token = OTPresponseToken.getResponseData().getToken();
    }

    @Then("validate {string} is {string} for requestOTP")
    public void validateIsForRequestOTP(String keyMessage, String messageValue) {
        keyMessage = OTPresponseToken.getMessage();
        Assert.assertEquals(keyMessage, messageValue);
    }



    //2nd Scenario
    @When("add firebaseToken payload for FirebaseToken")
    public void addFirebaseTokenPayloadForFirebaseToken() {
    }

    @Then("user submit {string} API with {string} request for FirebaseToken")
    public void userSubmitAPIWithRequestForFirebaseToken(String arg0, String arg1) {

    }

    @Then("validate API call is success with {int} status code for FirebaseToken")
    public void validateAPICallIsSuccessWithStatusCodeForFirebaseToken(int arg0) {
    }

    @And("verify {string} is {int} for FirebaseToken")
    public void verifyIsForFirebaseToken(String arg0, int arg1) {
    }

    @Then("validate {string} is {string}")
    public void validateIs(String arg0, String arg1) {
    }

    @And("verify {string} is populated")
    public void verifyIsPopulated(String arg0) {
    }
}
