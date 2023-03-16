package com.example.testsecurity2dbthymeleaf.controller;

import com.example.testsecurity2dbthymeleaf.entity.Book;
import com.example.testsecurity2dbthymeleaf.entity.UserAction;
import com.example.testsecurity2dbthymeleaf.repository.BookRepository;
import com.example.testsecurity2dbthymeleaf.repository.UserActionRepository;
import com.example.testsecurity2dbthymeleaf.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Slf4j
@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserActionRepository userActionRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/list")
    public ModelAndView getAllBooks() {
        log.info("/list -> connection");
        ModelAndView mav = new ModelAndView("list-books");
        mav.addObject("book", bookRepository.findAll());
        userActionRepository.save( new UserAction(
                "User email - " + getUserMail() + ";" +
                        " Open list book."
        ));
        return mav;
    }

    @GetMapping("/addBookForm")
    public ModelAndView addBookForm(){
        ModelAndView mav = new ModelAndView("add-book-form");
        Book book = new Book();
        mav.addObject("book", book);
        userActionRepository.save( new UserAction(
                "User email - " + getUserMail() + ";" +
                        " Open add book form."
        ));
        return mav;
    }

    @PostMapping("/saveBook")
    public String saveBook(@ModelAttribute Book book){
        userActionRepository.save( new UserAction(
                 "User email - " + getUserMail() + ";" +
                         " Save book." +
                         " Book ID-" + book.getId() +
                         " Book title-" + book.getTitle() +
                         " Book author-" + book.getAuthor() +
                         " Book year-" + book.getPublic_year() +
                         " Book rating-" + book.getRating()
        ));
        bookRepository.save(book);
        return "redirect:/list";
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long bookId){
        ModelAndView mav = new ModelAndView("add-book-form");
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        Book book = new Book();
        if (optionalBook.isPresent()){
            book = optionalBook.get();
        }
        mav.addObject("book", book);
        userActionRepository.save( new UserAction(
                "User email - " + getUserMail() + ";" +
                        " Open update form. " +
                        " Book ID - " + book.getId() +
                        " Book Title - " + book.getTitle() +
                        " Book Author - " + book.getAuthor() +
                        " Book Public Year - " + book.getPublic_year() +
                        " Book Rating - " + book.getRating()
        ));
        return mav;
    }

    @GetMapping("/deleteBook")
    public String deleteBook(@RequestParam Long bookId){
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        Book book = new Book();
        if (optionalBook.isPresent()){
            book = optionalBook.get();
        }
        userActionRepository.save( new UserAction(
                "User email - " + getUserMail() + ";" +
                        " Delete book. " +
                        " Book ID - " + book.getId() +
                        " Book Title - " + book.getTitle() +
                        " Book Author - " + book.getAuthor() +
                        " Book Public Year - " + book.getPublic_year() +
                        " Book Rating - " + book.getRating()
        ));
        bookRepository.deleteById(bookId);
        return "redirect:/list";
    }

    private String getUserMail(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null){
            return authentication.getName();
        }
        return "Unauthorizated user.";
    }
}
