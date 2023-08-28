package com.example.userservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.userservice.dto.UserDto;
import com.example.userservice.model.User;
import com.example.userservice.service.UserService;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "*")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> listar() {
        logger.info("Listar todos los usuario");

        return userService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User listarPorId(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/username/{username}")
    @ResponseStatus(HttpStatus.OK)
    public User listarPorUsername(@PathVariable String username) {
        logger.info("Listar usuario: " + username);

        return userService.findByUsername(username);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User crear(@RequestBody UserDto userDto) {
        return userService.save(new User(null, userDto.getEmail(), userDto.getPassword(), true, userDto.getNombre(),
                userDto.getApellido(), userDto.getEmail()));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User actualizar(@PathVariable Long id, @RequestBody UserDto UserDto) {
        // return userService.update(new User(id, bookDto.getTitle(),
        // bookDto.getAuthor()));
        return null;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        userService.delete(id);
    }

}
