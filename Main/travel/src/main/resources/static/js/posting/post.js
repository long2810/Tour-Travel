const token = localStorage.getItem("token");
if (!token) {
    location.href = "/lu/login";
}

const pathSplit = location.pathname.split("/");
const postId = pathSplit[pathSplit.length - 1];

const postTitle = document.getElementById("post-title");
const postContent = document.getElementById("post-content");
const writerImage = document.getElementById("writer-image");
const writerName = document.getElementById("writer-name");
const numOfLike = document.getElementById("post-like");
const numOfComment = document.getElementById("post-comment");
const postBody = document.getElementById("post-body");
const postImageContainer = document.getElementById("post-image-container");


fetch(`/posting-view/${postId}`, {
    method: "GET",
    header: {
        "Content-type": "Application/json"
    }
})
    .then(response => {
        if (response.ok) {
            return response.json();
        }
        else throw Error(response.statusText);
    })
    .then(json => {
        let imageList;

        if (json.images.length == 0) {
            imageList = [
                "/static/visual/posting/image1.png",
                "/static/visual/posting/image2.png",
                "/static/visual/posting/image3.png"
            ];
        } else {
            imageList = json.images;
        }

        writerImage.src = json.writerAvatar;
        postTitle.innerHTML = json.title;
        writerName.innerHTML = json.writer;

        numOfLike.innerHTML = json.numOfLike == null ? 0 : json.numOfLike;
        numOfComment.innerHTML = json.numOfComment == null ? 0 : json.numOfComment;

        imageList.forEach(url => {
            postImageContainer.insertAdjacentHTML("beforeend", `
                <div class="col-lg-4 col-sm-6 col-xs-12 mb-4">
                <img src="http://localhost:8080${url}" class="w-100" style="height: 230px;">
                </div>
                `)
        });
        postContent.innerHTML = `<p class="fs-5">${json.content}</p>`;
    })


//Comment
const commentList = document.getElementById("comment-list");
const commentWriter = document.getElementById("comment-writer-name");
const commentContent = document.getElementById("comment-content");
fetch(`http://localhost:8080/comments/list/${postId}`, {
    method: "GET",
    header: {
        "Content-type": "Application/json"
    }
})
    .then(response => {
        if (response.ok) {
            return response.json();
        }
        else throw Error(response.statusText);
    })
    .then(json => {
        json.forEach(comment => {
            commentList.insertAdjacentHTML("beforeend",
                `
                <div class="mx-3 my-3">
                <a class="navbar-brand  m-3 fs-5 fw-bold" aria-current="page" href="#" >${comment.writer}</a>
                <p class="m-3" id="comment-content">${comment.content}</p>
                <hr>
            </div>
                `
            )
        })
    })

const currentUserName = document.getElementById("current-user-name");
fetch("/users", {
    method: "GET",
    headers: {
        "Authorization": `Bearer ${token}`
    }
})
    .then(response => {
        if (response.ok) {
            return response.json();
        }
        else throw Error(response.statusText);
    })
    .then(json => {
        currentUserName.innerHTML = json.username;
    })

const commentPost = document.getElementById("comment-post");
const commentCreateContent = document.getElementById("comment-create-content");

commentPost.addEventListener("submit", e => {
    e.preventDefault();

    const commentData = {
        content: commentCreateContent.value,
        postingId: postId
    }

    fetch("/comments", {
        method: "POST",
        headers: {
            "Authorization": `Bearer ${token}`,
            "Content-type": "Application/json"
        },
        body: JSON.stringify(commentData),
    })
        .then(response => {
            if (response.ok) {
                return response.json();
            }
            else throw Error(response.statusText);
        })
});



//heart + down arrow handler
const heart = document.getElementById("heart");
const down = document.getElementById("down");
heart.addEventListener("click", () => {
    if (heart.style.color == "gray") {
        heart.style.color = "red";
    } else if (heart.style.color == "red") {
        heart.style.color = "gray";
    }
})

down.addEventListener("click", () => {
    if (commentList.style.display == "block") {
        commentList.style.display = "none";
    } else {
        commentList.style.display = "block";
    }
})