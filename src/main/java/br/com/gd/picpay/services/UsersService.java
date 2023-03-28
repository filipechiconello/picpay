package br.com.gd.picpay.services;

import br.com.gd.picpay.entities.UsersEntity;

import java.util.List;
import java.util.Optional;

public interface UsersService {

    List<UsersEntity> findAll();

    UsersEntity save(UsersEntity usersEntity);

    UsersEntity findById(Long id);

    UsersEntity updateById(Long id, UsersEntity usersEntity);

    Optional<UsersEntity> getByEmail(String email);

    UsersEntity findByName(String name);

    void deleteById(Long id);
}
