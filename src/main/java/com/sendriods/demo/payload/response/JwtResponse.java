package com.sendriods.demo.payload.response;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private Integer age;

    private String email;

    public JwtResponse(String accessToken, Long id, Integer age, String username, String email) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.age = age;
        this.email = email;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
