
const pathSplit = location.pathname.split("/");
const postId = pathSplit[pathSplit.length - 1];

alert(postId);

const imageForm1 = document.getElementById("ImageForm1");
const imageForm2 = document.getElementById("ImageForm2");
const imageForm3 = document.getElementById("ImageForm3");
const completeForm = document.getElementById('CompleteForm');

const image1 = document.getElementById('postImage_1');
const image2 = document.getElementById('postImage_2');
const image3 = document.getElementById('postImage_3');


function fetchImage(image) {
    const formData = new FormData();
        if (image.files.length > 0) {
            formData.append('image', image.files[0]);
            fetch(`/post-images/${postId}`, {
                method: 'POST',
                body: formData
            }).then(response => {
                if (response.ok) {
                    return response.json();
                }
                else {
                    return response.text().then(text => alert(text));
                }
            })
        } else {
            console.log("Didn't input image file");
        }
}

imageForm1.addEventListener('submit', e => {
    e.preventDefault();
    fetchImage(image1);
})
imageForm2.addEventListener('submit', e => {
    e.preventDefault();
    fetchImage(image2);
})
imageForm3.addEventListener('submit', e => {
    e.preventDefault();
    fetchImage(image3);
})

completeForm.addEventListener('submit', e => {
    e.preventDefault();
    
    fetch (`http://localhost:8080/posting-view/${postId}`, e => {
        method : "GET"
    })
    .then(response => {
        if (response.ok) {
            location.href = `http://localhost:8080/travel/post/${postId}`;
            return response.json();
        } else {
            return response.text().then(text => alert(text));
        }
    })
})