package com.tata.titan.libaray.abc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tata.titan.libaray.abc.entities.Genres;

/**
 * @author : Shanmukh Sravanth M 
 * 
 * Genre Repository
 */

@Repository
@Transactional
public interface GenreRepository extends JpaRepository<Genres, Long> {
	
	Long countByGenre(String genre);

}
