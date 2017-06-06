package kr.or.connect.todo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.connect.todo.domain.TodoListDataSet;
import kr.or.connect.todo.persistence.TodoDao;

@Service
public class TodoListService {
	private TodoDao dao;
	
	public TodoListService(TodoDao dao){
		this.dao = dao;
	}
	
	public List<TodoListDataSet> getListAll(){
		return dao.todolistAll();
	}
	
	public int insertList(TodoListDataSet data){
		return dao.insertList(data);
	}
	
	public void deleteList(int id){
	      dao.deleteList(id);
	   }
	
	public void completedList(int id){
		dao.completedList(id);
	}
}
