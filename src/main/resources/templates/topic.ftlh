<#import "partials/main.ftlh" as main>

<@main.renderWith scripts=["/js/topic.js"]>
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
        <h1 class="my-3">Topic '${topic.name}'</h1>
        <div class="row flex-wrap">
            <div class="col-3">
                Topic name: ${topic.name}
            </div>
            <div class="col-3">
                Creation date: ${topic.date}
            </div>
            <div class="col-3">
                Author: ${topic.user}
            </div>
        </div>
        <br>
        <div class="row flex-wrap" style="border: black solid 1px; padding: 5px">
            <div>
                ${topic.description}
            </div>
            <br>
            <a id="scroll-down" class="as-button col-1" href="">Answer</a>
        </div>
        <br>
        <div class="posts" id="posts">
            <#list items as post>
                <div class="row">
                    <div class="col-1">
                        <img style="height: 50px; width: 50px" src="${'data:image/png;base64,'+post.userAvatar}" alt="avatar"/>
                    </div>
                    <div class="col-10">
                        <div class="text-start row">
                            <i>${post.dateTime}; ${post.user}; posts count:${post.userPostCount}</i>
                        </div>
                        <div class="text-start row">
                            ${post.text}
                        </div>
                    </div>
                </div>
                <hr>
            </#list>
        </div>
        <#include "partials/pagination.ftlh">
        <br>
        <form id="addPostForm"><#-- action="/addPost" method="post"-->
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
            </#if>
            <div class="row">
                <label class="flex flex-v-center">
                    <p class="label-input-text mb-1">Your comment:</p>
                    <input id="fTopicId" type="text" name="topicId" value="${topic.id}" hidden>
                    <textarea rows="3" cols="45" id="fText" name="text" value="${(form.text)!''}" ></textarea>
                    <#if errors?? >
                        <#list errors as fieldError>
                            <#if fieldError.field == 'text'>
                                <p class="text-danger my-0">${fieldError.defaultMessage!'-- no message --'}</p>
                            </#if>
                        </#list>
                    </#if>
                </label>
            </div>
            <div class="mt-3">
                <button class="btn btn-primary" type="submit">Add post</button>
            </div>
        </form>
        <br>
    </div>

</@main.renderWith>