@import exercise.dto.MainPage
@import exercise.util.NamedRoutes

@param MainPage page

@template.layout.page(
content = @`
    <main>
        @if(page.getName() != null)
            Вы уже тут были товарищ ${page.getName().toString()}
            <form action="${NamedRoutes.logoutPath()}" method="post">
                <input type="submit" class="btn btn-primary" value="Выйти" />
            </form>
        @else
            <p> Новый пользователь </p>
            <a href="${NamedRoutes.buildSessionPath()}">Войти</a>
        @endif
    </main>
`
)