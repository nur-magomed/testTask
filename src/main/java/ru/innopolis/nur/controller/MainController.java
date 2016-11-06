package ru.innopolis.nur.controller;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.innopolis.nur.dao.IUserDAO;
import ru.innopolis.nur.dao.UserDAOImpl;
import ru.innopolis.nur.model.UserBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by nur on 04.11.16.
 */
@Controller
@Scope("session")
public class MainController {


    private Logger logger = Logger.getLogger(MainController.class);

    private IUserDAO customerDAO;
    public MainController() {
        customerDAO = new UserDAOImpl();
    }

    /**
     * Returns the unsigned user to sign-in page
     * @return
     */
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("warn", "Необходимо ввести учётные данные.");
        modelAndView.setViewName("sign-in");
        logger.info(" Необходимо ввести учётные данные. ");
        return modelAndView;
    }


    /**
     * Sign-in method, takes user to sign-in page
     * @return
     */
    @RequestMapping(value = "sign-in", method = RequestMethod.GET)
    public ModelAndView signinFirst() {

        ModelAndView modelAndView = new ModelAndView();
        logger.info("переход на страницу входа. ");
        modelAndView.setViewName("sign-in");
        return modelAndView;
    }

    /**
     * method for login. It takes following three parameters.
     * And passes username and login to method that checks validation
     * @param request
     * @param userName
     * @param userPassword
     * @return
     */
    @RequestMapping(value = "welcome", method = RequestMethod.POST)
    public ModelAndView loginForm(HttpServletRequest request,
      @RequestParam(value="username", required=false) String userName,
      @RequestParam(value="password", required=false) String userPassword){

        ModelAndView modelAndView = new ModelAndView();
        UserBean userBean = new UserBean();
        userBean.setUserName(userName);
        userBean.setUserPassword(userPassword);

        userBean = customerDAO.login(userBean);

        if(userBean!=null ){
            modelAndView.addObject("user", userBean);
            HttpSession session = request.getSession();
            session.setAttribute("user", userBean);
            modelAndView.setViewName("welcome");
            logger.info("Пользователь зашел в систему.");
        }

        else {
            modelAndView.addObject("error", "Имя пользователя и пароль не подходят.");
            modelAndView.setViewName("sign-in");
            logger.warn("Ошибка: Имя пользователя и пароль не подходят");
        }

        return modelAndView;
    }

    /**
     * method for registering the users. It passes user name and password to register dao.
     *
     * @param request
     * @param userName
     * @param userPassword
     * @return
     */
    @RequestMapping(value = "sign-up", method = RequestMethod.POST)
    public ModelAndView signUpForm(HttpServletRequest request,
                                  @RequestParam(value="username", required=false) String userName,
                                  @RequestParam(value="password", required=false) String userPassword){
        ModelAndView modelAndView = new ModelAndView();

        UserBean userBean = new UserBean();
        userBean.setUserName(userName);
        userBean.setUserPassword(userPassword);

        //Добавляю пользователя
        userBean =  customerDAO.addUser(userBean);
        if (userBean!= null) {
            modelAndView.addObject("user", userBean);
            HttpSession session = request.getSession();
            session.setAttribute("user", userBean);
            modelAndView.setViewName("welcome");
            logger.info("Пользователь зарегистрирован.");
        }
        else{
            logger.warn("Ошибка: Пользователь с таким именем уже зарегистрирован");
            modelAndView.addObject("error", "Пользователь с таким именем уже зарегистрирован");
            modelAndView.setViewName("sign-in");
        }

        return modelAndView;
    }

    /**
     * method for loging out from the system.
     * It closes the session
     * @param request
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public ModelAndView logOut(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        request.getSession().invalidate();
        logger.info("Вы вышли из приложения.");
        modelAndView.addObject("logout", "Вы вышли из приложения.");
        modelAndView.setViewName("sign-in");
        return modelAndView;
    }

    /**
     * Method takes a user to the sign-up page
     * @return
     */
    @RequestMapping(value = "sign-up", method = RequestMethod.GET)
    public ModelAndView signupPage() {
        logger.info("переход на страницу регистрации.");
        ModelAndView model = new ModelAndView();
        model.setViewName("sign-up");
        return model;
    }


}
