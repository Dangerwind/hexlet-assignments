@import exercise.dto.articles.ArticlesPage
@import exercise.dto.articles.BuildArticlePage
@param BuildArticlePage page

@template.layout.page(
content = @`

    @if(page.getErrors() != null)
        <ul>
            @for(var validator : page.getErrors().values())
                @for(var error : validator)
                    <li>${error.getMessage()}</li>
                @endfor
            @endfor
        </ul>
    @endif


    <div>
       <p> Пишем статью </p>
    </div>
    <form action="/articles" method="post">
        <div>
            <label>
                Название статьи
                <input type="text" name="title" value="${page.getTitle()}"/>
            </label>
        </div>
        <div>
            <label>
                Статья
                <input type="text" name="content" value="${page.getContent()}"/>
            </label>
        </div>
        <input type="submit" value="Отправить статью" />
    </form>
`
)