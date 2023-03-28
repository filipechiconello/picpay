package br.com.gd.picpay.utils;

import br.com.gd.picpay.exceptions.UsersException;
import br.com.gd.picpay.exceptions.enums.UsersEnum;
import br.com.gd.picpay.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VerifyExistsInTheBase {

    @Autowired
    private UsersRepository usersRepository;

    public void verifyUsers(String name, String lastName, String document, String email) {
      usersRepository.findAll().forEach(usersEntity -> {
          if (usersEntity.getName().equals(name)) {
              throw new UsersException(UsersEnum.NAME_ALREADY_EXISTS_IN_THE_BASE);
          }

          if (usersEntity.getLastName().equals(lastName)) {
              throw new UsersException(UsersEnum.LAST_NAME_ALREADY_EXISTS_IN_THE_BASE);
          }

          if (usersEntity.getDocument().equals(document)) {
              throw new UsersException(UsersEnum.DOCUMENT_ALREADY_EXISTS_IN_THE_BASE);
          }

          if (usersEntity.getEmail().equals(email)) {
              throw new UsersException(UsersEnum.EMAIL_ALREADY_EXISTS_IN_THE_BASE);
          }
      });
    }
}
