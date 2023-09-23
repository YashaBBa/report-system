package org.bsuir.service;

import org.bsuir.domain.User;
import org.bsuir.dto.request.UserRequest;

public interface UserService {
    public String createUserGetUserId(UserRequest user);
}
