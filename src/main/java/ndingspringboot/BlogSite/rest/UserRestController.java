package ndingspringboot.BlogSite.rest;

import ndingspringboot.BlogSite.domain.User;
import ndingspringboot.BlogSite.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/users")
public class UserRestController {

    private UserRepository userRepository;

    @Autowired
    public UserRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping()
    public ModelAndView list(Model model) {
        model.addAttribute("userList",  userRepository.findAll());
        model.addAttribute("title", "User Management");
        return new ModelAndView("users/list", "userModel", model);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ModelAndView view(@PathVariable("id") Long id, Model model) {
        User user = userRepository.findById(id).orElse(null);
        model.addAttribute("user", user);
        model.addAttribute("titile", "View User");
        return new ModelAndView("users/view", "userModel", model);
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public  ModelAndView creatForm(Model model) {
        model.addAttribute("user", new User(null, null));
        model.addAttribute("title", "Create User");
        return new ModelAndView("users/form", "userModel", model);
    }

    @RequestMapping(method = RequestMethod.POST)
    public  ModelAndView saveOrUpdateUser(User user) {
        user = userRepository.save(user);
        return new ModelAndView("redirect:/users");
    }

    @RequestMapping(value ="/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id") Long id) {
        userRepository.deleteById(id);
        return new ModelAndView("redirect:/users");
    }

    @RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
    public ModelAndView modify(@PathVariable("id") Long id, Model model) {
        User user = userRepository.findById(id).orElse(null);
        model.addAttribute("user", user);
        model.addAttribute("titile", "Edit User");
        return new ModelAndView("users/form", "userModel", model);
    }

}
