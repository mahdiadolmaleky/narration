package com.hit.narration.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hit.narration.config.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "nr_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Column(name = "email",unique = true)
    private String email;

    @Size(min = 11, max = 11)
    @Pattern(regexp = Constants.PHONE_NUMBER_REGEX)
    @Column(name = "phone_number",length = 11,unique = true)
    private String phoneNumber;

    @NotNull
    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @Size(max = 50)
    @Column(name = "first_name", length = 50)
    private String firstName;

    @NotNull
    @Size(max = 50)
    @Column(name = "last_name", length = 50)
    private String lastName;

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private Instant createdDate = Instant.now();

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private Instant lastModifiedDate;

    @Column(name = "last_login")
    private Instant lastLogin;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "email_confirmed")
    private Boolean emailConfirmed;

    @Column(name = "token")
    private String token;

    @Column(name="token_created_date")
    private Instant tokenCreatedDate;

    @Column(name = "token_expired")
    private Boolean tokenExpired;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "nr_user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    public User(String email, String encode, String phoneNumber, String firstName, String lastName) {
        this.email=email;
        this.password=encode;
        this.phoneNumber=phoneNumber;
        this.firstName=firstName;
        this.lastName=lastName;
    }
}
