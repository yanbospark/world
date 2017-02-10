package com.world.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.world.entity.UserVote;

public interface UserVoteRepository extends CrudRepository<UserVote, Long>{

	@Modifying
	@Query("update UserVote set score=?1 where user.id=?2 and show.id=?3")
	public int update(int score,long userId,long showId);
	
	@Query("select sum(uv.score) as score,uv.show from UserVote uv group by uv.show.id order by score desc")
	public List<UserVote> countVote();
	
	@Query("from UserVote uv where uv.user.id=?1 and uv.show.id=?2")
	public UserVote ifVoted(long userId,long showId);
	
	@Query("select uv from UserVote uv where uv.user.id=?1")
	public List<UserVote> haveVoted(long userId);
}
