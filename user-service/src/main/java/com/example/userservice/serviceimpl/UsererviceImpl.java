package com.example.userservice.serviceimpl;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.example.userservice.exception.RegistroNoEncontradoException;
import com.example.userservice.model.Role;
import com.example.userservice.model.User;
import com.example.userservice.service.UserService;

@Service
public class UsererviceImpl implements UserService {

    private List<User> list;

    public UsererviceImpl() {

        Role roleUser = new Role(1l, "ROLE_USER");
        Role roleAdmin = new Role(1l, "ROLE_ADMIN");

        list = Arrays.asList(
                new User(1l, "user@email.com", "12345", true, "Juan", "Matinez", "user@email.com",
                        Arrays.asList(roleUser, roleAdmin)),
                new User(2l, "user2@email.com", "12345", true, "Maria", "Matinez", "user2@email.com",
                        Arrays.asList(roleUser)));
    }

    @Override
    public List<User> findAll() {
        return list;
    }

    @Override
    public User findById(Long id) {

        return list.stream()
                .filter(usuario -> Objects.equals(usuario.getId(), id))
                .findFirst()
                .orElseThrow(() -> new RegistroNoEncontradoException("Registro no encontrado"));
    }

    @Override
    public User save(User user) {
        user.setId((long) list.size() + 1);
        list.add(user);
        return user;
    }

    @Override
    public User update(User user) {

        // int index = list.indexOf(book);
        // if (index == -1) {
        // throw new RegistroNoEncontradoException("Registro no encontrado");
        // }
        // list.set(index, book);
        // return book;

        return user;
    }

    @Override
    public void delete(Long id) {

        User user = list.stream()
                .filter(user1 -> Objects.equals(user1.getId(), id))
                .findFirst()
                .orElseThrow(() -> new RegistroNoEncontradoException("Registro no encontrado"));

        list.remove(user);
    }

}
