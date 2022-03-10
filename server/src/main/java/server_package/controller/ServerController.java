package server_package.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import server_package.entity.Account;
import server_package.exception.NotFoundElementException;
import server_package.service.RepositoryService;
import server_package.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@Controller
@AllArgsConstructor
public class ServerController {

    private final RepositoryService repositoryService;

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("/user/userHome")
    public ModelAndView getAccounts() throws NotFoundElementException {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = repositoryService.findUserByEmail(auth.getName());
        List<Account> accounts = repositoryService.getAccounts(user);
        Collections.sort(accounts);
        modelAndView.addObject("user", user);
        modelAndView.addObject("accounts", accounts);
        modelAndView.setViewName("user/userHome");
        return modelAndView;
    }

}
