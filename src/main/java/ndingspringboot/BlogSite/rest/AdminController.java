package ndingspringboot.BlogSite.rest;

import ndingspringboot.BlogSite.utils.Menu;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/admins")
public class AdminController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listUsers(Model model) {
        List<Menu> list = new ArrayList<>();
        list.add(new Menu("User Management", "/users"));
        list.add(new Menu("Role Management", "/roles"));
        list.add(new Menu("Blog Management", "/blogs"));
        list.add(new Menu("Comment Management", "/commits"));
        model.addAttribute("list", list);
        return new ModelAndView("/admins/index", "model", model);
    }
}
