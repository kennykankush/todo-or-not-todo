package nus.iss.todifier.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.iss.todifier.model.Todo;
import nus.iss.todifier.repository.TodoRepository;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepo;

    public void saveEntry(Todo todo){ //Create
        todoRepo.saveEntry(todo);
    }

    public void saveEntries(List<Todo> todos){
        todoRepo.saveEntries(todos);
    }

    public void updateEntry(Todo todo){ //Update
        todoRepo.updateEntry(todo);
    }

    public void deleteEntry(String id){ //Remove
        todoRepo.deleteEntry(id);
    }

    public Todo getById(String id){ //Fetch by ID
        return todoRepo.getById(id);
    }

    public List<Todo> getAllTodos() { //Fetch All
        return todoRepo.getAllTodos();
    }
    
}
