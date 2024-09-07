@import exercise.dto.posts.PostsPage
@import exercise.repository.PostRepository
@import exercise.util.NamedRoutes
@param PostsPage page
@template.layout.page(
    content = @`
            @for(var post : page.getPosts())
                <tr>
                    <td>
                        <p><a href="${NamedRoutes.postsPath()}/${post.getId()}">${post.getName()}</a></p>
                    </td>
                </tr>
            @endfor

            @if(page.getNumberPage() - 1 != 0)
                <p><a href="${NamedRoutes.postsPath()}?page=${page.getNumberPage() - 1}">previous</a></p>
            @endif
            @if(PostRepository.getEntities().size() - page.getNumberPage() * 5 >= 0)
                <p><a href="${NamedRoutes.postsPath()}?page=${page.getNumberPage() + 1}">next</a></p>
            @endif

    `
)
