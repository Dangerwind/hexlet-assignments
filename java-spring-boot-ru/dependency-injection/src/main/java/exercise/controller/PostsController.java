package exercise.controller;

import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN

@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

     //GET /posts — список всех постов
    @GetMapping(path = "")
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // GET /posts/{id} – просмотр конкретного поста
    @GetMapping(path = "{id}")
    public Post getPostById(@PathVariable("id") Long id) {
        return postRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Post with id "+ id + " not found"));
    }

    // POST /posts – создание нового поста. При успешном создании возвращается статус 201
    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@RequestBody Post post) {
        return postRepository.save(post);
    }

    // PUT /posts/{id} – обновление поста
    @PutMapping(path = "{id}")
    public Post updatePost(@PathVariable("id") Long id, @RequestBody Post post) {
        var postFind = postRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Post with id "+ id + " not found"));
        postFind.setTitle(post.getTitle());
        postFind.setBody(post.getBody());
        return postRepository.save(postFind);
    }

    // DELETE /posts/{id} – удаление поста. При удалении поста удаляются все комментарии этого поста
    @DeleteMapping(path = "{id}")
    public void deletePost(@PathVariable("id") Long id) {
        postRepository.deleteById(id);
        commentRepository.deleteByPostId(id);
    }

}
// END
