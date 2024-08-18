package resources.testUtils;

//This is enum class which contains all the API Endpoints
public enum Endpoints {

//  login endpoints #
    loginAPI("/retailitynew/login/api/auth/login"),
    requestOTP("/retailitynew/login/api/auth/requestOTP"),
    createFirebaseToken("/retailitynew/mobile/api/users/createFirebaseToken"),
    logoutApi("/retailitynew/login/api/logout"),

//  settings enpoints #
    assistant("retailitynew/setting/api/configuration/assistant"),
    getFieldsInSetting("retailitynew/setting/api/customType/getFieldsInSetting");
    private final String endpoint;

    Endpoints(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getValOfEndpoint() {
        return endpoint;
    }
}