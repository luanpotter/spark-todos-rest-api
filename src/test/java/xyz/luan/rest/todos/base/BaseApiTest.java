package xyz.luan.rest.todos.base;

import com.despegar.sparkjava.test.SparkServer;
import com.google.gson.Gson;
import org.dalesbred.Database;
import org.dalesbred.annotation.SQL;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import xyz.luan.rest.todos.DB;
import xyz.luan.rest.todos.todo.TodosController;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import static java.util.stream.Collectors.joining;

public class BaseApiTest {

	protected static final Gson GSON = new Gson();

	@ClassRule
	public static SparkServer<TodosController> server = new SparkServer<>(TodosController.class, 4568);

	@BeforeClass
	public static void setupFirstTime() {
		DB.db = Database.forUrlAndCredentials("jdbc:hsqldb:mem:memory_db;sql.syntax_pgs=true;sql.enforce_size=false", "SA", "");
	}

	@Before
	public void setup() {
		DB.db.update("DROP SCHEMA PUBLIC CASCADE");
		String[] instructions = readInputStream(BaseApiTest.class.getResourceAsStream("/setup.sql")).split(";");
		for (@SQL String instruction : instructions) {
			DB.db.update(instruction);
		}
	}

	public static String readInputStream(InputStream stream) {
		return new BufferedReader(new InputStreamReader(stream)).lines().collect(joining("\n"));
	}
}
