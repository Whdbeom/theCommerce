package org.example.thecommerce.user.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;


@Table(name = "MEMBER")
@Entity(name = "member")
@Data
@DynamicUpdate
public class User {

    @Id
    @SequenceGenerator(name = "userIdGenerator", sequenceName = "SEQ_NO", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userIdGenerator")
    private Integer no;
    private String id;
    private String pwd;
    private String nick;
    private String phone;
    private String email;
    @Column(name = "ENROLL_DATE")
    private LocalDateTime enrollDate;

    @PrePersist
    public void prePersist() {
        enrollDate = LocalDateTime.now();
    }
}
