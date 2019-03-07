package xyz.luan.rest.todos.todo;

import com.despegar.http.client.HttpClientException;
import com.despegar.http.client.HttpResponse;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import xyz.luan.rest.todos.base.BaseApiTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TodosApiTest extends BaseApiTest {

	@Test
	public void testListTodos() throws HttpClientException {
		HttpResponse response = server.execute(server.get("/todos", false));
		assertThat(response.code()).isEqualTo(200);
		assertThat(new String(response.body())).isEqualTo("[]");
	}

	@Test
	public void testCreateTodo() throws HttpClientException {
		HttpResponse createResp = server.execute(server.post("/todos", "{ \"text\": \"test!\" }", false));
		assertThat(createResp.code()).isEqualTo(200);

		HttpResponse listResp = server.execute(server.get("/todos", false));
		assertThat(listResp.code()).isEqualTo(200);
		List<Todo> todos = GSON.fromJson(new String(listResp.body()), new TypeToken<List<Todo>>() {
		}.getType());
		assertThat(todos.size()).isEqualTo(1);
		Todo todo = todos.get(0);
		assertThat(todo.getText()).isEqualTo("test!");

		HttpResponse fetchResp = server.execute(server.get("/todos/" + todo.getId(), false));
		assertThat(fetchResp.code()).isEqualTo(200);
		Todo todoFromFetch = GSON.fromJson(new String(fetchResp.body()), Todo.class);
		assertThat(todoFromFetch.getText()).isEqualTo("test!");
	}
}
