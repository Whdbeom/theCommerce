package org.example.thecommerce.user.dto;

import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
public class UserListDto {

    private String id;
    private String nick;
    private String phone;
    private String email;
    private LocalDateTime enrollDate;
}
