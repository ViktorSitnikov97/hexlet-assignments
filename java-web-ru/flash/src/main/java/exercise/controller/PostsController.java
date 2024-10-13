package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.dto.posts.BuildPostPage;
import exercise.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.validation.ValidationException;
import io.javalin.http.NotFoundResponse;

import java.util.List;

public class PostsController {

    public static void build(Context ctx) {
        var page = new BuildPostPage();
        ctx.render("posts/build.jte", model("page", page));
    }

    // BEGIN
    public static void create(Context ctx) {

        try {
            var description = ctx.formParam("body");
            var title = ctx.formParamAsClass("name", String.class)
                    .check(value -> value.length() >= 2, "Название должно быть не короче двух символов")
                    .get();

            var post = new Post(title, description);
            PostRepository.save(post);
            ctx.sessionAttribute("flash", "Post was successfully created!");
            ctx.sessionAttribute("flash-type", "success");
            ctx.redirect(NamedRoutes.postsPath());
        } catch (ValidationException e) {
            var page = new BuildPostPage(null, null, e.getErrors());
            ctx.render("posts/build.jte", model("page", page));
        }
    }
    public static void index(Context ctx) {
        List<Post> posts = PostRepository.getEntities();
        var page = new PostsPage(posts);
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        page.setFlashType(ctx.consumeSessionAttribute("flash-type"));
        ctx.render("posts/index.jte", model("page", page));
    }
    // END

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id)
            .orElseThrow(() -> new NotFoundResponse("Post not found"));

        var page = new PostPage(post);
        ctx.render("posts/show.jte", model("page", page));
    }
}
