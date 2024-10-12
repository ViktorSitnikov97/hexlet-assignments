@import exercise.dto.MainPage
@import exercise.util.NamedRoutes
@param MainPage page

@template.layout.page(content = @`
    @if(page.getName() == null)
        <a href="${NamedRoutes.buildSessionPath()}">Войти</a>
    @else
        <div class="text-center">
            <p>${String.valueOf(page.getName())}</p>
            <form action="${NamedRoutes.logoutPath()}" method="post" class="text-center p-5">
                <input type="submit" class="btn btn-primary" value="Выйти" />
            </form>
        </div>
    @endif
`
)
