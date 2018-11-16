package ndingspringboot.BlogSite.utils;

import lombok.Data;

import java.io.Serializable;

@Data
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String url;

    public Menu(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
