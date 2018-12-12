package ndingspringboot.BlogSite.rest;

import ndingspringboot.BlogSite.domain.Catalog;
import ndingspringboot.BlogSite.domain.User;
import ndingspringboot.BlogSite.service.CatalogService;
import ndingspringboot.BlogSite.utils.CatalogVO;
import ndingspringboot.BlogSite.utils.ConstraintViolationExceptionHandler;
import ndingspringboot.BlogSite.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Controller
@RequestMapping("/catalogs")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private UserDetailsService userDetailsService;
    /**
     * @param
     * @param model
     * @return
     */
    @GetMapping
    public String listComments(@RequestParam(value="username",required=true) String username, Model model) {
        User user = (User)userDetailsService.loadUserByUsername(username);
        List<Catalog> catalogs = catalogService.listCatalogs(user);

        // determine whether or not this user is the owner
        boolean isOwner = false;

        if (SecurityContextHolder.getContext().getAuthentication() !=null && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
                &&  !SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser")) {
            User principal = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal !=null && user.getUsername().equals(principal.getUsername())) {
                isOwner = true;
            }
        }

        model.addAttribute("isCatalogsOwner", isOwner);
        model.addAttribute("catalogs", catalogs);
        return "/userspace/u :: #catalogRepleace";
    }

    /**
     * Create a new catalog
     * @param catalogVO
     * @return
     */
    @PostMapping
    @PreAuthorize("authentication.name.equals(#catalogVO.username)")// check the user
    public ResponseEntity<Response> create(@RequestBody CatalogVO catalogVO) {

        String username = catalogVO.getUsername();
        Catalog catalog = catalogVO.getCatalog();

        User user = (User)userDetailsService.loadUserByUsername(username);

        try {
            catalog.setUser(user);
            catalogService.saveCatalog(catalog);
        } catch (ConstraintViolationException e)  {
            return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, e.getMessage()));
        }

        return ResponseEntity.ok().body(new Response(true, "Success!", null));
    }

    /**
     * Delete a catalog
     * @param username
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("authentication.name.equals(#username)")
    public ResponseEntity<Response> delete(String username, @PathVariable("id") Long id) {
        try {
            catalogService.removeCatalog(id);
        } catch (ConstraintViolationException e)  {
            return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, e.getMessage()));
        }

        return ResponseEntity.ok().body(new Response(true, "Success!", null));
    }

    /**
     * Get the catalog edit page
     * @param model
     * @return
     */
    @GetMapping("/edit")
    public String getCatalogEdit(Model model) {
        Catalog catalog = new Catalog(null, null);
        model.addAttribute("catalog",catalog);
        return "/userspace/catalogedit";
    }

    /**
     * Get the catalog information by id
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/edit/{id}")
    public String getCatalogById(@PathVariable("id") Long id, Model model) {
        Catalog catalog = catalogService.getCatalogById(id);
        model.addAttribute("catalog",catalog);
        return "/userspace/catalogedit";
    }

}

