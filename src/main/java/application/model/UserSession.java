package application.model;

import application.entity.User;
import application.tool.StageManager;

public class UserSession {

    private static User user;

    private UserSession() {
    }

    private static class UserSessionHolder
    {
        private final static UserSession instance = new UserSession();
    }

    public static UserSession getInstance()
    {
        return UserSession.UserSessionHolder.instance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User target){
        user = target;
    }

    public void cleanUserSession() {
        user = null;
    }

    @Override
    public String toString() {
        return user.toString();
    }


}
