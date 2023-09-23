package org.bsuir.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private UUID userId;
    private String email;
    private String password;
    private String username;
    private String name;
    private String surname;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
}

