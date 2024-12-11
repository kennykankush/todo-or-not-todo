package nus.iss.todifier.repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import nus.iss.todifier.model.Todo;

@Repository
public class TodoRepository {

    @Autowired
    @Qualifier("jsonTemplate")
    private RedisTemplate<String, Object> template;

    String todoKey = "todos";

    public void saveEntry(Todo todo){ //Create
        template.opsForHash().put(todoKey, todo.getId(), todo);
    }

    public void saveEntries(List<Todo> todos){
        for (Todo todo : todos){
            saveEntry(todo);
        }
    }

    public void updateEntry(Todo todo){ //Update
        saveEntry(todo);
        
    }

    public void deleteEntry(String id){ //Remove
        template.opsForHash().delete(todoKey, id);

    }

    public void deleteEntries(List<String> ids){
        for (String id : ids){
            deleteEntry(id);
        }
    }

    public Todo getById(String id){ //Fetch by ID
        return (Todo) template.opsForHash().get(todoKey, id);
    }

    public List<Todo> getAllTodos() { //Fetch All

        Map<Object, Object> entries = template.opsForHash().entries(todoKey);

        return entries.values().stream()
                .map(obj -> (Todo) obj)
                .collect(Collectors.toList());
    }
}
