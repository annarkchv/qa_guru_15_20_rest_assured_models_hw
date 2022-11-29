package models.pojo;

public class LoginRequestPojoModel {
    // POJO - Plain Old Java Object
    // Java Bean - другое название для POJO
    // Java Bean - минимальный Java-класс, позволяющий создать геттеры и сеттеры и не имеющий другой функциональности

    // String loginCredentials = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }";

    private String email, password;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
