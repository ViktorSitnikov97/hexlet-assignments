@import exercise.dto.posts.PostPage
@import exercise.util.NamedRoutes
@param PostPage page

@template.layout.page(

    content = @`
        <div class="mb-3">
            <h1>${page.getPost().getName()}</h1>
        </div>

        <div class="mb-3">
            ${page.getPost().getBody()}
        </div>
        <div>
            <a href="${NamedRoutes.postsPath()}" class="btn btn-primary">Вернуться к списку постов</a>
        </div>
    `,
)
