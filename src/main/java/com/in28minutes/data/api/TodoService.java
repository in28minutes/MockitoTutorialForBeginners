package com.in28minutes.data.api;

import java.util.List;
//Hello there
// External Service - Lets say this comes from WunderList
public interface TodoService {

	public List<String> retrieveTodos(String user);

	void deleteTodo(String todo);

}