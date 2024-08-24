@import exercise.dto.users.UsersPage
@param UsersPage page

<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>Users</title>
    </head>
    <body>
        <div class="col-lg-8 mx-auto p-4 py-md-5">
            <main>
                @if(page.getUsers().isEmpty())
                    <p>Пока нет ни одного пользователя!</p>p>
                @else
                    @for(var user : page.getUsers())
                        <div>
                            <h2><a href="/users/${user.getId()}">${user.getFirstName() + " " + user.getLastName()}</a></h2>
                        </div>
                    @endfor
                @endif
            </main>
        </div>
    </body>
</html>