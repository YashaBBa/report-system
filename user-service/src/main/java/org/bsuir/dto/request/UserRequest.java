package org.bsuir.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    @Email(regexp = "(\\w+)@(\\w+\\.)(\\w+)(\\.\\w+)*", message = "INVALID EMAIL TYPE")
    private String email;
    @Length(min = 8, message = "INVALID PASSWORD LENGTH")
    private String password;
    private String username;
    private String name;
    private String surname;
}
