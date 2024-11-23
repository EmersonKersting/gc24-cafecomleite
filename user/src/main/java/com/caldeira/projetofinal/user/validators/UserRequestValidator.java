package com.caldeira.projetofinal.user.validators;

import org.springframework.stereotype.Service;

@Service
public class UserValidator {
}
/* Criar validador para o modelo de entrada. Deverá ser chamada UserRequestValidator no pacote validators e deverá
validar a entrada do usuário. Deve ser anotada com @Component para o Spring gerenciá-la. Essa classe deverá ter um único
método com a assinatura private void validate(UserRequestModel model) e deverá lançar uma IllegalArgumentException se
caso o objeto de entrada contiver firstName ou lastName nulos ou menor do que 3 caracteres de tamanho ('firstName'
cannot be null or less than 3 characters long.). */