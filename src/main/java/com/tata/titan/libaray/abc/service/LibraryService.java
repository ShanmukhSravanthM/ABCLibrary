package com.tata.titan.libaray.abc.service;

import java.util.List;

import com.tata.titan.libaray.abc.model.BookModel;
import com.tata.titan.libaray.abc.model.SuccessResponse;

/**
 * @author : Shanmukh Sravanth M 
 * 
 * Library Service
 */

public interface LibraryService {
	
	public List<String> getGenres();

	public SuccessResponse addBook(BookModel bookModel);

	public List<BookModel> getBooksByGenres(String genres);

	public SuccessResponse updateBook(BookModel bookModel);

}
