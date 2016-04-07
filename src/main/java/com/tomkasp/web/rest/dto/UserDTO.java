package com.tomkasp.web.rest.dto;

import com.tomkasp.domain.Authority;
import com.tomkasp.domain.User;

import org.hibernate.validator.constraints.Email;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
/**
 * A DTO representing a user, with his authorities.
 */
public class UserDTO {

    public static final int PASSWORD_MIN_LENGTH = 5;
    public static final int PASSWORD_MAX_LENGTH = 100;
    public static final int PERCENTAGE_MAX = 100;

    @Pattern(regexp = "^[a-z0-9]*$")
    @NotNull
    @Size(min = 1, max = 50)
    private String login;

    @NotNull
    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
    private String password;

    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    @Email
    @Size(min = 5, max = 100)
    private String email;

    private Integer calories;

    @Max(PERCENTAGE_MAX)
    private Integer proteins;

    @Max(PERCENTAGE_MAX)
    private Integer fats ;

    @Max(PERCENTAGE_MAX)
    private Integer carbohydrates;

    private boolean activated = false;

    @Size(min = 2, max = 5)
    private String langKey;

    private Set<String> authorities;

    private Set<ExternalAccountDTO> externalAccounts = new HashSet<>();

    public UserDTO() {
    }

    public UserDTO(User user) {
        this(user.getLogin(), null, user.getFirstName(), user.getLastName(),
            user.getEmail(), user.getActivated(), user.getLangKey(),
            user.getAuthorities().stream().map(Authority::getName)
                .collect(Collectors.toSet()));
    }

    public UserDTO(String login, String password, String firstName, String lastName, String email, String langKey, Integer calories, Integer proteins, Integer fats, Integer carbohydrates,
                   Set<String> roles, Set<ExternalAccountDTO> externalAccounts) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.langKey = langKey;
        this.authorities = roles;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
        if (externalAccounts != null) {
            this.externalAccounts.addAll(externalAccounts);
        }
    }

    public UserDTO(String login, String password, String firstName, String lastName, String email, String langKey, Integer calories, Integer proteins, Integer fats, Integer carbohydrates,
                   Set<String> roles) {
        this(login, password, firstName, lastName, email, langKey, calories, proteins, fats, carbohydrates, roles, null);
    }

    public UserDTO(String login, String firstName, String lastName, String email, ExternalAccountDTO externalAccount) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        if (externalAccount != null) {
            this.externalAccounts.add(externalAccount);
        }
    }

    public UserDTO(String login, String password, String firstName, String lastName,
        String email, boolean activated, String langKey, Set<String> authorities) {

        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.activated = activated;
        this.langKey = langKey;
        this.authorities = authorities;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActivated() {
        return activated;
    }

    public String getLangKey() {
        return langKey;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public Set<ExternalAccountDTO> getExternalAccounts() {
        return externalAccounts;
    }

    public Integer getCalories() {
        return calories;
    }

    public Integer getCarbohydrates() {
        return carbohydrates;
    }

    public Integer getFats() {
        return fats;
    }

    public Integer getProteins() {
        return proteins;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
            "activated=" + activated +
            ", login='" + login + '\'' +
            ", password='" + password + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", calories=" + calories +
            ", proteins=" + proteins +
            ", fats=" + fats +
            ", carbohydrates=" + carbohydrates +
            ", langKey='" + langKey + '\'' +
            ", authorities=" + authorities +
            '}';
    }
}
