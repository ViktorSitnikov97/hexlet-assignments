@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes
@param PostsPage page
@template.layout.page(
    page = page,
    content = @`
        @for(var post : page.getPosts())
            <tr>
                <td>
                    <a href="${NamedRoutes.postPath(post.getId())}">${post.getName()}</a>
                </td>
            </tr>
        @endfor

    `
)
