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
const postBody = document.getElementById("post-body");
const postImageContainer = document.getElementById("post-image-container");

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
        console.log(json);
        writerImage.src = json.profileImg;
        writerName.innerHTML = json.username;
    })

fetch(`http://localhost:8080/posting-view/${postId}`, {
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
        let title = json.title;
        let content = json.content;
        let imageList = json.images;

        postTitle.innerHTML = title;
        imageList.forEach(url => {
            postImageContainer.insertAdjacentHTML("beforeend", `
                <div class="col-lg-4 col-sm-6 col-xs-12 mb-4">
                    <img src="http://localhost:8080${url}" class="w-100" style="height: 230px;">
                </div>
            `)
        });

        postContent.innerHTML = `<p class="fs-5">${content}</p>`;
    })