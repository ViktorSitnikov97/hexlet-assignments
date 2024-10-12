package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.repository.UsersRepository;
import static exercise.util.Security.encrypt;
import exercise.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.validation.ValidationException;

public class SessionsController {

    // BEGIN
    public static void index(Context ctx) {
        var page = new MainPage(ctx.sessionAttribute("session"));
        ctx.render("index.jte", model("page", page));
    }
    public static void build(Context ctx) {
        var page = new LoginPage(null, null);
        ctx.render("build.jte", model("page", page));
    }

    public static void create(Context ctx) {

        try {
            var name = ctx.formParamAsClass("name", String.class)
                    .check(UsersRepository::existsByName,"Нет такого имени")
                    .get();
            var user = UsersRepository.findByName(name);
            var password = ctx.formParamAsClass("password", String.class)
                    .check(value -> encrypt(value).equals(user.get().getPassword()), "Пароли не совпадают")
                    .get();
            ctx.sessionAttribute("session", name);
            ctx.status(302);
            ctx.redirect(NamedRoutes.rootPath());
        } catch(ValidationException e) {
            var page = new LoginPage(null, "Wrong username or password");
            ctx.status(302);
            ctx.render("build.jte", model("page", page));
        }
    }

    public static void destroy(Context ctx) {
        ctx.sessionAttribute("session", null);
        ctx.status(302);
        ctx.redirect(NamedRoutes.rootPath());
    }
    // END
}
