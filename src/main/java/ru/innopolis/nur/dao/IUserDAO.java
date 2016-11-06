package ru.innopolis.nur.dao;

import ru.innopolis.nur.model.UserBean;

/**
 * Created by nur on 04.11.16.
 */
public interface IUserDAO {

    /**
     * Method of interface for adding a user
     * @param userBean
     * @return
     */
    public UserBean addUser(UserBean userBean);

    /**
     * Method of interface for logging to the system
     * @param userBean
     * @return
     */
    public UserBean login(UserBean userBean);
}
