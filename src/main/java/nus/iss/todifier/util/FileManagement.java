package nus.iss.todifier.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import nus.iss.todifier.model.Todo;

public class FileManagement {

    public static boolean isFileTxt(String fileName){

        if (fileName.endsWith(".json")){
            return true;
        }

        return false;
    }

    public static List<Todo> uploadReader(MultipartFile file) throws IOException{

        List<Todo> todos = new ArrayList<>();

        InputStreamReader isr = new InputStreamReader(file.getInputStream());
        JsonReader jsonReader = Json.createReader(isr);
        JsonArray jsonArray = jsonReader.readArray();

        for (int i = 0; i < jsonArray.size(); i++){
            JsonObject iTodo = jsonArray.getJsonObject(i);

            Todo todo = new Todo();

            todo.setId(iTodo.getString("id"));
            todo.setName(iTodo.getString("name"));
            todo.setDescription(iTodo.getString("description"));
            todo.setDueDate(iTodo.getString("due_date"));
            todo.setPriority(iTodo.getString("priority_level"));
            todo.setStatus(iTodo.getString("status"));
            todo.setCreatedAt(iTodo.getString("created_at"));
            todo.setUpdatedAt(iTodo.getString("updated_at"));

            todos.add(todo);

            
        }

        return todos;

    
    }

}
