package exercise.controller;

//import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
// import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;


// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {


    private PostDTO postToDTO(Post post) {
        PostDTO dto = new PostDTO();



        // List<CommentDTO> listComDto = new ArrayList<>();

        var comm = commentRepository.findByPostId(post.getId());

        var listComDto = comm.stream()
                .map((com) -> {
                    var cDTO = new CommentDTO();
                    cDTO.setId(com.getId());
                    cDTO.setBody(com.getBody());
                    return cDTO;
                }).toList();


/*
        for (var comment : comm) {
            var comDto = new CommentDTO();
            comDto.setId(comment.getId());
            comDto.setBody(comment.getBody());
            listComDto.add(comDto);
        }
*/

        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setBody(post.getBody());
        dto.setComments(listComDto);

        return dto;
    }

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    public List<PostDTO> showAll() {

       // List<PostDTO> retList = new ArrayList<>();

        List<Post> posts = postRepository.findAll();

        var retList = posts.stream()
                .map(this::postToDTO)
                .toList();
        /*
        for (Post post : posts) {
            retList.add(postToDTO(post));
        }
        */
        return retList;
    }

    @GetMapping(path = "/{id}")
    public PostDTO showPost(@PathVariable Long id) {
        var post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
        return postToDTO(post);
    }

}

// END
