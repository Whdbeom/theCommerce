package org.example.thecommerce.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class UserUpdateDto {

    @ApiModelProperty(required = true, value="패스워드")
    private String pwd;

    @ApiModelProperty(required = true, value="닉네임")
    private String nick;

    @ApiModelProperty(required = true, value="전화번호")
    private String phone;

    @Email(message = "유효하지 않은 이메일 형식입니다.")
    @ApiModelProperty(required = true, value="이메일")
    private String email;
}
