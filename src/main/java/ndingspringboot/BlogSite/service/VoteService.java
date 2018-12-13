package ndingspringboot.BlogSite.service;

import ndingspringboot.BlogSite.domain.Vote;

public interface VoteService {

    Vote getVoteById(Long id);

    void removeVote(Long id);
}

