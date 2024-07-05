package com.felipe.ms_authN_authZ_labcenter.dtos;

import com.felipe.ms_authN_authZ_labcenter.entities.UserRole;

public record LoginResponseDTO(
        String tokenJWT,
        UserRole role
) {
}
