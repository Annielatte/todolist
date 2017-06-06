package kr.or.connect.todo.persistence;

public class TodoSqls {
	static final String DELETE_BY_ID =
			"DELETE FROM todo WHERE id= :id";
	
    static final String SELECT_TODOLIST =
            "SELECT * FROM todo ORDER BY ID DESC";
    
    static final String UPDATE_BY_ID =
            "UPDATE todo SET completed = 1 WHERE id= :id";

}

