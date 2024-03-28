package com.in28minutes.business;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.in28minutes.data.api.TodoService;

import org.junit.jupiter.api.Test;
import com.in28minutes.data.stub.TodoServiceStub;

public class TodoBusinessImplStubTest {

	@Test
	public void usingAStub() {
		TodoService todoService = new TodoServiceStub();
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
		List<String> todos = todoBusinessImpl
				.retrieveTodosRelatedToSpring("Ranga");
		assertEquals(2, todos.size());
	}
}
