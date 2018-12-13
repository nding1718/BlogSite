package ndingspringboot.BlogSite.service.impl;

import ndingspringboot.BlogSite.domain.Authority;
import ndingspringboot.BlogSite.domain.Repository.AuthorityRepository;
import ndingspringboot.BlogSite.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    private AuthorityRepository authorityRepository;

    @Autowired
    public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public Authority getAuthorityById(Long id) {
        return authorityRepository.findOne(id);
    }
}
