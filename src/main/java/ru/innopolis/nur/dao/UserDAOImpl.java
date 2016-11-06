package ru.innopolis.nur.dao;

import org.apache.log4j.Logger;
import ru.innopolis.nur.connection.DBconn;
import ru.innopolis.nur.model.UserBean;

import java.sql.*;

/**
 * Created by nur on 04.11.16.
 */
public class UserDAOImpl implements IUserDAO{
    private Logger logger = Logger.getLogger(UserDAOImpl.class);
    private Connection conn;

    /**
     * Initialising connection
     */
    public UserDAOImpl() {
        conn = DBconn.getConnection();
        logger.info("Получили соединение с БД");
    }

    /**
     * Method of for adding a user
     * @param userBean
     * @return
     */
    @Override
    public UserBean addUser(UserBean userBean) {
        try{
            String query = "INSERT INTO users_tbl(user_name, user_password) " +
                    "VALUES(?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement( query );
            preparedStatement.setString( 1, userBean.getUserName() );
            preparedStatement.setString( 2, userBean.getUserPassword() );
            try {
                preparedStatement.executeUpdate();
                logger.info("Пользователь добавлен в БД");
            } catch (SQLIntegrityConstraintViolationException e) {
                userBean = null;
                logger.error("Пользователь с таким именем уже существует: " +e);
            }
            preparedStatement.close();

        } catch (SQLException e) {
            logger.error("Ошибка добавления пользователя в БД: " + e);
            e.printStackTrace();
        } catch (Exception ee){
            logger.error("Ошибка добавления пользователя в БД: " + ee);
            ee.printStackTrace();
        }
        return userBean;
    }

    /**
     * Method of for logging in to a system
     * @param userBean
     * @return
     */
    @Override
    public UserBean login(UserBean userBean) {
        try{
            String queryLogin = "SELECT * FROM users_tbl WHERE user_name=? AND user_password=?";
            PreparedStatement preparedStatement = conn.prepareStatement( queryLogin );
            preparedStatement.setString(1, userBean.getUserName() );
            preparedStatement.setString(2, userBean.getUserPassword() );
            ResultSet rs = preparedStatement.executeQuery();
            boolean notEmpty=rs.next();

            if (notEmpty) {
                userBean.setUserName(rs.getString("user_name"));
                userBean.setUserPassword(rs.getString("user_password"));
                preparedStatement.close();
                rs.close();
                logger.info("Пользователь успешно вошел в систему");

            } else {
                logger.warn("Такого пользователя не существует");
                preparedStatement.close();
                rs.close();
                userBean = null;
            }

        } catch (SQLException e) {
            logger.error("Ошибка входа пользователя в систему: " + e);
            e.printStackTrace();
        }
        return userBean;
    }
}
