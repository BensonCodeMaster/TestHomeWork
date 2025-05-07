package web.service;

import web.model.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    void add(User user);

    void delete(Long id);

    public void update(Long id, String name, String surname, Integer age);

    User findById(Long id);
}
