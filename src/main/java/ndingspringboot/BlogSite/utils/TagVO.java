package ndingspringboot.BlogSite.utils;

import java.io.Serializable;

/**
 * Tag value object class, since we don't have a tag class, yet we still need to pass the value
 * of some tags to the front end, so we build this value object for tags
 */
public class TagVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private Long count;

    public TagVO(String name, Long count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

}

