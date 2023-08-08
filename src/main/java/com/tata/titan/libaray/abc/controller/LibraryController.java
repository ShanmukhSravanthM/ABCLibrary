package com.tata.titan.libaray.abc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tata.titan.libaray.abc.model.BookModel;
import com.tata.titan.libaray.abc.model.SuccessResponse;
import com.tata.titan.libaray.abc.service.LibraryService;

/**
 * @author : Shanmukh Sravanth M 
 * 
 * ABC library Controller
 * 
 */

@RestController
@RequestMapping("/library")
public class LibraryController {

	@Autowired
	LibraryService libraryService;

	//GET Genres API
	@GetMapping("/genres")
	public List<String> getGenres() {
		return libraryService.getGenres();
	}

	//Add New Book to library API
	@PostMapping("/book")
	public SuccessResponse addBook(@RequestBody BookModel bookModel) {
		return libraryService.addBook(bookModel);
	}
	
	//GET books by genre API
	@GetMapping("/{genres}/getBooks")
	public List<BookModel> getGenres(@PathVariable String genres) {
		return libraryService.getBooksByGenres(genres);
	}

	//Update Book details API
	@PutMapping("/book")
	public SuccessResponse updateBook(@RequestBody BookModel bookModel) {
		return libraryService.updateBook(bookModel);
	}

}	