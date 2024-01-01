package com.Login_Service.model;

import com.Login_Service.entities.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="passwordResetToken")
public class PasswordResetToken {
    @Id
    private String id;
    private String token;
    private LocalDateTime expDateTime;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",referencedColumnName = "userId",nullable = false)
    private User user;
}
