package xyz.luan.rest.todos.todo;

import com.google.gson.Gson;
import spark.Request;
import spark.servlet.SparkApplication;

import static spark.Spark.delete;
import static spark.Spark.*;
import static xyz.luan.rest.todos.todo.Todos.*;

public class TodosController implements SparkApplication {

	private static final Gson GSON = new Gson();

	@Override
	public void init() {
		get("/todos", (req, res) -> list(), GSON::toJson);
		get("/todos/:id", (req, res) -> fetch(parseId(req)), GSON::toJson);

		post("/todos", (req, res) -> update(parseTodo(req)), GSON::toJson);
		put("/todos/:id", (req, res) -> update(parseId(req), parseTodo(req)), GSON::toJson);
		delete("/todos/:id", (req, res) -> Todos.delete(parseId(req)), GSON::toJson);
	}

	private static long parseId(Request req) {
		return Long.parseLong(req.params("id"));
	}

	private static Todo parseTodo(Request req) {
		return GSON.fromJson(req.body(), Todo.class);
	}
}
