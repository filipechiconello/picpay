package br.com.gd.picpay.controllers;

import br.com.gd.picpay.dtos.requests.UsersRequestDTO;
import br.com.gd.picpay.dtos.responses.UsersResponseDTO;
import br.com.gd.picpay.facades.UsersFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UsersControllers {

    @Autowired
    private UsersFacade usersFacade;

    @PostMapping
    public ResponseEntity<UsersResponseDTO> save(@RequestBody UsersRequestDTO usersRequestDTO) {
        return new ResponseEntity<>(usersFacade.save(usersRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UsersResponseDTO>> findAll(@RequestHeader String token) {
        return new ResponseEntity<>(usersFacade.findAll(token), HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<UsersResponseDTO> findById(@RequestHeader String token) {
        return new ResponseEntity<>(usersFacade.findById(token), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsersResponseDTO> updateById(@PathVariable Long id,
                                                       @RequestBody UsersRequestDTO usersRequestDTO,
                                                       @RequestHeader String token) {
        return new ResponseEntity<>(usersFacade.updateById(id, usersRequestDTO, token), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id,
                                           @RequestHeader String token) {
        usersFacade.deleteById(id, token);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
