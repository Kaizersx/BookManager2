package net.proselyte.bookmanager.controller;

import net.proselyte.bookmanager.model.Book;
import net.proselyte.bookmanager.model.User;
import net.proselyte.bookmanager.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


//  класс который выводит наши данные
@Controller
public class BookController {
    private BookService bookService;

    @Autowired(required = true)
    @Qualifier(value = "bookService")
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView main(HttpSession session){

        return new ModelAndView("login","usera", new User());
    }

    @RequestMapping (value = "/check", method = RequestMethod.POST)
    public String checkUser(@Valid @ModelAttribute("usera") User user, BindingResult bindingResult, Model model) {


        if (user.getName().equals("Vahagn") && user.getPassword().equals("noob")) {

            model.addAttribute("user",user);
            return "checkUsr";

        }

        else {
            return "errorPage";
        }

    }

    //Далее мы определяем страницы, которые у нас будут

    @RequestMapping(value = "books", method = RequestMethod.GET)    //"books" -это ссылка на страницу,выбираем GET потому что хотим ПОЛУЧИТЬ данные
    public String listBooks(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("listBooks", this.bookService.listBooks());

        return "books"; //возвращаем ссылку на главную страницу "books"
    }

    @RequestMapping(value = "/books/add", method = RequestMethod.POST) // здесь определяем способ добавления страницы (делаем доп. страницу), метод выбираем POST, потому что нужно ДОБАВИТЬ
    public String addBook(@ModelAttribute("book") Book book){
        if(book.getId() == 0){
            this.bookService.addBook(book);
        }else {
            this.bookService.updateBook(book);
        }

        return "redirect:/books";  // после добавления направляемся на главную страницу "books"
    }

    @RequestMapping("/remove/{id}")   // создаем стрицу для удаления книги. Удалять будем по ID, т.е. так как указанов  методе
    public String removeBook(@PathVariable("id") int id){
        this.bookService.removeBook(id);

        return "redirect:/books";
    }

    @RequestMapping("edit/{id}") // страница которая редактирует книги
    public String editBook(@PathVariable("id") int id, Model model){
        model.addAttribute("book", this.bookService.getBookById(id));
        model.addAttribute("listBooks", this.bookService.listBooks());

        return "books";
    }

    @RequestMapping("bookdata/{id}") // страница для просмотра по отдельности нужной книги
    public String bookData(@PathVariable("id") int id, Model model){
        model.addAttribute("book", this.bookService.getBookById(id));

        return "bookdata";
    }
}
