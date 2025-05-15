package web.service;

import org.springframework.stereotype.Service;
import web.model.User;
import web.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
       return userRepository.findAll();
    }

    public void add(User user) {
        userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    public void update(Long id, String name, String surname, Integer age) {
     User user =  userRepository.findById(id).orElseThrow();
       user.setName(name);
       user.setSurname(surname);
       user.setAge(age);
       userRepository.save(user);
    }
}
