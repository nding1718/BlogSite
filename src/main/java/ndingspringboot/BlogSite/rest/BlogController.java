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
    public List<EsBlog> list(
            @RequestParam(value = "title") String title,
            @RequestParam(value = "summary") String summary,
            @RequestParam(value = "content") String content,
            @RequestParam(value = "pageIndex", defaultValue = "0") int pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize
    ) {
        Page<EsBlog> page = esBlogRepository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContaining(title, summary, content, new PageRequest(pageIndex, pageSize));
        return page.getContent();
    }
}
