const tokenMes = localStorage.getItem("token");
const chatOpen = document.getElementById("chat-open");
const chatMain = document.getElementById("chat-main");
const closeChat = document.getElementById("close-chat");
const mes = document.getElementById("mes");
const countMes = document.getElementById("count-mes");

const mesArea = document.getElementById("newmes-area");
const mesForm = document.getElementById("mes-form");
const mesInput = document.getElementById("mes-input");

const editArea = document.getElementById("edit-area");

let count =0;

function confirmMes(mes){
    if (mes.receiverId===1 && !mes.confirm){
        if (mesInput.value!==null){
            fetch(`/messages/admin/confirm/${mes.id}`, {
                method: "PUT",
                headers: {
                    "Authorization": `Bearer ${tokenMes}`,
                    "Content-type": "Application/json",
                }
            }).then(response=>{
                if (response.ok){
                    count=0;
                    countMes.classList.add("d-none");
                    return response.json();
                } else{
                    return response.text().then(text=>{alert(text)})
                }
            }).catch(e=>{
                alert(e.message);
            })

        }    
    }
}
fetch("/messages/admin/chat/2", {
    method: "GET",
    headers: {
        "Authorization": `Bearer ${tokenMes}`,
        "Content-type": "Application/json",
    }
}).then(response=>{
    if (response.ok){
        return response.json();
    } else{
        return response.text().then(text=>{alert(text)})
    }
}).then(json=>{
    json.forEach(message=>{
        displayMes(message);
        editMes(message);
        confirmMes(message);
        if (!message.confirm && message.receiverId===1){
            count++;
        }
    })
    if (count===0) countMes.classList.add("d-none");
    else {
        countMes.innerText=count;
        countMes.classList.remove("d-none")
    }
})

chatOpen.addEventListener("click", e=>{
    e.preventDefault();
    chatOpen.classList.add("d-none");
    chatMain.classList.add("d-lg-flex");
    mes.scrollTop = mes.scrollHeight;
})
closeChat.addEventListener("click", e=>{
    e.preventDefault();
    chatMain.classList.remove("d-lg-flex");
    chatOpen.classList.remove("d-none");
})

function displayMes(message) {
    if (message.receiverId === 1) {
        const divMain = document.createElement("div");
        divMain.className = "chat-messages pb-2 d-flex flex-wrap align-items-center";
        divMain.id = `mes${message.id}`
        divMain.innerHTML = `
        <div class="d-flex align-items-center">
            <div id="mes-content${message.id}" class="p-2 rounded bg-body d-inline border">
               ${message.content}
            </div>
            <a id="mes-edit${message.id}" href="#" class="d-none position-relative rounded p-1 bg-light ms-1" id="vertical">
                <i class="fa-solid fa-trash fa-sm"></i>
            </a>
            <a id="mes-remove${message.id}" href="#" class="d-none position-relative rounded p-1 bg-light ms-1" id="vertical">
                <i class="fa-solid fa-copy fa-sm"></i>
            </a>
        </div>
        <span id="edited${message.id}" class="d-none w-100 text-secondary" style="font-size: small;">
            (edited)
        </span>
        `;
        mes.appendChild(divMain);
    } else {
        const divMain = document.createElement("div");
        divMain.className= "chat-messages pb-2 d-flex flex-wrap justify-content-end align-items-center";
        divMain.id= `mes${message.id}`;
        divMain.innerHTML=
        `<div class="w-100 d-flex justify-content-end align-items-center">
            <a id="mes-edit${message.id}" href="#" class="position-relative rounded p-1 bg-light me-1" id="edit-message">
                <i class="fa-solid fa-pen fa-sm"></i>
            </a>
            <a id="mes-remove${message.id}" href="#" class="position-relative rounded p-1 bg-light me-1" id="edit-message">
                <i class="fa-solid fa-trash fa-sm"></i>
            </a>
            <div id="mes-content${message.id}" class="p-2 rounded text-bg-info">
                ${message.content}
            </div>
        </div>
        <span id="edited${message.id}" class="d-none w-100 text-secondary text-end" style="font-size: small;">
            (edited)
        </span>
        `;
        mes.appendChild(divMain);
        
    }
    if (message.edit){
        const edited = document.getElementById(`edited${message.id}`);
        edited.classList.remove("d-none");
    }
}

