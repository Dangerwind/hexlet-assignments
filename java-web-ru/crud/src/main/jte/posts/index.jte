
@import exercise.dto.posts.PostsPage
@param PostsPage page

@template.layout.page(
content = @`
    <div class="btn-group" role="group" aria-label="Button group with nested dropdown">
        @if (page.getCurrentPage() <= 1)
            <a href="#" class="btn btn-primary">-----</a>
        @else
            <a href="/posts?page=${page.getCurrentPage() - 1}" class="btn btn-primary">Назад</a>
        @endif

        <a href="#" class="btn btn-primary">${page.getCurrentPage()}</a>

        @if (page.getCurrentPage()<page.getMaxPages())
            <a href="/posts?page=${page.getCurrentPage() + 1}" class="btn btn-primary">Вперед</a>
        @else
            <a href="#" class="btn btn-primary">------</a>
        @endif
    </div>

    <h2>Список постов</h2>





    @for(var post : page.getPosts())
        <ul>
            <li><a href="/posts/${post.getId()}">${post.getName()}</a></li>
            <p>${post.getBody()}</p>
        </ul>
    @endfor


`)