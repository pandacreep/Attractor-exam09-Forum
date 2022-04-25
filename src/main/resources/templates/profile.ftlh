<#import "partials/main.ftlh" as main>

<@main.renderWith scripts=[]>
    <header class="header">
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
            <div class="container container-fluid">
                <a class="d-flex justify-content-around navbar-brand" href="#">Forum</a>
                <div class="d-flex justify-content-end collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="/">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="/profile">Profile</a>
                        </li>
                        <#if userLogged == false>
                            <li class="nav-item">
                                <a class="nav-link active" href="/register">Register</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" href="/login">Login</a>
                            </li>
                        </#if>
                        <#if userLogged == true>
                            <li class="nav-item">
                                <a class="nav-link active" href="/add-topic">Add topic</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" href="/quit">Logout</a>
                            </li>
                        </#if>
                    </ul>
                </div>
            </div>
        </nav>
    </header>

    <div class="container">
        <h1 class="my-3">Profile (secured)</h1>
        <div class="row flex-wrap">
            <div class="col-3">
                <img style="height: 200px; width: 200px" src="${'data:image/png;base64,'+user.avatar}" alt="avatar"/>
            </div>
            <div class="col-9">
                <p>Id: ${user.id}</p>
                <p>Name: ${user.name}</p>
                <p>Email: ${user.email}</p>
                <p>Posts quantity: ${user.countPosts}</p>
            </div>
        </div>
    </div>

</@main.renderWith>