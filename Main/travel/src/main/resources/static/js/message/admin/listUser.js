const tokenUser = localStorage.getItem("token");


const listUsers = document.getElementById("list-users");
function allUsers(user){
    const oneuser = document.createElement("div");
    oneuser.className = "mb-2";
    oneuser.innerHTML=
    `
    <a href="/admin/message/${user.id}" id="user${user.id}" class="link-image d-flex p-2 border rounded" aria-haspopup="true">
        <div class="d-flex col-6">
            <div class="col-4 text-center me-1" id="profile">
                <img src="${user.profileImg}" id="base-image" class="img-fluid w-50" style="border-radius: 50%;" >
            </div>
            <div class="d-none d-lg-flex col-7">
                ${user.name}
            </div>
        </div>
        <div class="d-flex col-6 justify-content-end">
            <div id='miss-call-icon${user.id}' class="d-none position-relative me-3">
                <img src="/static/visual/calling/miss-call.png" class="img-fluid" style="height: 24px; width: 24px;">
            </div>
            <div class="position-relative me-1">
                <i class="fa-solid fa-comments"></i>
                <span id="mes-count${user.id}" class="d-none position-absolute" style="top: -1px; right: -7px; background: red; color: white; border-radius: 50%; padding:1px 6px; font-size: 10px;">3</span>
            </div>
        </div>
    </a>
    `
    listUsers.appendChild(oneuser);

    fetch(`/messages/admin/count-mes/${user.id}`, {
        method:"GET",
        headers: {
            "Authorization": `Bearer ${tokenUser}`,
            "Content-type": "application/json",
        }
    }).then(response=>{
        if (response.ok){
            return response.json();
        } else {
            return response.text().then(text=>{
                alert(text);
            })
        }
    })
    .then(numOfMes=>{
        const mesCountDis = document.getElementById(`mes-count${user.id}`);
        mesCountDis.innerText= numOfMes;
        if (numOfMes>0) mesCountDis.classList.remove("d-none");
    }).catch(e=>{
        alert(e.message);
    })

    fetch(`/call/miss-call/admin/${user.id}`, {
        method: "GET",
        headers: {
            "Content-type":"application/json",
            "Authorization": `Bearer ${tokenUser}`,
        }
    }).then(response=>{
        if (response.ok){return response.json();}
        else {response.text().then(text=>{alert(text)})}
    }).then(call=>{
        if (call.id!==null){
            document.getElementById(`miss-call-icon${user.id}`).classList.remove("d-none");
            document.getElementById(`user${user.id}`).addEventListener("click", e=>{
                e.preventDefault();
                fetch(`/call/${call.id}`, {
                    method: "DELETE",
                    headers: {
                        "Authorization": `Bearer ${tokenUser}`,
                    }
                }).then(response=>{
                    if (response.ok){
                        location.href=`/admin/message/${user.id}`;
                    }
                })
                
            })
        }
    })
}

fetch("/users/all", {
    method: "GET",
    headers: {
        "Content-type":"application/json",
        "Authorization": `Bearer ${tokenUser}`,
    }
}).then(response=>{
    if (response.ok){return response.json()}
    else{ return response.text().then(text=> alert(text))}
}).then(json=>{
    json.forEach(user=>{
        allUsers(user);
    })
})