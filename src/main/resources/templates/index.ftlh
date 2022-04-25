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
        <h1 class="my-3 text-center">Topics</h1>
        <div class="text-start row">
            <div class="col-3"><b>Name</b></div>
            <div class="col-3"><b>Date</b></div>
            <div class="col-3"><b>User</b></div>
            <div class="col-3"><b>Count</b></div>
        </div>
        <#list items as topic>
            <a class="topic-link" href="/topics/${topic.id}">
                <div class="row-as-link text-start row">
                    <div class="col-3">${topic.name}</div>
                    <div class="col-3">${topic.date}</div>
                    <div class="col-3">${topic.user}</div>
                    <div class="col-3">${topic.postCount}</div>
                </div>
            </a>
        </#list>
        <#include "partials/pagination.ftlh">
    </div>

</@main.renderWith>