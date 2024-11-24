package com.caldeira.projetofinal.user.services;

import com.caldeira.projetofinal.user.entities.UserEntity;
import com.caldeira.projetofinal.user.models.request.UserRequestModel;
import com.caldeira.projetofinal.user.models.response.UserResponseModel;
import com.caldeira.projetofinal.user.repositories.UserRepository;
import com.caldeira.projetofinal.user.validators.UserRequestValidator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserRequestValidator userRequestValidator;

    public UserService(UserRepository userRepository, UserRequestValidator userRequestValidator) {
        this.userRepository = userRepository;
        this.userRequestValidator = userRequestValidator;
    }

    public List<UserResponseModel> getAll() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream().map(user -> new UserResponseModel(user.getId(), user.getFirstName(),
                user.getLastName(), user.getCreationDate())).collect(Collectors.toList());
    }

    public UserResponseModel getById(UUID id) {
        return userRepository.findById(id)
                .map(user -> new UserResponseModel(user.getId(), user.getFirstName(),
                        user.getLastName(), user.getCreationDate()))
                .orElse(null);
    }

    public UserResponseModel create(UserRequestModel requestModel) {
        userRequestValidator.validate(requestModel);

        UserEntity userEntity = userRepository.save(new UserEntity(
                null, requestModel.getFirstName(),
                requestModel.getLastName(), LocalDateTime.now()
        ));
        return new UserResponseModel(userEntity.getId(), userEntity.getFirstName(),
                userEntity.getLastName(), userEntity.getCreationDate());

    }
}
