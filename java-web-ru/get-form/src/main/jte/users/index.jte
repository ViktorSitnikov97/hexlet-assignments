@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
    content = @`
    <form class="input-group" action="/users">
        <input type="search" class="form-control rounded" placeholder="Search" aria-label="Search" aria-describedby="search-addon" name="term" value="${page.getTerm()}" />
        <input type="submit" class="btn btn-outline-primary" value="Search">
    </form>
    @for(var user : page.getUsers())
        <table class="table table-striped text-center p-5 text-dark">
            <tr>
                <td>
                    <p>${user.getFirstName()} ${user.getLastName()}</p>
                </td>
            </tr>
        </table>
    @endfor
    `,
    footer = @`
        <p>Just do it</p>
    `
)


