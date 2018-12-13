package ndingspringboot.BlogSite.service;

import ndingspringboot.BlogSite.domain.Catalog;
import ndingspringboot.BlogSite.domain.User;

import java.util.List;

public interface CatalogService {

    Catalog saveCatalog(Catalog catalog);

    void removeCatalog(Long id);

    Catalog getCatalogById(Long id);

    List<Catalog> listCatalogs(User user);
}
