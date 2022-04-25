'use strict';

const serverPath = 'http://localhost:8080/api';
document.getElementById('addPostForm').addEventListener('submit', addComment);
document.getElementById('scroll-down').addEventListener('click', e => {
    e.stopPropagation();
    e.preventDefault();
    window.scrollTo(0, document.body.scrollHeight);
})



async function sendAsyncRequest(url) {
    const data = await fetch(url, {cache: 'no-cache'});
    return data.json();
};

async function addComment(e) {
    e.stopPropagation();
    e.preventDefault();
    let url = `${serverPath}/comments/add`;
    let fTopicId = document.getElementById('fTopicId').value;
    let fText = document.getElementById('fText').value;

    url += `?id=${fTopicId}`;
    url += `&text=${fText}`;
    if (fText != "") {
        const data = await sendAsyncRequest(url);
        alert('Comment was added');
    } else {
        alert('Empty text!');
    }
    document.getElementById('fText').value = "";
    window.location.href = "/topics/" + fTopicId;
}

