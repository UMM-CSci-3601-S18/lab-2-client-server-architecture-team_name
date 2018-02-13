package umm3601.todo;

import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

/**
 * Tests umm3601.todo.TodoDatabase getTodo functionality
 */
public class GetTodoByIDFromDB {

  @Test
  public void getStokesClayton() throws IOException {
    TodoDatabase db = new TodoDatabase("src/main/data/todos.json");
    Todo todo = db.getTodo("58895985a22c04e761776d54");
    assertEquals("Incorrect name", "Blanche", todo.owner);
  }


}

