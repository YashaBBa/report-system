package org.bsuir.domain;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "user_")
public class User {
    @Id
    @Type(type = "pg-uuid")
    private UUID id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "created", nullable = false)
    private LocalDateTime created;

    @Column(name = "updated", nullable = false)
    private LocalDateTime updated;

}
