package br.com.gd.picpay.facades.impl;

import br.com.gd.picpay.dtos.requests.UsersRequestDTO;
import br.com.gd.picpay.dtos.responses.UsersResponseDTO;
import br.com.gd.picpay.entities.OtpEntity;
import br.com.gd.picpay.enums.OtpEnum;
import br.com.gd.picpay.exceptions.OtpException;
import br.com.gd.picpay.facades.UsersFacade;
import br.com.gd.picpay.mappers.UsersMappers;
import br.com.gd.picpay.services.OTPService;
import br.com.gd.picpay.services.UsersService;
import br.com.gd.picpay.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;

@Component
@Slf4j
public class UsersFacadeImpl implements UsersFacade {

    @Autowired
    private UsersService usersService;

    @Autowired
    private OTPService otpService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UsersMappers mappers;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public List<UsersResponseDTO> findAll(String token) {
        jwtUtil.validateToken(token);
        return mappers.toDtoList(usersService.findAll());
    }

    @Override
    public UsersResponseDTO save(UsersRequestDTO usersRequestDTO) {
        mappers.verifyPasswordAndPasswordConfirmation(usersRequestDTO.getPassword(), usersRequestDTO.getConfirmPassword());

        OtpEntity otpEntity = otpService.findCodeByEmail(usersRequestDTO.getEmail());
        if (otpEntity != null && usersRequestDTO.getOtp() != otpEntity.getCode()) {
            throw new OtpException(OtpEnum.OTP_INVALID);
        } else if (otpEntity == null) {
            throw new OtpException(OtpEnum.OTP_DO_NOT_EXIST);
        } else if (Calendar.getInstance().getTime().after(otpEntity.getExpiration())) {
            otpService.remove(otpEntity.getId());
            throw new OtpException(OtpEnum.OTP_EXPIRED);
        }

        otpService.remove(otpEntity.getId());
        usersRequestDTO.setPassword(encoder.encode(usersRequestDTO.getPassword()));
        return mappers.toDto(usersService.save(mappers.toEntity(usersRequestDTO)));
    }

    @Override
    public UsersResponseDTO findById(String token) {
        jwtUtil.validateToken(token);
        return mappers.toDto(usersService.findById(jwtUtil.getAllClaims(token).getId()));
    }

    @Override
    public UsersResponseDTO updateById(Long id, UsersRequestDTO usersRequestDTO, String token) {
        jwtUtil.validateToken(token);
        return mappers.toDto(usersService.updateById(id, mappers.toEntity(usersRequestDTO)));
    }

    @Override
    public void deleteById(Long id, String token) {
        jwtUtil.validateToken(token);
        usersService.deleteById(id);
    }
}
