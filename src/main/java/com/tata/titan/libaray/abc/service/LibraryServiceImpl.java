package com.tata.titan.libaray.abc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tata.titan.libaray.abc.entities.Book;
import com.tata.titan.libaray.abc.entities.Genres;
import com.tata.titan.libaray.abc.exception.ABCLibraryInvalidInputException;
import com.tata.titan.libaray.abc.model.BookModel;
import com.tata.titan.libaray.abc.model.SuccessResponse;
import com.tata.titan.libaray.abc.repositories.BookRepository;
import com.tata.titan.libaray.abc.repositories.GenreRepository;

/**
 * @author : Shanmukh Sravanth M
 * 
 *         Library Service implementation
 */

@Service
public class LibraryServiceImpl implements LibraryService {

	@Autowired
	GenreRepository genreRepository;

	@Autowired
	BookRepository bookRepository;

	@Override
	public List<String> getGenres() {

		List<Genres> list = genreRepository.findAll();
		return getGenresNamesFromGenres(list);
	}

	public List<String> getGenresNamesFromGenres(List<Genres> list) {
		List<String> genresList = new ArrayList<>();
		list.forEach(l -> genresList.add(l.getGenre()));
		return genresList;
	}

	@Override
	@Transactional
	public SuccessResponse addBook(BookModel bookModel) {
		try {
			Book book = getBookFromBookModel(bookModel);
			book = bookRepository.save(book);
			return new SuccessResponse(book.getName() + " Book Added to library Successfully", "201");
		} catch (ABCLibraryInvalidInputException e) {
			return new SuccessResponse(e.getMessage(), "500");
		} catch (Exception e) {
			e.printStackTrace();
			return new SuccessResponse(e.getMessage(), "500");
		}
	}

	private Book getBookFromBookModel(BookModel bookModel) throws ABCLibraryInvalidInputException {

		if (bookModel.name == null || bookModel.genre == null || bookModel.author == null
				|| bookModel.isAvailable == null) {
			throw new ABCLibraryInvalidInputException(
					"Mandatory fields are missing, kindly provide Name, Genre, Author and isAvailable fields");
		}

		if (bookRepository.countByName(bookModel.name) > 0) {
			throw new ABCLibraryInvalidInputException("Book Already avialble in ABC Library");
		}

		Book book = new Book();
		book.setName(bookModel.name);
		book.setGenre(bookModel.genre);
		if (genreRepository.countByGenre(bookModel.genre) == 0) {
			addNewGenre(bookModel.genre);
		}
		book.setAuthor(bookModel.author);
		book.setAvailable(bookModel.isAvailable);
		book.setPublishedDate(bookModel.publishedDate);

		if (bookModel.isAvailable != null && !bookModel.isAvailable && bookModel.availableDate == null) {
			throw new ABCLibraryInvalidInputException("Please Provide AvailableDate!");
		} else {
			book.setAvailableDate(bookModel.availableDate);
		}

		return book;
	}

	private void addNewGenre(String genre) {
		Genres genres = new Genres(genre);
		genreRepository.save(genres);
	}

	@Override
	public List<BookModel> getBooksByGenres(String genres) {

		List<BookModel> list = new ArrayList<>();
		List<Book> books = bookRepository.findByGenre(genres);

		books.forEach(book -> list.add(getBookModelFromBook(book)));

		return list;
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

	@Override
	public SuccessResponse updateBook(BookModel bookModel) {
		try {
			if (bookModel.name == null) {
				return new SuccessResponse("Please provide valid Book Name", "500");
			}
			
			List<Book> orgBooks = bookRepository.findByName(bookModel.name);
			if (orgBooks.isEmpty()) {
				return new SuccessResponse("Book Not Found, Please provide valid Book Name", "500");
			}
			Book orgBook = orgBooks.get(0);
			if (bookModel.isAvailable != null) {
				orgBook.setAvailable(bookModel.isAvailable);
			}
			if (bookModel.isAvailable != null && !bookModel.isAvailable && bookModel.availableDate == null) {
				throw new ABCLibraryInvalidInputException("Please Provide AvailableDate!");
			} else {
				orgBook.setAvailableDate(bookModel.availableDate);
			}

			if (bookModel.author != null) {
				orgBook.setAuthor(bookModel.author);
			}
			if (bookModel.publishedDate != null) {
				orgBook.setPublishedDate(bookModel.publishedDate);
			}
			if (bookModel.genre != null) {
				orgBook.setGenre(bookModel.genre);
			}

			orgBook = bookRepository.save(orgBook);
			return new SuccessResponse(orgBook.getName() + " Book updated in library Successfully", "202");
		} catch (ABCLibraryInvalidInputException e) {
			return new SuccessResponse(e.getMessage(), "500");
		} catch (Exception e) {
			e.printStackTrace();
			return new SuccessResponse(e.getMessage(), "500");
		}

	}

}
