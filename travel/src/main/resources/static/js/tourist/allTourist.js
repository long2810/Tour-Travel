const city = document.getElementById("city");
const touristArea = document.getElementById("tourist");

const pathSplit = location.pathname.split("/");
const pageNo = pathSplit[pathSplit.length-1];

fetch("/tours/kor/city", {
    method: "GET",
}).then(response=>{
    if (response.ok){
        return response.json();
    } else{
        return response.text().then(text=>{
            alert(text);
        })
    }
}).then(json=>{
    json.forEach(item=>{
        cityDisplay(item);
    })
})

function cityDisplay(item){
    const divDis= document.createElement("div");
    divDis.className = "form-check";
    divDis.innerHTML=
    `
    <input class="form-check-input" type="checkbox" value="city${item.code}" id="city${item.code}">
    <label class="form-check-label" for="city${item.code}">
        ${item.name}
    </label>
    `;
    city.appendChild(divDis);
}

function touristDisplay(tourist){
    let touristImg;
    if (tourist.firstimage=="") touristImg="/static/visual/tourist/Seoul.png";
    else {touristImg=tourist.firstimage;}
    const divTour = document.createElement("div");
    divTour.className= "col-12 col-md-6 p-3";
    divTour.id = `${tourist.contentid}`;
    divTour.innerHTML= 
    `
    <a href="#" class="text-decoration-none text-dark">
        <div class="p-2 bg-warning-subtle rounded">
            <img src="${touristImg}" class="w-100 image img-fluid rounded">
            <div class="text-center p-3">
                <h4 class="fw-bold">${tourist.title}</h4>
                <p class="text-center">${tourist.addr1}</p>
            </div>
        </div>
    </a>
    `;
    touristArea.appendChild(divTour);
}

// Pagination
const pagination = document.getElementById("pagination");
const previous = document.getElementById("previous");
const next = document.getElementById("next");

function totalPage(result){
    pagination.innerHTML=``;
    const totalPage = Math.ceil(result.totalCount/10);
    const pageShow = 9;
    const totalRange = Math.ceil(totalPage/pageShow);
    const currentPage = result.pageNo;
    let currentRange = Math.ceil(currentPage/pageShow);
    if (currentRange===totalRange) next.classList.add("disabled");
    if (currentRange===1) previous.classList.add("disabled");
    next.addEventListener("click", e=>{
        e.preventDefault();
        currentRange++;
        location.href=`/view/tour/${(currentRange-1)*9+1}`
    })
    previous.addEventListener("click", e=>{
        e.preventDefault();
        currentRange--;
        location.href=`/view/tour/${(currentRange-1)*9+10}`
    })
    
    listPage();
    document.getElementById(`page${pageNo}`).classList.add("active");
    function listPage(){
        let endRange = Math.min((currentRange-1)*9+10, totalPage+1);

        for (let i = (currentRange-1)*9+1; i<endRange; i++){
            const li = document.createElement("li");
            li.className="page-item";
            li.id=`page${i}`;
            li.innerHTML=`<a class="page-link" href="/view/tour/${i}">${i}</a>`;
            pagination.appendChild(li);
        }
    }    
}


fetch(`/tours/kor/landmark?pageNo=${pageNo}`,{
        method: "GET",
    }).then(response=>{
        if (response.ok){
            return response.json();
        } else {
            return response.text().then(text=>{alert(text)});
        }
    }).then(json=>{
        json.items.item.forEach(tourist=>{
            touristDisplay(tourist);
        })
        totalPage(json);
    })




