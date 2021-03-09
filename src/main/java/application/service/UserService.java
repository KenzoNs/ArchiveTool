package application.service;

import application.entity.User;
import application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User authentificed(String login, String password){
        return userRepository.findUserByLoginAndPassword(login, password);
    }

}
