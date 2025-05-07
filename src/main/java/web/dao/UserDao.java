package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {

    List<User> getAll();

    void add(User user);

    void delete(Long id);

    void update(User user);

    User findById(Long id);

}
