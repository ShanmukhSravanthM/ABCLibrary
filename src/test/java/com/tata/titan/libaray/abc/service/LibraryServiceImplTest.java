package com.tata.titan.libaray.abc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tata.titan.libaray.abc.entities.Book;
import com.tata.titan.libaray.abc.model.BookModel;
import com.tata.titan.libaray.abc.model.SuccessResponse;
import com.tata.titan.libaray.abc.repositories.BookRepository;
import com.tata.titan.libaray.abc.repositories.GenreRepository;

@SpringBootTest
class LibraryServiceImplTest {

	@Autowired
	private GenreRepository genreRepository;

	@Autowired
	LibraryServiceImpl libraryServiceImpl;

	@Autowired
	private BookRepository bookRepository;

	@Test
	void testGetGenres() {
		List<String> list = libraryServiceImpl.getGenres();
		long count = genreRepository.count();
		assertEquals(count, list.size());
	}

	@Test
	void testAddBook() {

		byte[] array = new byte[7];
		new Random().nextBytes(array);
		String generatedString = new String(array, Charset.forName("UTF-8"));

		BookModel model = new BookModel();
		model.name = generatedString;
		model.genre = "Genre";
		model.author = "author";
		model.publishedDate = new Date();
		model.isAvailable = true;
		SuccessResponse resp = libraryServiceImpl.addBook(model);
		
		assertEquals("201", resp.responseCode);
	}

	@Test
	void testGetBooksByGenres() {
		String genres = "Fiction";
		List<BookModel> list = libraryServiceImpl.getBooksByGenres(genres);
		List<Book> books = bookRepository.findByGenre(genres);
		assertEquals(books.size(), list.size());
	}

	@Test
	void testUpdateBook() {
		String author = "apj abdul kalam";
		List<Book> books = bookRepository.findAll();
		if (!books.isEmpty()) {
			BookModel model = getBookModelFromBook(books.get(0));
			model.author = author;
			libraryServiceImpl.updateBook(model);

			List<Book> dbBook = bookRepository.findByName(books.get(0).getName());
			assertEquals(dbBook.get(0).getAuthor(), author);
		}

	}

	private BookModel getBookModelFromBook(Book book) {
		BookModel bookModel = new BookModel();

		if (book != null) {
			if (book.getName() != null) {
				bookModel.name = book.getName();
			}
			if (book.getAuthor() != null) {
				bookModel.author = book.getAuthor();
			}
			if (book.getGenre() != null) {
				bookModel.genre = book.getGenre();
			}
			if (book.getAvailableDate() != null && !book.isAvailable()) {
				bookModel.availableDate = book.getAvailableDate();
			}
			if (book.getPublishedDate() != null) {
				bookModel.publishedDate = book.getPublishedDate();
			}
			bookModel.isAvailable = book.isAvailable();
		}

		return bookModel;
	}

}
