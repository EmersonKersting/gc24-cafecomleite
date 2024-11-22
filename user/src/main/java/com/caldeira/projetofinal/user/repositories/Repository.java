package com.caldeira.projetofinal.user.repositories;

import com.caldeira.projetofinal.user.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface Repository extends JpaRepository<UserEntity, UUID> {
}
