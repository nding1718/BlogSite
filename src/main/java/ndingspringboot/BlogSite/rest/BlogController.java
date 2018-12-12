package ndingspringboot.BlogSite.rest;

import ndingspringboot.BlogSite.domain.EsBlog;
import ndingspringboot.BlogSite.domain.User;
import ndingspringboot.BlogSite.service.EsBlogService;
import ndingspringboot.BlogSite.utils.TagVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping(value = "/blogs")
public class BlogController {

    private EsBlogService esBlogService;

    /**
     * Constructor DI
     * @param esBlogService
     */
    @Autowired
    public BlogController(EsBlogService esBlogService) {
        this.esBlogService = esBlogService;
    }

    /**
     * Full text search for blog accourding to the pass in parameters
     * @param order
     * @param keyword
     * @param async
     * @param pageIndex
     * @param pageSize
     * @param model
     * @return
     */
    @GetMapping
    public ModelAndView listEsBlogs(
            @RequestParam(value="order",required=false,defaultValue="new") String order,
            @RequestParam(value="keyword",required=false,defaultValue="" ) String keyword,
            @RequestParam(value="async",required=false) boolean async,
            @RequestParam(value="pageIndex",required=false,defaultValue="0") int pageIndex,
            @RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize,
            Model model) {

        Page<EsBlog> page = null;
        List<EsBlog> list;
        boolean isEmpty = true;
        try {
            if (order.equals("hot")) { // Find the most hot blogs
                Sort sort = new Sort(Sort.Direction.DESC,"readSize","commentSize","voteSize","createTime");
                Pageable pageable = new PageRequest(pageIndex, pageSize, sort);
                page = esBlogService.listHotestEsBlogs(keyword, pageable);
            } else if (order.equals("new")) { // Find the newest blogs
                Sort sort = new Sort(Sort.Direction.DESC,"createTime");
                Pageable pageable = new PageRequest(pageIndex, pageSize, sort);
                page = esBlogService.listNewestEsBlogs(keyword, pageable);
            }
            isEmpty = false;
        } catch (Exception e) { // catch the exception that we don't have any esblogs at first search
            Pageable pageable = new PageRequest(pageIndex, pageSize);
            page = esBlogService.listEsBlogs(pageable);
        }

        list = page.getContent();

        model.addAttribute("order", order);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        model.addAttribute("blogList", list);

        // Only loaded the first time we access the page since this ranks will not change because the page changes
        if (!async && !isEmpty) {
            List<EsBlog> newest = esBlogService.listTop5NewestEsBlogs();
            model.addAttribute("newest", newest);
            List<EsBlog> hotest = esBlogService.listTop5HotestEsBlogs();
            model.addAttribute("hotest", hotest);
            List<TagVO> tags = esBlogService.listTop30Tags();
            model.addAttribute("tags", tags);
            List<User> users = esBlogService.listTop12Users();
            model.addAttribute("users", users);
        }
        //return (async==true?"/index :: #mainContainerRepleace":"/index");
        return async==true ? new ModelAndView("/index :: #mainContainerRepleace", "searchModel", model) : new ModelAndView("/index", "searchModel", model);
    }

    @GetMapping("/newest")
    public String listNewestEsBlogs(Model model) {
        List<EsBlog> newest = esBlogService.listTop5NewestEsBlogs();
        model.addAttribute("newest", newest);
        return "newest";
    }

    @GetMapping("/hotest")
    public String listHotestEsBlogs(Model model) {
        List<EsBlog> hotest = esBlogService.listTop5HotestEsBlogs();
        model.addAttribute("hotest", hotest);
        return "hotest";
    }


}