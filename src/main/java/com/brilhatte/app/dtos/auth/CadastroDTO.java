package com.brilhatte.app.dtos.auth;

import com.brilhatte.app.enums.UserRoleEnum;

public record CadastroDTO (String username, String password, UserRoleEnum role) {
}
