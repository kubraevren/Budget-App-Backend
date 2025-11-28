package com.kubraevren.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class UserEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true, nullable=false)
    private String username;

    @Column(unique=true, nullable=false)
    private String email;

    @Column(nullable=false)
    private String password; // BCrypt hashed

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles = Set.of("USER");

    @Column(name="create_time")
    private LocalDateTime createdAt = LocalDateTime.now();

}
