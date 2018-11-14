package ndingspringboot.BlogSite.domain.es;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;
import java.io.Serializable;

@Data
@Document(indexName = "blog", type = "blog")
public class EsBlog implements Serializable {

    @Id
    private String id;
    private String title;
    private String summary;
    private String content;

    protected EsBlog() {} // JPA request;

    public EsBlog(String title, String summary, String content) {
        this.content = content;
        this.summary = summary;
        this.title = title;
    }

    @Override
    public String toString() {
        return String.format("EsBlog[id='%s', titile='%s', summary=='%s', content='%s']", id, title, summary, content);
    }
}
