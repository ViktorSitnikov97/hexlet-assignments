@import exercise.dto.LoginPage
@import exercise.util.NamedRoutes
@param LoginPage page
@template.layout.page(
content = @`
    @if(page.getError() != null)
        <ul class="text-danger">
            <li>${page.getError()}</li>
        </ul>
    @endif
    <form action="${NamedRoutes.loginPath()}" method="post" class="text-center p-5">
        <div class="mb-3">
            <label class="form-label">
                name
                <input type="text" class="form-control" required name="name"/>
            </label>
        </div>
        <div class="mb-3">
            <label class="form-label">
                password
                <input type="password" class="form-control" required name="password" />
            </label>
        </div>
        <input type="submit" class="btn btn-primary" value="Войти" />
    </form>
`
)
