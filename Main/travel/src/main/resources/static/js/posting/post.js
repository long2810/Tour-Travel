const token = localStorage.getItem("token");
if (!token) {
    location.href = "/lu/login";
}

const pathSplit = location.pathname.split("/");
const postId = pathSplit[pathSplit.length - 1];
let writerId;
let userId;

const postTitle = document.getElementById("post-title");
const postContent = document.getElementById("post-content");
const writerImage = document.getElementById("writer-image");
const writerName = document.getElementById("writer-name");
const numOfLike = document.getElementById("post-like");
const numOfComment = document.getElementById("post-comment");
const postBody = document.getElementById("post-body");
const postImageContainer = document.getElementById("post-image-container");


const editPost = document.getElementById("edit-post");
const editBtn = document.getElementById("edit-post-btn")
const formEdit = document.getElementById("form-edit");
const editTitle = document.getElementById("edit-title");
const editContent = document.getElementById("edit-content");
const currentUserName = document.getElementById("current-user-name");

fetch("http://localhost:8080/users", {
    method: "GET",
    headers: {
        "Authorization": `Bearer ${token}`,
        "Content-type": "application/json",
    }
})
    .then(response => {
        if (response.ok) {
            return response.json();
        }
        else throw Error(response.statusText);
    })
    .then(json => {
        userId = json.id;
        currentUserName.innerHTML = json.username;
    })
    .catch(error => console.error("Error:", error));

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
        let imageList = json.images;;
        this.writerId = json.writerId;

        writerImage.src = json.writerAvatar;
        postTitle.innerHTML = json.title;
        writerName.innerHTML = json.writer;

        editTitle.value = json.title;
        editContent.value = json.content;

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

//modal
console.log("userid" + this.userId);
console.log("writerId" + this.writerId);
if (userId == writerId) {
    editBtn.style.display = "inline"

    editBtn.addEventListener("click", function () {
        if (editPost.style.display == "none") {
            editPost.style.display = "flex";
        } else {
            editPost.style.display = "none";
        }
    })

    document.getElementById("close-post").addEventListener("click", e => {
        e.preventDefault();
        editPost.style.display = "none";
    })

    formEdit.addEventListener("submit", e => {
        e.preventDefault();

        const dataEdit = {
            title: editTitle.value,
            content: editContent.value
        };
        fetch(`/posting/${postId}`, {
            method: "PUT",
            headers: {
                "Authorization": `Bearer ${token}`,
                "Content-type": "Application/json"
            },
            body: JSON.stringify(dataEdit)
        })
            .then(response => {
                if (response.ok) { return response.json() }
                else {
                    return response.text().then(text => { alert(text) })
                }
            })
            .then(data => {
                // window.location.reload();
            })
    })
}

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
            const commentId = comment.id;
            let commentWriterImg;
            fetch(`/comments/${commentId}/writerImg`, {
                method: "GET",
                header: {
                    "Authorization": `Bearer ${token}`,
                    "Content-type": "Application/json"
                }
            })
                .then(response => {
                    if (response.ok) {
                        console.log(response.json);
                        return response.json();
                    } else throw Error(reponse.statusText);
                })
                .then(json => {
                    console.log(json);
                    alert(json);
                    commentWriterImg = json;
                });

            // <img src= ${commentWriterImg} class="rounded-circle border border-secondary comment-writer-image"
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

const commentPost = document.getElementById("comment-post");
const commentCreateContent = document.getElementById("comment-create-content");

commentPost.addEventListener("submit", e => {
    // e.preventDefault();

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
        .then(data => {
            window.location.reload();
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