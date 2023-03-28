package br.com.gd.picpay.facades;

import br.com.gd.picpay.dtos.requests.UsersRequestDTO;
import br.com.gd.picpay.dtos.responses.UsersResponseDTO;

import java.util.List;

public interface UsersFacade {

    List<UsersResponseDTO> findAll(String token);
    UsersResponseDTO save(UsersRequestDTO usersRequestDTO);
    UsersResponseDTO findById(String token);
    UsersResponseDTO updateById(Long id, UsersRequestDTO usersRequestDTO, String token);
    void deleteById(Long id, String token);
}