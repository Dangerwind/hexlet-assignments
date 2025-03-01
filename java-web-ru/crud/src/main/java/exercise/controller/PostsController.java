package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;

import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class PostsController {

    // BEGIN

    public static void showPost(Context ctx) {
        try {
            var id = ctx.pathParamAsClass("id", Long.class).get();
            var post = PostRepository.find(id).orElseThrow(() -> new NotFoundResponse("Page not found"));
            var page = new PostPage(post);
            ctx.render("posts/show.jte", model("page", page));
        } catch (NotFoundResponse e){
            ctx.status(404);
            ctx.result(String.valueOf(e));
        }
    }

    public static void showAllPosts(Context ctx) {
        var pageNum = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
        var posts = PostRepository.getEntities();
        var maxPosts = (int) Math.ceil((double) posts.size() / 5);
        posts = PostRepository.findAll(pageNum,5);
        var page = new PostsPage(pageNum, maxPosts, posts);
        ctx.render("posts/index.jte", model("page", page));
    }
    
    // END
}
