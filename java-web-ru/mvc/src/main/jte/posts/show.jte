@import exercise.dto.posts.PostPage
@import exercise.util.NamedRoutes
@param PostPage page

@template.layout.page(
    content = @`
        <main>
            <div class="mx-auto p-4 py-md-5">
                <h1>${page.getPost().getName()}</h1>
            </div>

            <div class="mx-auto p-4 py-md-5">
                ${page.getPost().getBody()}
            </div>
            <!-- BEGIN -->
            <div class="mx-auto p-4 py-md-5">
                <a class="btn btn-primary btn-sm" href="${NamedRoutes.editPostPath(page.getPost().getId())}" role="button">Редактировать</a>
            </div>
            <!-- END -->
        </main>
    `
)
