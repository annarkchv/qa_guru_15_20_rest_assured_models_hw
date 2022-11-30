package models;

import lombok.Data;

@Data
public class LoginRequestLombokModel {
    // String loginCredentials = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }";

    private String email, password;
}
