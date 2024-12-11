package nus.iss.todifier.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import nus.iss.todifier.model.Todo;
import nus.iss.todifier.service.TodoService;
import nus.iss.todifier.util.FileManagement;
import org.springframework.web.bind.annotation.GetMapping;

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
    
    
}
