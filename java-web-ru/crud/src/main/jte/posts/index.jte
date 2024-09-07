@import exercise.dto.posts.PostsPage
@import exercise.repository.PostRepository
@import exercise.util.NamedRoutes
@param PostsPage page
@template.layout.page(
content = @`
    <div class="mx-auto p-4 py-md-5">
        <main>
            <h1>Все посты</h1>

            <table class="table table-striped">
                @for(var post : page.getPosts())
                    <tr>
                        <td>
                            ${post.getId()}
                        </td>
                        <td>
                            <a href="${NamedRoutes.postPath(post.getId())}">${post.getName()}</a>
                        </td>
                        <td>
                            ${post.getBody()}
                        </td>
                    </tr>
                @endfor
            </table>
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <li class="page-item">
                        @if(page.getNumberPage() - 1 != 0)
                            <a class="page-link" href="?page=${page.getNumberPage() - 1}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        @endif
                    </li>
                    <li class="page-item"><a class="page-link" href="${NamedRoutes.postsPath()}?page=${page.getNumberPage()}">${page.getNumberPage()}</a></li>
                    <li class="page-item">
                        @if(PostRepository.getEntities().size() - page.getNumberPage() * 5 >= 0)
                            <a class="page-link" href="?page=${page.getNumberPage() + 1}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        @endif
                    </li>
                </ul>
            </nav>
        </main>
    </div>
`
)