package ndingspringboot.BlogSite.rest;


import ndingspringboot.BlogSite.domain.es.EsBlog;
import ndingspringboot.BlogSite.domain.es.EsBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/blogs")
public class BlogController {

    @Autowired
    private EsBlogRepository esBlogRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String listBlogs(
            @RequestParam(value = "order",required = false, defaultValue = "new") String order,
            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) {
        System.out.println("order:" +  order  + ";keyword:" + keyword);
        return "redirect:/index?order=" + order + "&keyword=" + keyword;
    }
}
