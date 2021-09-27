package com.ssafy.billige.user.repository;

import java.util.Optional;

import com.ssafy.billige.user.domain.User;
import com.sun.xml.bind.v2.schemagen.episode.SchemaBindings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUserTokenId(String tokenId);

	Optional<User> findByUid(long uid);

	Optional<User> findByUserEmail(String username);

	boolean existsByUserEmail(String userEmail);
}
