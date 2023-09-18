package org.opensearch.data.example.service.impl;


import org.opensearch.client.RestHighLevelClient;
import org.opensearch.data.example.model.Book;
import org.opensearch.data.example.repository.BookRepository;
import org.opensearch.data.example.service.BookService;
import org.opensearch.data.example.service.exception.BookNotFoundException;
import org.opensearch.data.example.service.exception.DuplicateIsbnException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultBookService implements BookService {

    private final BookRepository bookRepository;

    private final RestHighLevelClient restHighLevelClient;

    public DefaultBookService(BookRepository bookRepository, RestHighLevelClient restHighLevelClient) {
        this.bookRepository = bookRepository;
        this.restHighLevelClient = restHighLevelClient;
    }

    @Override
    public Optional<Book> getByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public List<Book> getAll() {
        List<Book> books = new ArrayList<>();
        bookRepository.findAll()
                .forEach(books::add);
        return books;
    }

    @Override
    public List<Book> findByAuthor(String authorName) {
        return bookRepository.findByAuthorName(authorName);
    }

    @Override
    public List<Book> findByTitleAndAuthor(String title, String author) {
        return bookRepository.findByTitleAndAuthorName(title, author);
    }

//    @Override
//    public List<Book> findByTitleAndAuthor(String title, String author) {
//        var criteria = QueryBuilders.boolQuery()
//                .must(QueryBuilders.matchQuery("authorName", author))
//                .must(QueryBuilders.matchQuery("title", title));
//
//        SearchRequest searchRequest = new SearchRequest("book-index");
//        searchRequest.source().query(criteria);
//
//        try {
//            SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
//            return Arrays.stream(response.getHits().getHits())
//                    .map(hit -> new ObjectMapper().convertValue(hit.getSourceAsMap(), Book.class))
//                    .collect(Collectors.toList());
//        } catch (IOException e) {
//            throw new RuntimeException("Error executing search", e);
//        }
//    }

    @Override
    public Book create(Book book) throws DuplicateIsbnException {
        if (getByIsbn(book.getIsbn()).isEmpty()) {
            return bookRepository.save(book);
        }
        throw new DuplicateIsbnException(String.format("The provided ISBN: %s already exists. Use update instead!", book.getIsbn()));
    }

    @Override
    public void deleteById(String id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book update(String id, Book book) throws BookNotFoundException {
        Book oldBook = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("There is not book associated with the given id"));
        oldBook.setIsbn(book.getIsbn());
        oldBook.setAuthorName(book.getAuthorName());
        oldBook.setPublicationYear(book.getPublicationYear());
        oldBook.setTitle(book.getTitle());
        return bookRepository.save(oldBook);
    }
}
