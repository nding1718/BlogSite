package ndingspringboot.BlogSite.domain.es;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EsBlogRepositoryTest {

    @Autowired
    private EsBlogRepository esBlogRepository;

    @Before
    public void initRepositoryData() {
        // clear all the data
        esBlogRepository.deleteAll();
        esBlogRepository.save(new EsBlog("test", "test1", "this is test1"));
        esBlogRepository.save(new EsBlog("test", "test2", "this is test2"));
        esBlogRepository.save(new EsBlog("test", "test3", "this is test3"));
    }

    @Test
    public void testFindDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContaining(){
        Pageable pageable = new PageRequest(0, 20);
        String title = "test";
        String summary = "1";
        String content = "3";
        Page<EsBlog> page = esBlogRepository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContaining(title, summary, content,pageable);
        assertThat(page.getTotalElements()).isEqualTo(3);
        System.out.println("-----------Start");
        for (EsBlog blog : page.getContent()) {
            System.out.println(blog.toString());
        }
        System.out.println("-----------Finish");
    }
}
