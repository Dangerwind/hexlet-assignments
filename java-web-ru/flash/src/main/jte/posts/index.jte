
@import exercise.dto.posts.PostsPage
@param PostsPage page
@template.layout.page(

content = @`
    <div class="mx-auto p-4 py-md-5">
        <main>
            <h1>Все посты</h1>
<!--
            <div class="mb-3">
                <a href="/articles/build">Создать новую статью</a>
            </div>
-->
           @if (page != null && page.getFlash() != null)
                <div class="alert alert-primary" role="alert">
                    ${page.getFlash()}
                </div>
            @endif


            <table class="table table-striped">
                @for(var article : page.getPosts())
                    <tr>
                        <td>
                            ${article.getId()}
                        </td>
                        <td>
                            ${article.getName()}
                        </td>
                        <td>
                            ${article.getBody()}
                        </td>
                    </tr>
                @endfor
            </table>
        </main>
    </div>
`
)