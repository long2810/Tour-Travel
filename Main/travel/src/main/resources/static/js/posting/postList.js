const token = localStorage.getItem("token");
if (!token) {
    location.href = "/lu/login";
}

const createBtn = document.getElementById('create-btn');
const createForm = document.getElementById("create-form")

createForm.addEventListener('click', e => {
    e.preventDefault();
    const postId = (Number(count) + 1).toString();
    location.href = `/travel/post-create`;
})

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
        console.log(json);

        json.forEach(post => {
            let writerName, profileImg, likeCount, commentCount;
            let postId = post.id;
            let title = post.title;
            let content = post.content;
            let images = post.images;
            let image_url;
            if (images.length == 0) {
                image_url = "/static/image/post3.jpg";
            } else {
                image_url = `http://localhost:8080${images[0]}`;
            }
            writerId = post.writerId;

            fetch(`/users/${writerId}`, {
                method: "GET"
            })
                .then(response => {
                    if (response.ok)
                        return response.json();
                    else throw Error(response.statusText);
                })
                .then(user => {
                    writerName = user.userName;
                    profileImg = user.profileImg;
                })

            fetch(`/like/${postId}`, {
                method: "GET"
            })
                .then(response => {
                    if (response.ok) {
                        likeCount = response.json();
                        return response.json();
                    }  
                    else throw Error(response.statusText);
                })
            
            fetch(`/comment/${postId}`, {
                method : "GET"
            })
            .then(response => {
                if(response.of) {
                    console.log(response.json());
                    commentCount = response.json();
                } 
                else throw Error(response.statusText);
            })
            
            postBody.insertAdjacentHTML("beforeend",
                `
            <div class="col-lg-9 col-sm-12 ">
                    <div class="d-flex align-items-center">
                        <div><img src="http://localhost:8080${profileImg}"
                                class="rounded-circle border border-secondary profile-image"> </div>
                        <a class="navbar-brand  m-3 fs-5" aria-current="page" href="#">${writerName}</a>
                    </div>
                    <a class="postTitle text-dark fw-bold text-decoration-none fs-4  " href="#">${title}</a>
                    <div class="postContent fs-6 limited-text">${content}</div>
                    <div class="postState d-flex fs-6 ">
                        <p class="post-like me-1">공감</p>
                        <p class="post-likeCount me-2">50 </p>
                        <p class="post-comment me-1">댓글</p>
                        <p class="post-commentCount me-2 ">27</p>
                    </div>
                </div>
                <div class="col-lg-3 col-sm-12">
                    <img src="http://localhost:8080${image_url}" class="img-fluid img-thumbnail post-image" alt="">
                </div>
            `
            )
        })
    })

