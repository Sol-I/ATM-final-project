package client_package.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class ClientController {

    @RequestMapping(value = {"/", "/homepage"}, method = RequestMethod.GET)
    public String homepage() {
        return "homepage";
    }

}
