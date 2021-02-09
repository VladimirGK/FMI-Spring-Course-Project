package com.autodeli.web.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @Pattern(regexp = "[A-Za-z0-9]{24}")
    private String id;
    @Size(min = 2, max = 30)
    @NonNull
    @NotNull
    private String firstName;
    @Size(min = 2, max = 30)
    @NonNull
    @NotNull
    private String lastName;
    @Size(min = 2, max = 15)
    @NonNull
    @NotNull
    private String username;
    @NonNull
    @NotNull
    @Email
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NonNull
    @Size(min = 8)
    private String password;

    private boolean active = true;

    private Set<Role> roles = new HashSet<>(Arrays.asList(Role.USER, Role.ADMIN));

    private ShoppingCart shoppingCart = new ShoppingCart();

    @PastOrPresent
    private LocalDateTime created = LocalDateTime.now();
    @PastOrPresent
    private LocalDateTime modified = LocalDateTime.now();

    public User(@Size(min = 2, max = 30) @NonNull @NotNull String firstName,
                @Size(min = 2, max = 30) @NonNull @NotNull String lastName,
                @Size(min = 2, max = 15) @NonNull @NotNull String username,
                @NonNull @NotNull @Email String email,
                @NonNull @Size(min = 8) String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.toString())).collect(Collectors.toList());
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return active;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return active;
    }
}
