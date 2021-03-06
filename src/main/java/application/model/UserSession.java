package application.model;

public class UserSession {

    private static UserSession instance;

    private User user;

    private UserSession(User user) {
        this.user = user;
    }

    public static UserSession getInstance(User user) {
        if(instance == null) {
            instance = new UserSession(user);
        }
        return instance;
    }

    public User getUser() {
        return this.user;
    }

    public void cleanUserSession() {
        user = null;
    }

    @Override
    public String toString() {
        return user.toString();
    }


}
