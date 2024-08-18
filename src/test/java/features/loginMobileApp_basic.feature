#@loginMobileApp
Feature: Validate login functionality for mobile application. This feature contains mobile menu, owner option, firebase token


  @otploginAppsc1
  Scenario Outline: Submit an API to generate OTP for an user for mobile application

    Given user submit "loginAPI" with "username" and "password"
    When add requestOTP payload
    Then user submit "requestOTP" with "POST" request with "token" for requestOTP
    Then get ApiResponse for validation
    And verify "statusCode" is 200 for requestOTP
    Then validate ApiResponse execution time
    Then validate "<customerId>" and "<customerName>" for requestOTP
    Then verify "OTP" and "token" are generated for requestOTP
    Then validate "message" is "OTP Sent Successfully" for requestOTP
  Examples:
    |customerId|customerName|
    |  326338  |   Som C    |


#  @firebaseloginAppsc2
  Scenario: Submit an API to create firebase token for mobile device

    Given user submit "loginAPI" with "username" and "password"
    When add firebaseToken payload for FirebaseToken
    Then user submit "createFirebaseToken" API with "POST" request for FirebaseToken
    Then validate API call is success with 200 status code for FirebaseToken
    And verify "statusCode" is 200 for FirebaseToken
    Then validate "message" is "SUCCESS"
    And verify "firebaseToken" is populated