package application.service;

import application.model.User;
import application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean authentificed(String login, String password){
        User user = userRepository.findUserByLoginAndPassword(login, password);
        if(user == null){
            return false;
        }
        return user.getUserLogin().equals(login) && user.getPassword().equals(password);
    }

}
