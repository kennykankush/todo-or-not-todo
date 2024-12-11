package nus.iss.todifier.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import nus.iss.todifier.model.Todo;
import nus.iss.todifier.service.TodoService;
import nus.iss.todifier.util.Common;
import nus.iss.todifier.util.FileManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class TodoController {

    @Autowired
    private TodoService todoServ;

    @GetMapping
    public String showHome(Model model) {
        List<Todo> todos = todoServ.getAllTodos();
        model.addAttribute("todos", todos);
        return "listing";
    }

    @GetMapping("/create")
    public String showCreate(Model model){
        model.addAttribute("todo", new Todo());

        return "create";
    }
    
    @PostMapping("/upload")
    public String uploadToDatabase(@RequestParam("fileupload") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {

        if (file.isEmpty()){
            String message = "Please... don't do this to me, select something ffs";
            redirectAttributes.addFlashAttribute("message", message);
            return "redirect:/";
        }

        List<Todo> todos = FileManagement.uploadReader(file);
        todoServ.saveEntries(todos);

        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteFromDatabase(@RequestParam("selected") List<String> selected, RedirectAttributes redirectAttributes) {
       
        todoServ.deleteEntries(selected);

        return "redirect:/";
    }

    @PostMapping("/created")
    public String addEntryFromCreatePage(@ModelAttribute @Valid Todo todo, BindingResult result){

        if (result.hasErrors()){
            return "create";
        }

        todo.setId(Common.generateId());
        todo.setCreatedAt(Common.getCurrentTime());
        todo.setUpdatedAt(Common.getCurrentTime());

        todoServ.saveEntry(todo);

        return ("redirect:/");

    }
    
    
    
}
