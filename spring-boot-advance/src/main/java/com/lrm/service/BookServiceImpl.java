package com.lrm.service;

import com.lrm.domain.Book;
import com.lrm.domain.BookRepository;
import com.lrm.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book getBookById(Long id) {
        Optional<Book> opt = bookRepository.findById(id);
        if(!opt.isPresent()){
            throw new BookNotFoundException("书单信息找不到");
        }
        return opt.get();
    }
}
