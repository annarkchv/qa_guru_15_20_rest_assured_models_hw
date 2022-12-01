package models;

import lombok.Data;

@Data
public class ResponseModel {

    private String token, error, name, job;
    private int id;
}
