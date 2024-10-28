const navbar = document.getElementById("navbar");
// fetch("/token/oauth", {
//     method: "GET",
// }).then(response=>{
//     if (response.ok) {
//         localStorage.setItem("token", response.json().token);
//     } else if (response.status===403){
//         localStorage.setItem("token", null);
//     }
// })
const tokenNavbar = localStorage.getItem("token");
if (tokenNavbar) {login()}
else {nonLogin()}

function nonLogin(){
    navbar.innerHTML =
`
                <div class="dropdown navbar-menu">
                    <button id="btn-menu" class="navbar-toggler" type="button" data-bs-toggle="dropdown"
                        data-bs-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false"
                        aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <ul id="list-menu" class="dropdown-menu navbar-menu-list" aria-labelledby="dropdownMenuButton" style="width: 150px;">
                        <li><a class="dropdown-item" href="#"><i class="fa-solid fa-globe"></i> 여행코스</a></li>
                        <li><a class="dropdown-item" href="#"><i class="fa-solid fa-earth-americas"></i> 관광지</a></li>
                        <li><a class="dropdown-item" href="#"><i class="fa-solid fa-blog"></i> 블로그</a></li>
                        <li><a class="dropdown-item" href="#"><i class="fa-solid fa-map"></i> 지도</a></li>
                    </ul>
                </div>

                <a class="navbar-brand fs-2" href="#">Seoul Travel</a>
                <div class="collapse navbar-collapse mx-4 " id="navbarTogglerDemo03">
                    <div class="d-flex w-100 justify-content-between align-items-center">
                        <ul class="navbar-nav  me-auto mb-2 mb-lg-0 ">
                            <li class="nav-item mx-3">
                                <a class="navbar-brand active" aria-current="page" href="#">여행 코스</a>
                            </li>
                            <li class="nav-item mx-3">
                                <a class="navbar-brand" href="#">관광지</a>
                            </li>
                            <li class="nav-item mx-3">
                                <a class="navbar-brand" href="#">블로그</a>
                            </li>
                        </ul>
                        <div class="d-flex align-items-center justify-content-center">
                            <!-- <i class="fa-solid fa-location-dot fa-2x " style="color: #d21e1e;"></i> -->
                            <a href="/travel/map" target="_blank"><img src="/static/visual/home/map.png" class="ms-4" style="height: 40px; width: 40px;"></a>
                        </div>
                    </div>
                </div>
                <div class="d-none d-sm-flex justify-content-end">
                    <a href="/travel/login" class="btn btn-light me-2">로그인</a>
                    <a href="/travel/signup" class="btn btn-light">회원가입</a>
                </div>
`;
}

function login(){
    navbar.innerHTML =
`
                <div class="dropdown navbar-menu">
                    <button id="btn-menu" class="navbar-toggler" type="button" data-bs-toggle="dropdown"
                        data-bs-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false"
                        aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <ul id="list-menu" class="dropdown-menu navbar-menu-list" aria-labelledby="dropdownMenuButton" style="width: 150px;">
                        <li><a class="dropdown-item" href="#"><i class="fa-solid fa-globe"></i> 여행코스</a></li>
                        <li><a class="dropdown-item" href="#"><i class="fa-solid fa-earth-americas"></i> 관광지</a></li>
                        <li><a class="dropdown-item" href="#"><i class="fa-solid fa-blog"></i> 블로그</a></li>
                        <li><a class="dropdown-item" href="#"><i class="fa-solid fa-map"></i> 지도</a></li>
                    </ul>
                </div>

                <a class="navbar-brand fs-2" href="#">Seoul Travel</a>
                <div class="collapse navbar-collapse mx-4 " id="navbarTogglerDemo03">
                    <div class="d-flex w-100 justify-content-between align-items-center">
                        <ul class="navbar-nav  me-auto mb-2 mb-lg-0 ">
                            <li class="nav-item mx-3">
                                <a class="navbar-brand active" aria-current="page" href="#">여행 코스</a>
                            </li>
                            <li class="nav-item mx-3">
                                <a class="navbar-brand" href="#">관광지</a>
                            </li>
                            <li class="nav-item mx-3">
                                <a class="navbar-brand" href="#">블로그</a>
                            </li>
                        </ul>
                        <div class="d-flex align-items-center justify-content-center">
                            <!-- <i class="fa-solid fa-location-dot fa-2x " style="color: #d21e1e;"></i> -->
                            <a href="/travel/map" target="_blank"><img src="/static/visual/home/map.png" class="ms-4" style="height: 40px; width: 40px;"></a>
                        </div>
                    </div>
                </div>
                <div class="dropdown">
                    <div id="profileButton" type="button" data-toggle="dropdown" aria-haspopup="true"
                        aria-expanded="false">
                        <img id="avatar" src="/static/visual/user.png"
                            class="rounded-circle border border-secondary" style="height: 40px; width: 40px;">
                    </div>
                    <div class="dropdown-menu position-absolute end-0 mt-3" id="profile-dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <div class="d-flex align-items-center mt-1">
                            <i class="fa-solid fa-circle-user fa-lg mx-2"></i>
                            <p id="name" class=" mb-0 fs-6 text-center">Tran Vu Long</p>
                        </div>
                        <hr>
                        <div class="d-flex align-items-center mb-3">
                            <i class="fa-solid fa-user ms-2"></i>
                            <a class="dropdown-item" href="/travel/profile">프로필</a>
                        </div>
                        <div id="logout" class="d-flex align-items-center ">
                            <i class="fa-solid fa-right-from-bracket ms-2"></i>
                            <a class="dropdown-item" href="#">로그아웃</a>
                        </div>
                    </div>
                </div>
`;

document.getElementById("logout").addEventListener("click", e=>{
    e.preventDefault();
    localStorage.removeItem("token");
    location.href = "/travel/login";
})

const profileButton = document.getElementById('profileButton');
const dropdownMenu = document.getElementById('profile-dropdown-menu');
profileButton.addEventListener('click', function () {
    if (dropdownMenu.style.display == 'block') {
        dropdownMenu.style.display = 'none';
    } else {
        dropdownMenu.style.display = 'block';
    }
})

window.addEventListener('click', function (e) {
    if (!profileButton.contains(e.target) && !dropdownMenu.contains(e.target)) {
        dropdownMenu.style.display = 'none';
    }
});

fetch("/users", {
    headers: {
        "Authorization": `Bearer ${tokenNavbar}`,
        "Content-type": "Application/json",
    }
}).then(response=>{
    if(response.ok){return response.json()}
    else{
        return response.text().then(text=>{alert(text)})
    }
}).then(user=>{
    document.getElementById("avatar").src=user.profileImg;
    document.getElementById("name").innerText=user.name;
})

}


