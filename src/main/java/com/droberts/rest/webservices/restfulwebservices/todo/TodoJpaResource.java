package com.droberts.rest.webservices.restfulwebservices.todo;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

//import com.droberts.rest.webservices.restfulwebservices.todo.Todo;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class TodoJpaResource {

	@Autowired
	private TodoHardcodedService todoService;
	
	@Autowired
	private TodoJpaRepository todoJpaRepository;
	
	@GetMapping("/jpa/users/{username}/todos")
	public List<Todo> getAllTodos(@PathVariable String username) {
		return todoJpaRepository.findByUsername(username);
		//return todoService.findAll();
	}
	
	@GetMapping("/jpa/users/{username}/todos/{id}")
	public Todo getTodo(@PathVariable String username, @PathVariable long id) {
		return todoJpaRepository.findById(id).get();
		//return todoService.findById(id);
	}
	
	/* Rest API standards:
	 * Deleting content should return ResponseEntity.noContent().build() on success or ResponseEntity.notFound().build() on fail
	 * Updating content should return a status of OK (200) along with the content of the updated resource.
	 * Creating content should return a status of created (201) with the URI of the created resource.
	 * */
	
	//edit a todo, use a put
	@PutMapping("/jpa/users/{username}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable String username, @PathVariable long id, @RequestBody Todo todo) { //notice ResponseEntity is Todo, not void
		todo.setUsername(username);
		Todo todoUpdated = todoJpaRepository.save(todo);
		return new ResponseEntity<Todo>(todo, HttpStatus.OK);
	}
	
	//create a todo, use a post
	@PostMapping("/jpa/users/{username}/todos")
	public ResponseEntity<Void> createTodo(@PathVariable String username, @RequestBody Todo todo) { //notice ResponseEntity is Todo, not void
		todo.setUsername(username);
		Todo createdTodo = todoJpaRepository.save(todo);
		
		//ResponseEntity<Void> should return location so
		//get current resource url (/users/{username}/todos
		//and append {id}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdTodo.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	
	//delete todo /users/{username}/todos/{id}
	@DeleteMapping("/jpa/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id) { //deletes a todo and returns ResponseEntity to show if it's successful or not.
		todoJpaRepository.deleteById(id);
		return ResponseEntity.noContent().build();
		
//		Todo todo = todoService.deleteById(id);
//		if(todo != null) {
//			return ResponseEntity.noContent().build(); //if we get the deleted todo back as a response, we know we deleted it successfully
//		}
//		
//		return ResponseEntity.notFound().build(); //if we couldn't find it, it didn't get deleted
	}
}
