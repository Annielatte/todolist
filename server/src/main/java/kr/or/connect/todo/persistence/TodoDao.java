package kr.or.connect.todo.persistence;

import kr.or.connect.todo.domain.TodoListDataSet;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class TodoDao {
    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;

    public TodoDao(DataSource dataSource) { /* Dao Init*/
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("todo").usingColumns("todo");
    }

    public Integer insertList(TodoListDataSet todo) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(todo);
        return insertAction.execute(params);
    }
    //���� �ٽ� ¥���� test
    public List<TodoListDataSet> todolistAll() {
    	RowMapper<TodoListDataSet> rowMapper = BeanPropertyRowMapper.newInstance(TodoListDataSet.class);
        return jdbc.query(TodoSqls.SELECT_TODOLIST, rowMapper);
 
    }
    
    public void deleteList(int id) {
        SqlParameterSource params = new MapSqlParameterSource("id",id);
        try{
           jdbc.update(TodoSqls.DELETE_BY_ID, params);
        }catch(Exception e){
           e.printStackTrace();
        }
     }
    
    public void completedList(int id){
    	SqlParameterSource params = new MapSqlParameterSource("id",id);
        try{
            jdbc.update(TodoSqls.UPDATE_BY_ID, params);
         }catch(Exception e){
            e.printStackTrace();
         }
    	
    }

}
