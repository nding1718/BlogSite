package ndingspringboot.BlogSite.rest;

import org.springframework.web.bind.annotation.*;

@RestController("/user")
public class UserSpaceController {

    @RequestMapping(value ="/{username}", method = RequestMethod.GET)
    public String userSpace(@PathVariable("username") String username) {
        System.out.println("username" +  username);
        return "/userspace/user";
    }

    @RequestMapping(value = "/{username}/blogs")
    public String listBlogsByOrder(@PathVariable("username") String username,
                                   @RequestParam(value = "order", required = false, defaultValue="new") String order,
                                   @RequestParam(value = "category", required = false) Long category,
                                   @RequestParam(value = "keyword", required = false) String keyword) {
        if (category != null) {
            System.out.println("category:" + category);
            System.out.println("selflink:" + "redirect:/user/" + username + "/blogs?category=" + category);
            return "/userspace/user";
        } else if (keyword != null && keyword.isEmpty() == false) {
            System.out.println("keyword" + keyword);
            System.out.println("selflink:" + "redirect:/user/" + username + "/blogs?keyword=" + keyword);
            return "/userspace/user";
        }

        System.out.print("order:" + order);
        System.out.print("selflink:" + "redirect:/user/" + username + "/blogs?order=" + order);
        return "/userspace/user";
    }

    @RequestMapping(value = "/{username}/blogs/{id}")
    public String listBlogsByOrder(@PathVariable("id") Long id) {
        System.out.println("blogId:" + id);
        return "/userspace/blog";
    }

    @RequestMapping(value = "/{username}/blogs/edit")
    public String editBlog() {
        return "/userspace/blogedit";
    }
 }
