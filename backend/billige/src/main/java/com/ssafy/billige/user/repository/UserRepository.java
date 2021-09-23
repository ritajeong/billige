package com.ssafy.billige.user.repository;

import java.util.Optional;

import com.ssafy.billige.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByTokenId(long tokenId);
}
