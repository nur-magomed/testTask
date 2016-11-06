package ru.innopolis.nur.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by nur on 04.11.16.
 */
@Component
public class UserBean {

    /**
     * User ID
     */
    private int userID;

    /**
     * User name
     */
    private String userName;

    /**
     * User password
     */
    private String userPassword;

    /**
     * getter for user ID
     * @return
     */
    public int getUserID() {
        return userID;
    }

    /**
     *  seeter for user ID
     * @param userID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * getter for user name
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     * seeter for user name
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * getter for user password
     * @return
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * setter fot user password
     * @param userPassword
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
