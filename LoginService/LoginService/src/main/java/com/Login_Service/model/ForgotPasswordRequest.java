package com.Login_Service.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ForgotPasswordRequest {

    private String newPassword;
    private String confirmationPassword;
}
