package com.example.codinqquiz.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
@EqualsAndHashCode(of = {"id"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id")
    protected int id;

    @Column(unique = true)
    @NotBlank(message = "User nickname is required")
    private String nickname;

    @Email(message = "Invalid email format")
    @Column(unique = true)
    private String email;

    @Column(name = "verification_code")
    private String verificationCode;

    @Column
    private String password;

    @Column
    private LocalDateTime created;

    @Column
    private LocalDateTime updated;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;


    @PrePersist
    public void onCreate() {
        this.created = LocalDateTime.now();
        this.updated = created;
    }

    @PreUpdate
    public void onUpdate() {
        this.updated = LocalDateTime.now();
    }


    public boolean isVerified(){
        return verificationCode==null;
    }

    public boolean isAdmin(){
        return this.roles.stream()
                .map(Role::getName)
                .anyMatch(role->role.equalsIgnoreCase("admin"));
    }

}


