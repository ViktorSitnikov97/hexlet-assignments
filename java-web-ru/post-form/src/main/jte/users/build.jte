@template.layout.page(
    content = @`
        <form action="/users" method="post">
            <div>
                <label>
                    First Name
                    <input type="text" required name="firstName">
                </label>
            </div>
            <div>
                <label>
                    Last Name
                    <input type="text" required name="lastName">
                </label>
            </div>
            <div>
                <label>
                    Email
                    <input type="email" required name="email">
                </label>
            </div>
            <div>
                <label>
                    Password
                    <input type="password" required name="password">
                </label>
            </div>
            <input type="submit" value="Sign Up">
        </form>
    `
)