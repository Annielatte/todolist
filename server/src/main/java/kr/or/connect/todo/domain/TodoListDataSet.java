package kr.or.connect.todo.domain;

import java.sql.Timestamp;

public class TodoListDataSet {
	private int id;
	private String todo;
	private boolean completed;
	private Timestamp date;
	
	public TodoListDataSet(){}
	public TodoListDataSet(int id, String todo, boolean completed,  Timestamp date) {
        this.id = id;
        this.todo = todo;
        this.completed = completed;
        this.date = date;
    }

	public void setId(int id){
		this.id = id;
	}
	
	public void setTodo(String todo){
		this.todo = todo;
	}
	
	public void setCompleted(boolean completed){
		this.completed = completed;
	}
	
	public void setDate( Timestamp date){
		this.date = date;
	}
	
	public int getId(){
		return id;
	}
	
	public String getTodo(){
		return todo;
	}
	
	public boolean getCompleted(){
		return completed;
	}
	
	public Timestamp getDate(){
		return date;
	}
}
