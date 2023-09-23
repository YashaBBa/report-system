package org.bsuir.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bsuir.domain.User;
import org.bsuir.dto.mapper.UserMapper;
import org.bsuir.dto.request.UserRequest;
import org.bsuir.dto.response.UserResponse;
import org.bsuir.repo.UserRepository;
import org.bsuir.service.UserService;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final Keycloak keycloak;
    @Value("${idm.realm-name}")
    private String realmName;
    private String defaultRole = "USER";

    private final UserRepository userRepository;

    @Override
    public String createUserGetUserId(UserRequest userRequestDto) {
        UserRepresentation userRepresentation = userMapper.toKeycloackUserRepresentation(userRequestDto);
        RealmResource realmResource = keycloak.realm(realmName);
        UsersResource usersResource = realmResource.users();
        String userUUID;

        Response response = usersResource.create(userRepresentation);
        userUUID = CreatedResponseUtil.getCreatedId(response);

        // Adding Default Application Role To Keyckloack user
        UserResource userResource = usersResource.get(userUUID);
        List<RoleRepresentation> roles = realmResource.roles().list();

        RoleRepresentation managerRealmRole = realmResource.roles().get(defaultRole).toRepresentation();
        userResource.roles().realmLevel().add(List.of(managerRealmRole));

        //Create local user
        User user = userMapper.toUser(userRequestDto);
        user.setId(UUID.fromString(userUUID));
        user.setCreated(LocalDateTime.now());
        user.setUpdated(LocalDateTime.now());

        userRepository.save(user);


        return userUUID;

    }
}
