package com.artech.personal.controller;

import com.artech.common.util.Result;
import com.artech.common.util.ResultUtil;
import com.artech.personal.domain.entity.Book;
import com.artech.personal.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("books", bookService.findAllBook());
        return "book/list";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/add")
    public String add() {
        return "book/add";
    }

    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/add")
    @ResponseBody
    public Result<Book> add(Book book) {
        Book result = bookService.addBook(book);
        return ResultUtil.success(result);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        model.addAttribute("book", bookService.findById(id));
        return "book/detail";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/info/{id}")
    @ResponseBody
    public Result<Book> info(@PathVariable("id") Long id) {
        Book book = bookService.findById(id);
        return ResultUtil.success(book);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/detail")
    public String detail(Model model, @RequestParam("name") String name) {
        model.addAttribute("book", bookService.findByName(name));
        return "book/detail";
    }
}
