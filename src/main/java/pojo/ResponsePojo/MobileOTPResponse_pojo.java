package pojo.ResponsePojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MobileOTPResponse_pojo {
    private String statusCode, message;
    private ResponseData responseData;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseData getResponseData() {
        return responseData;
    }

    public void setResponseData (ResponseData responseData) {
        this.responseData = responseData;
    }

    public static class ResponseData {
        private String appServerUrl,customerId,projectId,customerName,token;
        @JsonProperty("OTP")
        private String OTP;

        public String getAppServerUrl() {
            return appServerUrl;
        }

        public void setAppServerUrl(String appServerUrl) {
            this.appServerUrl = appServerUrl;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getOTP() {
            return OTP;
        }

        public void setOTP(String OTP) {
            this.OTP = OTP;
        }

        public String getProjectId() {
            return projectId;
        }

        public void setProjectId(String projectId) {
            this.projectId = projectId;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
