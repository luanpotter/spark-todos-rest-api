package xyz.luan.rest.todos;

import xyz.luan.rest.todos.todo.TodosController;

public class Main {

	public static void main(String[] args) {
		DB.initDb();
		new TodosController().init();
	}
}