@import exercise.dto.posts.PostPage
@import exercise.util.NamedRoutes
@param PostPage page
@template.layout.page(

    content = @`
        <h3>${page.getPost().getName()}</h3>
        <p>Content: ${page.getPost().getBody()}</p>
        <a href="${NamedRoutes.postsPath()}">All posts</a>
    `
)