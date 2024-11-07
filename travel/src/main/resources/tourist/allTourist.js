const city = document.getElementById("city");
const tourist = document.getElementById("tourist");
let touristImg;

fetch("https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=cxtbTLbpx9N%2FaG8%2BHZPEINXBbq6cMJ%2B4Xkhr4fVQIwzPX8YDEX%2F0O1oEfDecBvKbRRnUTcX9bvKf5uLbPj3m%2FA%3D%3D&_type=json&MobileOS=ETC&MobileApp=AppTest&numOfRows=17", {
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
    json.response.body.items.forEach(item=>{
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
    if (tourist.firstimage=="") touristImg="/static/"
    const divTour = document.createElement("div");
    divTour.className= "col-12 col-md-6 p-3";
    divTour.id = `${tourist.contentid}`;
    divTour.innerHTML= 
    `
    <a href="#" class="text-decoration-none text-dark">
        <div class="p-2 bg-warning-subtle rounded">
            <img src="Seoul.jpg" class="w-100 image img-fluid rounded">
            <div class="text-center p-3">
                <h2 class="fw-bold">문화역 서울 284</h2>
                <p class="text-center">서울특별시 중구 통일로 1</p>
            </div>
        </div>
    </a>
    `;
    tourist.appendChild(divTour);
}

