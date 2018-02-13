package umm3601.todo;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * A fake "database" of user info
 *
 * Since we don't want to complicate this lab with a real database,
 * we're going to instead just read a bunch of user data from a
 * specified JSON file, and then provide various database-like
 * methods that allow the `UserController` to "query" the "database".
 */
public class TodoDatabase{

  private Todo[] allTodos;

  public TodoDatabase(String todoDataFile) throws IOException {
    Gson gson = new Gson();
    FileReader reader = new FileReader(todoDataFile);
    allTodos = gson.fromJson(reader, Todo[].class);
  }

  /**
   * Get the single user specified by the given ID. Return
   * `null` if there is no user with that ID.
   *
   * @param id the ID of the desired user
   * @return the user with the given ID, or null if there is no user
   * with that ID
   */
  public Todo getTodo(String id) {
    return Arrays.stream(allTodos).filter(x -> x._id.equals(id)).findFirst().orElse(null);
  }

  /**
   * Get an array of all the users satisfying the queries in the params.
   *
   * @param queryParams map of required key-value pairs for the query
   * @return an array of all the users matching the given criteria
   */
  public Todo[] listTodos(Map<String, String[]> queryParams) {
    Todo[] filteredTodos = allTodos;

    // Filter age if defined
    if(queryParams.containsKey("status")) {
      String targetStatus = queryParams.get("status")[0];
      filteredTodos = filterTodosByStatus(filteredTodos, targetStatus);
    }
    // Process other query parameters here...
    if(queryParams.containsKey("limit")) {
      int targetLimit = Integer.parseInt(queryParams.get("limit")[0]);
      filteredTodos = filterTodosByLimit(filteredTodos, targetLimit);
    }

    if(queryParams.containsKey("contains")) {
      String targetBody = queryParams.get("contains")[0];
      filteredTodos = filterTodosByBody(filteredTodos, targetBody);
    }

    if(queryParams.containsKey("owner")) {
      String targetOwner = queryParams.get("owner")[0];
      filteredTodos = filterTodosByOwner(filteredTodos, targetOwner);
    }

    if(queryParams.containsKey("category")) {
      String targetCategory = queryParams.get("category")[0];
      filteredTodos = filterTodosByCategory(filteredTodos, targetCategory);
    }

    if(queryParams.containsKey("orderBy")) {
      if (queryParams.get("orderBy")[0].equals("owner")){
        filteredTodos = filterTodosByOBOwner(filteredTodos);
      }
      if (queryParams.get("orderBy")[0].equals("status")){
        filteredTodos = filterTodosByOBStatus(filteredTodos);
      }
      if (queryParams.get("orderBy")[0].equals("body")){
        filteredTodos = filterTodosByOBBody(filteredTodos);
      }
      if (queryParams.get("orderBy")[0].equals("category")){
        filteredTodos = filterTodosByOBCategory(filteredTodos);
      }
    }
    return filteredTodos;
  }

  /**
   * Get an array of all the users having the target age.
   *
   * @param todos the list of users to filter by age
   * @param targetStatus the target age to look for
   * @return an array of all the users from the given list that have
   * the target age
   */
  public Todo[] filterTodosByStatus(Todo[] todos, String targetStatus) {
    if (targetStatus.equals("complete")) {
      return Arrays.stream(todos).filter(x -> (x.status)==(true)).toArray(Todo[]::new);
    } else {
      return Arrays.stream(todos).filter(x -> (x.status)==(false)).toArray(Todo[]::new);
    }
  }

  public Todo[] filterTodosByLimit(Todo[] todos, int targetLimit) {
    return Arrays.stream(todos).limit(targetLimit).toArray(Todo[]::new);
  }

  public Todo[] filterTodosByBody(Todo[] todos, String targetBody) {
    return Arrays.stream(todos).filter(x -> x.body.contains(targetBody)).toArray(Todo[]::new);
  }

  public Todo[] filterTodosByOwner(Todo[] todos, String targetOwner) {
    return Arrays.stream(todos).filter(x -> x.owner.equals(targetOwner)).toArray(Todo[]::new);
  }

  public Todo[] filterTodosByCategory(Todo[] todos, String targetCategory) {
    return Arrays.stream(todos).filter(x -> x.category.equals(targetCategory)).toArray(Todo[]::new);
  }

  public Todo[] filterTodosByOBStatus(Todo[] todos) {
    return Arrays.stream(todos).sorted((a,b)-> String.valueOf(a.status).compareTo(String.valueOf(b.status))).toArray(Todo[]::new);
  }

  public Todo[] filterTodosByOBOwner(Todo[] todos) {
    return Arrays.stream(todos).sorted((a,b)-> a.owner.compareTo(b.owner)).toArray(Todo[]::new);
  }

  public Todo[] filterTodosByOBBody(Todo[] todos) {
    return Arrays.stream(todos).sorted((a,b)-> a.body.compareTo(b.body)).toArray(Todo[]::new);
  }

  public Todo[] filterTodosByOBCategory(Todo[] todos) {
    return Arrays.stream(todos).sorted((a,b)-> a.category.compareTo(b.category)).toArray(Todo[]::new);
  }



}
