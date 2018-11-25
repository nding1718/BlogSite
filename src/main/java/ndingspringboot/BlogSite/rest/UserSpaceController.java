package ndingspringboot.BlogSite.rest;

import ndingspringboot.BlogSite.domain.User;
import ndingspringboot.BlogSite.service.UserService;
import ndingspringboot.BlogSite.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/u")
public class UserSpaceController {

    // Two services that we need
    private UserService userService;

    private UserDetailsService userDetailsService;

    @Value("${file.server.url}")
    private String fileServerUrl;

    /**
     * Constructor DI
     * @param userService
     * @param userDetailsService
     */
    @Autowired
    public UserSpaceController(UserService userService, UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }

    /**
     * Method used to display all the posts from this user
     * @param username
     * @param model
     * @return
     */
    @RequestMapping(value ="/{username}", method = RequestMethod.GET)
    public String userSpace(@PathVariable("username") String username, Model model) {
        User  user = (User)userDetailsService.loadUserByUsername(username);
        model.addAttribute("user", user);
        return "redirect:/u/" + username + "/blogs";
    }

    /**
     * Method used to display the user's profile
     * @param username
     * @param model
     * @return
     */
    @RequestMapping(value = "/{username}/profile", method = RequestMethod.GET)
    @PreAuthorize("authentication.name.equals(#username)")
    public ModelAndView profile(@PathVariable("username") String username, Model model) {
        User user = (User)userDetailsService.loadUserByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("fileServerUrl", fileServerUrl);
        return new ModelAndView("/userspace/profile", "userModel", model);
    }

    /**
     * Method used to edit the profile of a specific user
     * @param username
     * @param user
     * @return
     */
    @RequestMapping(value = "/{username}/profile", method = RequestMethod.POST)
    @PreAuthorize("authentication.name.equals(#username)")
    public ModelAndView saveProfile(@PathVariable("username") String username,User user) {
        User originalUser = userService.getUserById(user.getId());
        originalUser.setEmail(user.getEmail());
        originalUser.setName(user.getName());

        // determine if the password of user has been changed
        String rawPassword = originalUser.getPassword();
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePasswd = encoder.encode(user.getPassword());
        boolean isMatch = encoder.matches(rawPassword, encodePasswd);
        if (!isMatch) {
            originalUser.setEncodePassword(user.getPassword());
        }
        userService.saveUser(originalUser);
        return new ModelAndView("redirect:/u/" + username + "/profile");
    }

    /**
     * Method used to get the page for user to edit their avatar
     * @param username
     * @param model
     * @return
     */
    @RequestMapping(value = "/{username}/avatar", method = RequestMethod.GET)
    @PreAuthorize("authentication.name.equals(#username)")
    public ModelAndView avatar(@PathVariable("username") String username, Model model) {
        User  user = (User)userDetailsService.loadUserByUsername(username);
        model.addAttribute("user", user);
        return new ModelAndView("/userspace/avatar", "userModel", model);
    }

    /**
     * Method used to save the Avatar of a specific user
     * @param username
     * @return
     */
    @RequestMapping(value = "/{username}/avatar", method = RequestMethod.POST)
    @PreAuthorize("authentication.name.equals(#username)")
    @ResponseBody
    public ResponseEntity<Response> saveAvatar(@PathVariable("username") String username, @RequestBody User user) {
        System.out.println(user);
        System.out.println("******************");
        String avatarUrl = user.getAvatar();
        System.out.println("this is avatarurl");
        System.out.println(avatarUrl);
        User originalUser = userService.getUserById(user.getId());
        originalUser.setAvatar(avatarUrl);
        userService.saveUser(originalUser);
        return ResponseEntity.ok().body(new Response(true, "处理成功", avatarUrl));
    }

    /**
     *  Method used to return the list of sorted blogs
     * @param username
     * @param order
     * @param category
     * @param keyword
     * @return
     */
    @GetMapping("/{username}/blogs")
    public String listBlogsByOrder(@PathVariable("username") String username,
                                   @RequestParam(value="order",required=false,defaultValue="new") String order,
                                   @RequestParam(value="category",required=false ) Long category,
                                   @RequestParam(value="keyword",required=false ) String keyword) {

        if (category != null) {

            System.out.print("category:" +category );
            System.out.print("selflink:" + "redirect:/u/"+ username +"/blogs?category="+category);
            return "/u";

        } else if (keyword != null && keyword.isEmpty() == false) {

            System.out.print("keyword:" +keyword );
            System.out.print("selflink:" + "redirect:/u/"+ username +"/blogs?keyword="+keyword);
            return "/u";
        }

        System.out.print("order:" +order);
        System.out.print("selflink:" + "redirect:/u/"+ username +"/blogs?order="+order);
        return "/u";
    }

    /**
     * Method used to display the specific blog
     * @param id
     * @return
     */
    @GetMapping("/{username}/blogs/{id}")
    public String listBlogsByOrder(@PathVariable("id") Long id) {

        System.out.print("blogId:" + id);
        return "/blog";
    }


    @GetMapping("/{username}/blogs/edit")
    public String editBlog() {

        return "/blogedit";
    }
 }
