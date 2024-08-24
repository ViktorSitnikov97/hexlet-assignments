@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
    content = @`
        <h1>Пользователи</h1>
        <table class="table table-striped">
            @for(var user : page.getUsers())
                <tr>
                    <td>
                        <p><a href="/users/${user.getId()}">${user.getFirstName()} ${user.getLastName()}</a></p>
                    </td>
                </tr>
            @endfor
        </table>
    `,
    footer = @`
        <p>Just do it</p>
    `
)
