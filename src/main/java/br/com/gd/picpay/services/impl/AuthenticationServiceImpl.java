package br.com.gd.picpay.services.impl;

import br.com.gd.picpay.dtos.requests.AuthenticationRequestDTO;
import br.com.gd.picpay.dtos.responses.AuthenticationResponseDTO;
import br.com.gd.picpay.entities.UsersEntity;
import br.com.gd.picpay.exceptions.AuthenticationException;
import br.com.gd.picpay.exceptions.UsersException;
import br.com.gd.picpay.exceptions.enums.AuthenticationEnum;
import br.com.gd.picpay.exceptions.enums.UsersEnum;
import br.com.gd.picpay.repositories.UsersRepository;
import br.com.gd.picpay.services.AuthenticationService;
import br.com.gd.picpay.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public AuthenticationResponseDTO generateToken(AuthenticationRequestDTO authenticationRequestDTO) {
        UsersEntity user = usersRepository.findByEmail(authenticationRequestDTO.getEmail()).orElseThrow(() -> new UsersException(UsersEnum.USER_NOT_FOUND));

        if(encoder.matches(authenticationRequestDTO.getPassword(), user.getPassword())) {
            log.info("checking username and password of user {}", authenticationRequestDTO.getEmail());
            String accessToken = jwtUtil.generateTokenJwt(user.getId(), authenticationRequestDTO.getEmail(), user.getName());
            jwtUtil.validateToken(accessToken);
            return new AuthenticationResponseDTO(accessToken);
        } else {
            log.warn("{} username or password is invalid", authenticationRequestDTO.getEmail());
            throw new AuthenticationException(AuthenticationEnum.USER_OR_PASSAWORD_IS_INVALID);
        }
    }
}