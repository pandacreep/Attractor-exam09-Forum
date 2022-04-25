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
        <h1 class="my-3">Please login</h1>
        <form action="/login" method="post">
            <div>
                <#if error!false >
                    <p style="color:red">Incorrect username or password</p>
                </#if>
            </div>
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
            </#if>
            <div class="row">
                <label class="flex flex-v-center">
                    <p class="label-input-text mb-1">E-mail:</p>
                    <input type="text" name="username" placeholder="your email"  value="${(form.email)!''}" >
                    <#if errors?? >
                        <#list errors as fieldError>
                            <#if fieldError.field == 'email'>
                                <p class="text-danger my-0">${fieldError.defaultMessage!'-- no message --'}</p>
                            </#if>
                        </#list>
                    </#if>
                </label>
            </div>
            <div class="row mt-3">
                <label class="flex flex-v-center">
                    <p class="label-input-text mb-1">Password:</p>
                    <input type="password" name="password" placeholder="your password"  value="">
                    <#if errors?? >
                        <#list errors as fieldError>
                            <#if fieldError.field == 'password'>
                                <p class="text-danger my-0">${fieldError.defaultMessage!'-- no message --'}</p>
                            </#if>
                        </#list>
                    </#if>
                </label>
            </div>
            <div class="mt-3">
                <button class="btn btn-primary" type="submit">Login</button>
            </div>
            <br>
            <p>Demo accounts:</p>
            <p>1 - username: anna@gmail.com / password: 123</p>
            <p>2 - username: oleg@gmail.com / password: 123</p>
            <p>3 - username: mike@gmail.com / password: 123</p>
        </form>
    </div>

</@main.renderWith>