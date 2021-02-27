package com.ngsoft.part1.dao;

import com.google.gson.Gson;
import com.ngsoft.part1.pojos.TodoItem;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DefaultTodoDao implements TodoDao {
	
	private static List<TodoItem> todoItems;
	
	
    @Override
    public synchronized List<TodoItem> getTodos() throws FileNotFoundException, URISyntaxException {
        if(todoItems == null){
            Gson gson = new Gson();
            File myObj = new File(this.getClass().getClassLoader().getResource("todos.json").toURI());
            Scanner myReader = new Scanner(myObj);
            StringBuilder sb = new StringBuilder();
            while(myReader.hasNext()){
                sb.append(myReader.next());
            }
            todoItems =  Arrays.asList(gson.fromJson(sb.toString(), TodoItem[].class));
        }




        return todoItems;
    }

    
}
