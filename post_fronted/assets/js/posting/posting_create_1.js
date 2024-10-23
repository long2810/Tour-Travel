const post_title = document.getElementById("post-title");
const post_content = document.getElementById("post-content");

const formPost_1 = document.getElementById("post-create-1");

formPost_1.addEventListener("submit", e => {
    e.preventDefault();

    const dataPost = {
        title : post_title.value,
        content : post_content.value
    }
})