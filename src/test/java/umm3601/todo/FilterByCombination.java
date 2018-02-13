package umm3601.todo;

import org.junit.Test;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import static junit.framework.TestCase.assertEquals;
/**
 * Tests  filterByCombination
 */
public class FilterByCombination {

  @Test
  public void filterTodosByCombination() throws IOException {
    TodoDatabase db = new TodoDatabase("src/main/data/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());


    Todo[] todos = db.filterTodosByOwner(allTodos, "Blanche");
    todos = db.filterTodosByStatus(todos, "incomplete");
    todos = db.filterTodosByLimit(todos, "5");

    for (int i=0; i < todos.length; i++) {
      assertEquals("Incorrect number of todos with status", false, todos[i].status);
    }

  }

  @Test
  public void listTodosWithByCombination() throws IOException {
    TodoDatabase db = new TodoDatabase("src/main/data/todos.json");
    Map<String, String[]> queryParams = new HashMap<>();
    queryParams.put("owner", new String[]{"Blanche"});
    queryParams.put("status", new String[]{"incomplete"});
    queryParams.put("limit", new String[]{"5"});
    Todo[] todos1 = db.listTodos(queryParams);

    for (int i = 0; i < todos1.length; i++) {

      assertEquals("Wrong string", "Blanche", todos1[i].owner);
      assertEquals("Wrong status", true, todos1[i].status == (false));
    }
    assertEquals("Wrong size returned", 5, todos1.length);

  }
}
