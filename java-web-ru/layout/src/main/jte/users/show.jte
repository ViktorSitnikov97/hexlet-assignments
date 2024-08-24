@import exercise.dto.users.UserPage
@param UserPage page

@template.layout.page(
    content = @`
    <h3>${page.getUser().getFirstName()}  ${page.getUser().getLastName()}</h3>
    @if(page.getUser().getEmail() != null)
        <p>Email: ${page.getUser().getEmail()}</p>
    @else
        <p>Email: not found</p>
    @endif
    `
)
