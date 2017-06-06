package kr.or.connect.todo.api;

import kr.or.connect.todo.Service.TodoListService;
import kr.or.connect.todo.domain.TodoListDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoListService service;

    @Autowired
    public TodoController(TodoListService service) {
        this.service = service;
    }

    @GetMapping
    List<TodoListDataSet> todoList() {
        return service.getListAll();
    }


    @PostMapping
    @ResponseBody TodoListDataSet insertList(@RequestBody TodoListDataSet data) {
    	service.insertList(data);
        return data;
    }
    

	@DeleteMapping("/{id}")
	//@ResponseStatus(HttpStatus.NO_CONTENT)
	void delete(@PathVariable int id) {
		service.deleteList(id);
	}
	
	@PutMapping("/{id}")
	void completed(@PathVariable int id){
		service.completedList(id);
	}
	
   /* @PutMapping(value = "/{id}", consumes = "application/json")
    ResponseEntity<?> update(@PathVariable Integer id, @RequestBody TodoListDataSet todo) {
        todo.setId(id);
        AjaxResult ajaxResult = new AjaxResult();
        if (service.complete(todo)) {
            ajaxResult.setResult("SUCS");

        } else {
            ajaxResult.setResult("FAIL");

        }
        return new ResponseEntity<AjaxResult>(ajaxResult, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Integer id) {
        AjaxResult ajaxResult = new AjaxResult();

        if (service.delete(id)) {
            ajaxResult.setResult("SUCS");

        } else {
            ajaxResult.setResult("FAIL");

        }
        return new ResponseEntity<AjaxResult>(ajaxResult, HttpStatus.OK);

    }*/
}
