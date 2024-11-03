package com.microuser.mail.entity;


public class UserVerificationDTO {

    private Integer idUserVerification;
    private int code;
    private UserEntityDTO user;

    public Integer getIdUserVerification() {
        return idUserVerification;
    }

    public void setIdUserVerification(Integer idUserVerification) {
        this.idUserVerification = idUserVerification;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public UserEntityDTO getUser() {
        return user;
    }

    public void setUser(UserEntityDTO user) {
        this.user = user;
    }

}
