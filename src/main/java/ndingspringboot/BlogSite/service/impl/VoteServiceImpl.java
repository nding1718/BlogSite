package ndingspringboot.BlogSite.service.impl;

import ndingspringboot.BlogSite.domain.Repository.VoteRepository;
import ndingspringboot.BlogSite.domain.Vote;
import ndingspringboot.BlogSite.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Override
    @Transactional
    public void removeVote(Long id) {
        voteRepository.delete(id);
    }
    @Override
    public Vote getVoteById(Long id) {
        return voteRepository.findOne(id);
    }

}

