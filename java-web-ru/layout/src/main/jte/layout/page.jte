
@import gg.jte.Content
@param Content content
@param Content footer = null

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Пример страницы</title>
</head>
<body>
<p>
    <a href="/">Домой </a>
    <a href="/users">Посмотреть всех пользователей</a>
    <a href="/users/5">Посмотреть только 5 пользователя</a>
</p>
<div>
    ${content}
</div>

@if (footer != null)
    <div>
        ${footer}
    </div>
@endif
</body>
</html>