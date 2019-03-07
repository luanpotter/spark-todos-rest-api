package xyz.luan.rest.todos;

import org.dalesbred.Database;
import org.dalesbred.query.SqlQuery;

import java.util.List;

public class DB {

	public static Database db = Database.forUrlAndCredentials("jdbc:postgresql://[::1]:5432/todo-api", "todo-api", "123");

	public static Long updateAndGetGeneratedId(SqlQuery query) {
		return db.updateAndProcessGeneratedKeys(result -> {
			result.next();
			return result.getLong(1);
		}, List.of("id"), query);
	}
}