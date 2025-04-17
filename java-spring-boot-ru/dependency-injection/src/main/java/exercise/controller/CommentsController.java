package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;

import java.util.List;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN

@RestController
@RequestMapping("/comments")
public class CommentsController{

    @Autowired
    private CommentRepository commentRepository;

    //GET /comments — список всех комментариев
    @GetMapping(path = "")
    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    //GET /comments/{id} – просмотр конкретного комментария
    @GetMapping(path = "{id}")
    public Comment getComment(@PathVariable Long id) {
        return commentRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Post with id "+ id + " not found"));
    }
    // POST /comments – создание нового комментария. При успешном создании возвращается статус 201
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "")
    public Comment createComment(@RequestBody Comment comment) {
        return commentRepository.save(comment);
    }

    //PUT /comments/{id} – обновление комментария
    @PutMapping(path = "{id}")
    public Comment updateComment(@PathVariable Long id, @RequestBody Comment comment) {
        var comFind = commentRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Post with id "+ id + " not found"));
        comFind.setBody(comment.getBody());
        comFind.setPostId(comment.getPostId());
        return commentRepository.save(comFind);
    }

    //DELETE /comments/{id} – удаление комментария
    @DeleteMapping(path = "{id}")
    public void deleteComment(@PathVariable Long id) {
        commentRepository.deleteById(id);
    }

}
// END
