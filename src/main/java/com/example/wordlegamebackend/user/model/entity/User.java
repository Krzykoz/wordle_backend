package com.piaskowy.urlshortenerbackend.user.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.piaskowy.urlshortenerbackend.url.model.Url;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String email;
    @NonNull
    @Setter
    private String password;
    @NonNull
    private String name;
    @NonNull
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "is_enabled")
    private boolean isEnabled = false;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Url> urls;

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final User user = (User) o;
        return id.equals(user.id)
                && email.equals(user.email)
                && password.equals(user.password)
                && name.equals(user.name)
                && lastName.equals(user.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, name, lastName);
    }
}
