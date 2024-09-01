@import exercise.dto.articles.BuildArticlePage
@param BuildArticlePage page
@template.layout.page(
    content = @`
        @if(page.getErrors() != null)
            <ul>
                @for(var validation : page.getErrors().values())
                    @for(var error : validation)
                        <li>${error.getMessage()}</li>
                    @endfor
                @endfor
            </ul>
        @endif
        <form action="/articles" method="post">
            <div>
                <label>
                    Title
                    <input type="text" name="title" value="${page.getTitle()}">
                </label>
            </div>
            <div>
                <label>Content:</label>
                <label>
                    <textarea name="content" rows="5" cols="33">${page.getContent()}</textarea>
                </label>
            </div>
            <input type="submit" value="Publish">
        </form>
    `
)
