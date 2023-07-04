package com.casestudymodule6.model.dto;

public class ChangePasswordDTO
{
    private String oldPassword;

    private String newPassword;


    private String confirmPassword;

    public ChangePasswordDTO() {
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
}
