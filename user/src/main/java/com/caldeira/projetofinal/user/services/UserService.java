package com.caldeira.projetofinal.user.services;

import com.caldeira.projetofinal.user.repositories.UserRepository;
import com.caldeira.projetofinal.user.validators.UserRequestValidator;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserRequestValidator userRequestValidator;

    public UserService(UserRepository userRepository, UserRequestValidator userRequestValidator) {
        this.userRepository = userRepository;
        this.userRequestValidator = userRequestValidator;
    }
}
/* Criar a camada de serviços chamada UserService no pacote services. Essa classe deverá conter nossas regras de negócio.
Ela deverá ser anotada com @service para o Spring gerenciá-la. Deverá ter dois campos, private final UserRepository
userRepository e private final UserRequestValidator userRequestValidator;, um construtor com dois parâmetros, public
UserService(UserRepository userRepository, UserRequestValidator userRequestValidator) para injeção do userRepository
e do userRequestValidator à classe. Os métodos serão implementados nas próximas tarefas. */
