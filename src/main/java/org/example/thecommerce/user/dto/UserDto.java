package org.example.thecommerce.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserDto {

    @NotBlank(message = "아이디는 필수 입력값입니다.")
    @Size(min = 1, max = 20, message = "아이디는 1~20글자까지 허용됩니다.")
    @ApiModelProperty(required = true, value = "아이디")
    private String id;

    @NotBlank(message = "패스워드는 필수 입력값입니다.")
    @ApiModelProperty(required = true, value="패스워드")
    private String pwd;

    @NotBlank(message = "닉네임은 필수 입력값입니다.")
    @ApiModelProperty(required = true, value="닉네임")
    private String nick;

    @NotBlank(message = "전화번호는 필수 입력값입니다.")
    @ApiModelProperty(required = true, value="전화번호")
    private String phone;

    @NotBlank(message = "이메일은 필수 입력값입니다.")
    @Email(message = "유효하지 않은 이메일 형식입니다.")
    @ApiModelProperty(required = true, value="이메일")
    private String email;
}
