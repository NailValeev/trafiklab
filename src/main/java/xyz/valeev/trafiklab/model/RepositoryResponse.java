package xyz.valeev.trafiklab.model;

import lombok.Data;

@Data
public class RepositoryResponse {
    private int statusCode;
    private String body;

    public RepositoryResponse (int statusCode, String body){
        this.statusCode = statusCode;
        this.body = body;
    }
}
