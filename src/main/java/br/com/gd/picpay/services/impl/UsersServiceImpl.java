package br.com.gd.picpay.services.impl;

import br.com.gd.picpay.entities.UsersEntity;
import br.com.gd.picpay.exceptions.UsersException;
import br.com.gd.picpay.exceptions.enums.UsersEnum;
import br.com.gd.picpay.repositories.UsersRepository;
import br.com.gd.picpay.services.UsersService;
import br.com.gd.picpay.utils.VerifyExistsInTheBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private VerifyExistsInTheBase verifyExistsInTheBase;

    @Override
    public List<UsersEntity> findAll() {
        log.info("listing all users");
        return usersRepository.findAll();
    }

    @Override
    public UsersEntity save(UsersEntity usersEntity) {
        if (usersEntity.getBalance().doubleValue() < 0) {
            throw new UsersException(UsersEnum.BALANCE_CANNOT_BE_LESS_THAN_ZERO);
        }
        verifyExistsInTheBase.verifyUsers(usersEntity.getName(), usersEntity.getLastName(), usersEntity.getDocument(), usersEntity.getEmail());
        UsersEntity result = usersRepository.save(usersEntity);
        result.setPassword("**********");
        log.info("registering a new user {}", result);
        return result;
    }

    @Override
    public UsersEntity findById(Long id) {
        log.info("listing user by id - {}", id);
        return usersRepository.findById(id).orElseThrow(() -> new UsersException(UsersEnum.USER_NOT_FOUND));
    }

    @Override
    public UsersEntity updateById(Long id, UsersEntity usersEntity) {
        log.info("updating user by id - {} - new values - {}", id, usersEntity);
        findById(id);
        usersEntity.setId(id);
        return usersRepository.save(usersEntity);
    }

    @Override
    public Optional<UsersEntity> getByEmail(String email) {
        log.info("listing user by email - {}", email);
        return usersRepository.findByEmail(email);
    }

    @Override
    public UsersEntity findByName(String name) {
        log.info("listing user by name - {}", name);
        return usersRepository.findByName(name);
    }

    @Override
    public void deleteById(Long id) {
        log.info("deleting user by id - {}", id);
        findById(id);
        usersRepository.deleteById(id);
    }
}
