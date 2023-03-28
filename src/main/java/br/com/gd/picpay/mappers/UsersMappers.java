package br.com.gd.picpay.mappers;

import br.com.gd.picpay.dtos.requests.UsersRequestDTO;
import br.com.gd.picpay.dtos.responses.UsersResponseDTO;
import br.com.gd.picpay.entities.UsersEntity;
import br.com.gd.picpay.exceptions.UsersException;
import br.com.gd.picpay.exceptions.enums.UsersEnum;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class UsersMappers {

    @Autowired
    private final ModelMapper mapper;

    public UsersEntity toEntity(UsersRequestDTO request) {
        return mapper.map(request, UsersEntity.class);
    }

    public UsersResponseDTO toDto(UsersEntity entity) {
        return mapper.map(entity, UsersResponseDTO.class);
    }

    public UsersRequestDTO responseToRequest(UsersResponseDTO response) {
        return mapper.map(response, UsersRequestDTO.class);
    }

    public List<UsersResponseDTO> toDtoList(Iterable<UsersEntity> list) {
        List<UsersEntity> result = new ArrayList<>();
        list.forEach(result::add);

        return result.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public void verifyPasswordAndPasswordConfirmation(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            log.warn("passwords do not match");
            throw new UsersException(UsersEnum.PASSWORD_DO_NOT_MATCH);
        }
    }
}
