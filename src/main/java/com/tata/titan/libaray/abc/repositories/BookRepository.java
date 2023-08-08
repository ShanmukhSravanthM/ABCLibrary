package com.tata.titan.libaray.abc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tata.titan.libaray.abc.entities.Book;

/**
 * @author : Shanmukh Sravanth M 
 * 
 * Book Repository
 */

@Repository
@Transactional
public interface BookRepository extends JpaRepository<Book, Long> {

	List<Book> findByGenre(String genres);

	int countByName(String name);

	List<Book> findByName(String name);

}
