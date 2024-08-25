@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
    content = @`
    <form action="/users" method="get" class="text-center p-5">
        <input type="search" name="term" value="${page.getTerm()}">
        <input type="submit" value="Search">
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