const stompClient = new StompJs.Client({
    brokerURL: "ws://127.0.0.1:8080/gs-guide-websocket",
})

stompClient.onConnect = (frame) => {
    stompClient.subscribe("/topic/display-mes", (message) =>{
        const newMes = JSON.parse(message.body);
        displayMes(newMes);
        editMes(newMes);
        mesInput.value = null;
        mes.scrollTop = mes.scrollHeight;
    })

    stompClient.subscribe("/topic/edit", (message)=>{
        const mesEdit = JSON.parse(message.body);
        document.getElementById(`mes-content${mesEdit.id}`).innerText = mesEdit.content;
        const edited = document.getElementById(`edited${mesEdit.id}`);
        edited.classList.remove("d-none");
        editMes(mesEdit);
        mes.scrollTop = mes.scrollHeight;
    })

    stompClient.subscribe("/topic/remove", (messageId)=>{
        const mesRemoveId = JSON.parse(messageId.body);
        const mesRemove = document.getElementById(`mes${mesRemoveId}`);
        mesRemove.innerHTML = 
        `
        <div class="d-none d-flex align-items-center me-5">
            <div class="p-1 rounded text-bg-secondary d-inline border">
                This message has been removed!!!
            </div>
        </div>        
        `;
        mes.appendChild(mesRemove);
        mes.scrollTop = mes.scrollHeight;
    })

    // stompClient.subscribe("/topic/confirm", (message)=>{
    //     mes.scrollTop = mes.scrollHeight;
    // })
}
stompClient.activate();

mesForm.addEventListener("submit", e=>{
    e.preventDefault();
    const dataInput = {
        content: mesInput.value,
        receiverId: 2,
        senderId: 1,
    }
    stompClient.publish({
        destination: "/app/new-mes",
        body: JSON.stringify(dataInput),
    })
})


function editMes(message){
    const updateMes= document.getElementById(`mes-edit${message.id}`);
    const deleteMes = document.getElementById(`mes-remove${message.id}`);
    updateMes.addEventListener("click", e=>{
        e.preventDefault();
        mesArea.classList.add("d-none");
        editArea.classList.remove("d-none");
        editArea.innerHTML =
        `
        <a href="#" id="cancel-edit${message.id}" style="font-size: small;" class="border rounded round d-flex text-center align-items-center text-bg-warning text-decoration-none">Cancel edit</a>
        <form class="input-group w-100" id="edit-form${message.id}">
            <input id="edit-input" type="text" class="form-control fs-6" placeholder="Message..." style="height: 40px;">
            <button class="rounded" type="submit">Send</button>
        </form>
        `;

        const editInput = document.getElementById("edit-input");
        editInput.value = message.content;
        const editForm = document.getElementById(`edit-form${message.id}`);
        editForm.addEventListener("submit", e=>{
            e.preventDefault();
            const dataEdit = {
                "content": editInput.value,
                "senderId": 1,
                "id": message.id,
            }
            stompClient.publish({
                destination: "/app/edit",
                body: JSON.stringify(dataEdit),
            })
            editArea.innerHTML = ``;
            mesArea.classList.remove("d-none");
            editArea.classList.add("d-none");
        })
        
        const cancelEdit = document.getElementById(`cancel-edit${message.id}`);
        cancelEdit.addEventListener("click", e=>{
            e.preventDefault();
            editInput.value=null;
            mesArea.classList.remove("d-none");
            editArea.classList.add("d-none");
        })
    })
    
    deleteMes.addEventListener("click", e=>{
        e.preventDefault();
        
        const deleteConfirm = confirm("Are you sure to delete this message?")
        if (deleteConfirm){
            const dataRemove = {
                senderId: 1,
                id: message.id,
            }
            stompClient.publish({
                destination: "/app/remove",
                body: JSON.stringify(dataRemove),
            })
        }
    })
}


