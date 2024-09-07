package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;

import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;


public class PostsController {

    // BEGIN
    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        Post post = PostRepository.find(id).orElseThrow(() -> new NotFoundResponse("Page not found"));
        var page = new PostPage(post);
        ctx.render("posts/show.jte", model("page", page));
    }

    public static void index(Context ctx) {
        var numberPage = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
        final int pageSize = 5;
        var posts = PostRepository.findAll(numberPage, pageSize);

        var page = new PostsPage(posts, numberPage);
        ctx.render("posts/index.jte", model("page", page));
    }
    // END
}
