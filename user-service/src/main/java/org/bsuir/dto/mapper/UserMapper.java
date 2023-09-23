package org.bsuir.dto.mapper;

import org.bsuir.domain.User;
import org.bsuir.dto.request.UserRequest;
import org.bsuir.dto.response.UserResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.Collections;
@Service
public class UserMapper {
    public UserRepresentation toKeycloackUserRepresentation(UserRequest userRequestDto) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setEnabled(true);
        userRepresentation.setUsername(userRequestDto.getUsername());
        userRepresentation.setEmail(userRequestDto.getEmail());
        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(userRequestDto.getPassword());
        userRepresentation.setCredentials(Collections.singletonList(passwordCred));
        return userRepresentation;
    }

    public UserResponse toUserResponseDto(User user) {

        return new UserResponse()
                .builder()
                .userId(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .username(user.getUsername())
                .surname(user.getSurname())
                .creationDate(user.getCreated())
                .updateDate(user.getUpdated())
                .build();

    }


    public User toUser(UserRequest requestDto) {
        return User.builder()
                .username(requestDto.getUsername())
                .email(requestDto.getEmail())
                .name(requestDto.getName())
                .surname(requestDto.getSurname())
                .build();
    }


}
