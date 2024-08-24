@import exercise.dto.users.UserPage
@param UserPage page

<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>User</title>
    </head>
    <body>
        <div class="col-lg-8 mx-auto p-4 py-md-5">
            <main>
            @if(page.getUser().getEmail() == null)
                <p>email: отсутствует</p>
            @else
                <h3>${page.getUser().getFirstName() + " " + page.getUser().getLastName()}</h3>
                <p>email:  ${page.getUser().getEmail()}</p>
            @endif
            </main>
        </div>
    </body>
</html>