package ndingspringboot.BlogSite.rest;

import ndingspringboot.BlogSite.domain.Authority;
import ndingspringboot.BlogSite.domain.User;
import ndingspringboot.BlogSite.service.AuthorityService;
import ndingspringboot.BlogSite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Index page controller
 */
@RestController
public class MainController {

    private static final Long ROLE_USER_AUTHORITY_ID = 2L;

    private UserService userService;

    private AuthorityService authorityService;

    @Autowired
    public MainController(UserService userService, AuthorityService authorityService) {
        this.userService = userService;
        this.authorityService = authorityService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView root() {
        return new ModelAndView("redirect:/index");
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/login-error", method = RequestMethod.GET)
    public ModelAndView loginError(Model model) {
        model.addAttribute("loginError", true);
        model.addAttribute("errorMsg", "Login failed, username or password wrong!");
        return new ModelAndView("login", "errorModel", model);
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        return new ModelAndView("register");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerUser(User user) {
        List<Authority> authorities = new ArrayList<>();
        authorities.add(authorityService.getAuthorityById(ROLE_USER_AUTHORITY_ID));
        user.setAuthorities(authorities);
        userService.saveUser(user);
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView search() {
        return new ModelAndView("search");
    }
}
