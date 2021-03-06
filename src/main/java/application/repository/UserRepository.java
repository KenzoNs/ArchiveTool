package application.repository;

import application.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findById(int id);

    User findByUserLogin(String login);

    @Query("SELECT u FROM User u WHERE u.userLogin = :userLogin AND u.password = :password")
    User findUserByLoginAndPassword(@Param("userLogin") String login, @Param("password") String password);

}


