const token = localStorage.getItem("token");
if (!token) {
    location.href = "/lu/login";
}

//post List
const postBody = document.getElementById("post-body");
// const postTitle = document.getElementById("post-title");
// const postContent = document.getElementById("post-content");
// const writerImage = document.getElementById("writer-image");
// const writerName = document.getElementById("writer-name");
// const postLike = document.getElementById("post-like");
// const postComment = document.getElementById("post-comment");
// const postImage = document.getElementById("post-image");
let writerId;

// fetch("/users",{
//     method: "GET",
//     headers: {
//         "Authorization" : `Bearer ${token}`
//     }
// })
// .then(response => {
//     if (response.ok) {
//         return response.json();
//     } 
//     else throw Error(response.statusText);
// })
// .then(json => {
//     console.log(json);
//     writerImage.src = json.profileImg;
//     writerName.innerHTML = json.writerName;
// })

fetch("http://localhost:8080/posting-view", {
    method: "GET",
    headers: {
        "Authorization": `Bearer ${token}`
    }
})
    .then(response => {
        if (response.ok) {
            return response.json();
        } else throw Error(response.statusText);
    })
    .then(json => {
        json.forEach(post => {
            let postId = post.id;
            let title = post.title;
            let content = post.content;
            let images = post.images;
            let numOfLike = post.numOfLike == null ? 0 : post.numOfLike;
            let numOfComment = post.numOfComment == null ? 0 : post.numOfComment;
            let image_url = images.length === 0 ? "/static/visual/posting/image1.png" : `http://localhost:8080${images[0]}`;
            let writerId = post.writerId;

            fetch(`http://localhost:8080/users/${writerId}`, {
                method: "GET"
            })
                .then(response => {
                    if (response.ok)
                        return response.json();
                    else throw Error(response.statusText);
                })
                .then(user => {
                    const writerName = user.name;
                    const profileImg = user.profileImg;

                    // Insert HTML after user data is retrieved
                    postBody.insertAdjacentHTML("beforeend", `
                        <div class="col-lg-9 col-sm-12">
                            <div class="d-flex align-items-center">
                                <div><img src="http://localhost:8080${profileImg}"
                                        class="rounded-circle border border-secondary profile-image"> </div>
                                <a class="navbar-brand  m-3 fs-5" aria-current="page" href="#">${writerName}</a>
                            </div>
                            <a class="postTitle text-dark fw-bold text-decoration-none fs-4" href="#">${title}</a>
                            <div class="postContent fs-6 limited-text">${content}</div>
                            <div class="postState d-flex fs-6">
                                <p class="post-like me-1">공감</p>
                                <p class="post-likeCount me-2">${numOfLike}</p>
                                <p class="post-comment me-1">댓글</p>
                                <p class="post-commentCount me-2">${numOfComment}</p>
                            </div>
                        </div>
                        <div class="col-lg-3 col-sm-12">
                            <img src="${image_url}" class="img-fluid img-thumbnail post-image" alt="">
                        </div>
                        <div class="w-100 d-flex mt-1" id="placeBtn">
                            <a class=" btn-detail btn btn-primary text-white text-decoration-none" href="/travel/post/${postId}">자세히 보기</a>
                        </div>
                        <hr>
                    `);
                })
                .catch(error => console.error("User fetch error:", error));
        });
    })
    .catch(error => console.error("Post fetch error:", error));

