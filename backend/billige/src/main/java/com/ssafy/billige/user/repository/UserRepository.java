package com.ssafy.billige.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.billige.user.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUserTokenId(String tokenId);

	Optional<User> findByUid(long uid);

	Optional<User> findByUserEmail(String username);

	boolean existsByUserEmail(String userEmail);

	Optional<User> findByUid(Long uid);
}
