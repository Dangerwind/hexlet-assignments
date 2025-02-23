@import exercise.dto.users.UserPage
@import exercise.dto.users.UsersPage
@param UsersPage page

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Hello Hexlet!</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>

<div>
    <h1>Users information</h1>
</div>

<table class="table">
    <thead class="thead-light">
    <tr>
        <th scope="col">id</th>
        <th scope="col">First Name</th>
        <th scope="col">Last Name</th>
        <th scope="col">Email</th>
    </tr>
    </thead>

    @for(var iter :  page.getUser())
    <tbody>
    <tr>
        <th scope="row">${iter.getId()}</th>
        <td>${iter.getFirstName()}</td>
        <td>${iter.getLastName()}</td>
        <td>@${iter.getEmail()}</td>
    </tr>
    </tbody>
    @endfor
    
    
</table>

</body>
</html>>