const tokenCall = localStorage.getItem("token");
if (tokenCall){
    callSystem();
}
function callSystem(){
    const videoSystem = document.getElementById("video-system");
    videoSystem.innerHTML=
    `
        <div id="videoBox" class="d-none ms-5 me-5 mt-3 border border-4 rounded bg-light">
            <div class="d-flex justify-content-center ps-3 pe-3 text-white align-items-center" style="background-color: darkblue;">
                <h3>통화중</h3>
            </div>
            <div class="p-3 d-flex flex-wrap">
                <div class="d-flex w-100 justify-content-center p-3 gap-3">
                    <div id="remote-display" class="d-none col-12 col-lg-6 border bg-white rounded" style="height: 400px;">
                        <video id="remote-video" style="transform: scaleX(-1);" class="h-100 w-100 rounded" autoplay playsinline></video>
                    </div>
                    <div id="local-display" class="col-12 col-lg-9 border bg-white rounded" style="height: 400px;">
                        <video id="local-video" style="transform: scaleX(-1);" class="h-100 w-100 rounded" autoplay playsinline></video>
                    </div>
                </div>
                <div class="w-100 d-flex justify-content-center">
                    <button class="btn btn-outline-primary me-3" id="muteVideo" ><i class="fa-solid fa-video"></i></button>
                    <button class="btn btn-outline-primary me-3" id="muteAudio"><i class="fa-solid fa-microphone"></i></button>
                    <button class="btn btn-outline-danger" id="hangup"><i class="fa-solid fa-phone-flip"></i></button>
                </div>
            </div>
        </div>
        <div id="call-bell" class="d-none mt-5 d-flex justify-content-center">
            <div class="col-9 col-md-6 col-lg-4 d-flex border rounded border-4 flex-wrap shadow-md bg-white">
                <div class="d-flex justify-content-center text-white w-100" style="background-color: darkblue;">
                    <h4>전화</h4>
                </div>
                <div class="d-flex justify-content-center flex-wrap">
                    <div class="w-100 d-flex justify-content-center p-5">
                        <img id="caller-img" src="/static/visual/user.png" class="img-fluid" style="height: 150px; width: 150px;">
                    </div>
                    <div id="caller" class="text-center fs-4 fw-bold w-100 mb-3 ps-5 pe-5">
                        
                    </div>
                    <div class="mb-5">
                        <button id="accept" class="btn btn-success me-3"><i class="fa-solid fa-phone-flip"></i> 통화</button>
                        <button id="reject" class="btn btn-danger"><i class="fa-solid fa-phone-slash"></i> 거절</button>
                    </div>
                </div>
            </div>
        </div> 
    `;

    const videoBox = document.getElementById("videoBox");
    const callBell = document.getElementById("call-bell");

    // const voiceCall = document.getElementById("voiceCall");
    const videoCall = document.getElementById("call-icon");

    let userLogin;
    let callFix;

    fetch("/users",{
    headers:{
        "Authorization": `Bearer ${tokenCall}`,
    }
    }).then(response=>{
    if (response.ok) return response.json();
    }).then(user=>{
        userLogin = user;
    })

    const localVideo = document.getElementById("local-video");
    const remoteVideo = document.getElementById("remote-video");
    const remoteDis = document.getElementById("remote-display");
    const localDis = document.getElementById("local-display");
    const hangup = document.getElementById("hangup");
    let iceCandidates = [];


    const stompClientCall = new StompJs.Client({
    brokerURL: "ws://127.0.0.1:8080/gs-guide-websocket",
    //         brokerURL: "ws://13.124.202.180/gs-guide-websocket",
    });
    stompClientCall.activate();

    let pcCaller;
    let pcCallee;
    let pc;
    let localStream;
    let offerResponse;
    let onCall;
    let setupCall;
    

    const configuration = {
    iceServers: [
            { "urls": 
                ["stun:stun.l.google.com:19302", 
                "stun:stun1.l.google.com:19302", 
                "stun:stun2.l.google.com:19302"] 
            }
    ]
    };

    videoCall.addEventListener("click", e=>{
        e.preventDefault;
        fetch("/call/check-available/1",{
            headers:{
                "Authorization": `Bearer ${tokenCall}`,
            }
        }).then(response=>{
            if (response.ok){return response.json()}
            else (response.text().then(text=>{alert(text)}))
        }).then(available=>{
            if (available){
                setupCall = {video:true, audio: true};
                startCall(setupCall, {calleeId: 1, callerId: userLogin.id}, localVideo, configuration, stompClientCall);
                hangup.addEventListener("click", e=>{
                    e.preventDefault();
                    endCall(callFix);
                })
            }
            else {alert("죄송합니다! 다른 연결중입니다. 다시 연락드리겠습니다!");}
        })
    })

    stompClientCall.onConnect=(frame)=>{
        
        stompClientCall.subscribe("/topic/signaling", (callResponse)=>{
            const calling = JSON.parse(callResponse.body);
            callFix = calling;
            
            replyCall(calling, {video:true, audio: true}, callBell, 
                videoBox, localVideo, stompClientCall, configuration, offerResponse, pcCaller);
            // console.log(pcCaller);
        })

        stompClientCall.subscribe('/topic/offer', offer=>{
            offerResponse= JSON.parse(offer.body);
        });
        stompClientCall.subscribe('/topic/answer', message=>{
            handleAnswer(message, pcCaller);
        } );
        stompClientCall.subscribe('/topic/candidate', response=>{
            const candidate = JSON.parse(response.body);
            iceCandidates.push(candidate);
        });

        stompClientCall.subscribe("/topic/on-call", (callResponse)=>{
            const calling = JSON.parse(callResponse.body);
            onCall = calling;
            callFix = calling;
        })
        stompClientCall.subscribe("/topic/reject", (callResponse)=>{
            const calling = JSON.parse(callResponse.body);
            if (userLogin.id===calling.callerId && calling.status==="Reject"){
                videoBox.classList.add("d-none");
                alert(`대답 못 해서 죄송드립니다! 다시 연락드리겠습니다`);
            }
        })
        stompClientCall.subscribe("/topic/miss-call", (callResponse)=>{
            const calling = JSON.parse(callResponse.body);
            if (userLogin.id===calling.callerId && calling.status==="MissCall"){
                videoBox.classList.add("d-none");
                alert(`대답 못 해서 죄송드립니다! 다시 연학드리겠습니다`);
            }
            callBell.classList.add("d-none");
            
        })
        // stompClientCall.subscribe("/topic/hangup", (callResponse)=>{
        //     const calling = JSON.parse(callResponse.body);
            
        //     if (userLogin.id===calling.callerId || userLogin.id===calling.calleeId){
        //         document.getElementById("videoBox").classList.add("d-none");
        //         // alert(`저의 서비스 사랑해주셔서 감사합니다!`);
        //     }
        //     fetch(`/call/${calling.id}`, {
        //         method: "DELETE",
        //         headers: {
        //             "Authorization": `Bearer ${tokenCall}`,
        //         }
        //     }).then(response=>{
        //         if (response.ok) {callFix=null}
        //     })
        // })
    }
    
    let isVideo = true;
    document.getElementById("muteVideo").addEventListener("click", e=>{
    e.preventDefault();
    isVideo = !isVideo
    localStream.getVideoTracks()[0].enabled = isVideo
    if (isVideo===true){
        document.getElementById("muteVideo").innerHTML=`<i class="fa-solid fa-video"></i>`;
    } else {document.getElementById("muteVideo").innerHTML=`<i class="fa-solid fa-video-slash"></i>`;}
    })

    let isAudio = true;
    document.getElementById("muteAudio").addEventListener("click", e=>{
        e.preventDefault();
        isAudio = !isAudio;
        localStream.getAudioTracks()[0].enabled = isAudio;
        if (isAudio===true){
        document.getElementById("muteAudio").innerHTML=`<i class="fa-solid fa-microphone"></i>`;
        } else {document.getElementById("muteAudio").innerHTML=
        `<i class="fa-solid fa-microphone-slash"></i>`;}
    })

    function startCall(setup, dataCall, localVideo, configuration, stompClientCall){
        videoBox.classList.remove("d-none");
        navigator.mediaDevices.getUserMedia(setup).then(stream => {
            localVideo.srcObject = stream;
            localStream = stream;
            stream.getAudioTracks()[0].enabled= true;
        
            pcCaller = new RTCPeerConnection(configuration);
            pcCaller.addStream(localStream);
        
            pcCaller.onaddstream = (e) => {
              remoteVideo.srcObject = e.stream
            }
        
            stompClientCall.publish({
                destination: "/app/signaling",
                body: JSON.stringify(dataCall)
            });

            pcCaller.createOffer().then(offer=>{
                return pcCaller.setLocalDescription(offer);
            }).then(() => {
            stompClientCall.publish({
                destination: "/app/offer",
                body: JSON.stringify(pcCaller.localDescription),
            });
            })
        
            pcCaller.onicecandidate = event => {
              if (event.candidate) {
                stompClientCall.publish({
                    destination: "/app/candidate",
                    body: JSON.stringify(event.candidate)
                });
              }
            };
            
        })
    }
    
    function replyCall(call, setup, callBell, videoBox,
        localVideo, stompClientCall, configuration, offerResponse, pcCaller)
        {
        if (call.calleeId === userLogin.id && call.status==="Connecting"){
            callBell.classList.remove("d-none");
            const caller = document.getElementById("caller");
            const callerImg=document.getElementById("caller-img");
            if (call.calleeId===1){
                caller.innerText= `${call.callerName}님의 전화가 왔습니다.` ;
            } else {
                caller.innerText= `안녕하세요, 저의 서울 여행입니다.` ;
            }
            callerImg.src=call.callerImg;
    
            document.getElementById("accept").addEventListener("click", e=>{
                e.preventDefault();
                callBell.classList.add("d-none");
                videoBox.classList.remove("d-none");
                document.getElementById("remote-display").classList.remove("d-none");
                document.getElementById("local-display").className = "d-none d-lg-flex col-6 border bg-white rounded";
                
                stompClientCall.publish({
                    destination: `/app/on-call`,
                    body: JSON.stringify({id: call.id}),
                })
    
                navigator.mediaDevices.getUserMedia(setup)
                .then(stream => {
                  localVideo.srcObject = stream;
                  localStream = stream;
                  stream.getAudioTracks()[0].enabled= true;
    
                  pcCallee = new RTCPeerConnection(configuration);
                  pcCallee.addStream(localStream);
      
                  pcCallee.onaddstream = (e) => {
                    remoteVideo.srcObject = e.stream
                  }
                pcCallee.setRemoteDescription(new RTCSessionDescription(offerResponse))
                  .then(() => {
                      return pcCallee.createAnswer();
                  })
                  .then(answer => {
                      return pcCallee.setLocalDescription(answer);
                  })
                  .then(() => {
                      stompClientCall.publish({
                          destination: "/app/answer",
                          body: JSON.stringify(pcCallee.localDescription)
                      });
                  }).then(()=>{
                    iceCandidates.forEach(candidate => {
                      pcCallee.addIceCandidate(new RTCIceCandidate(candidate))
                          .catch(error => console.error('Error adding ICE candidate', error));
                    });
                    iceCandidates = [];
                  })
                  .catch(error => console.error('Error handling offer', error));
      
                  pcCallee.onicecandidate = (event) => {
                    if (event.candidate) {
                      stompClientCall.publish({
                          destination: "/app/candidate",
                          body: JSON.stringify(event.candidate)
                      });
                  }}
                })
            })
            
            document.getElementById("reject").addEventListener("click", e=>{
                e.preventDefault();
                callBell.classList.add("d-none");
                stompClientCall.publish({
                    destination: "/app/reject",
                    body: JSON.stringify({id: call.id})
                })
            })
            
          }
    }
    
    function handleAnswer(message, pcCaller) {
        const answer = JSON.parse(message.body);
        if (pcCaller){
            pcCaller.setRemoteDescription(new RTCSessionDescription(answer))
        .then(()=>{
          iceCandidates.forEach(candidate => {
            pcCaller.addIceCandidate(new RTCIceCandidate(candidate))
                .catch(error => console.error('Error adding ICE candidate', error));
          });
          iceCandidates = [];
          document.getElementById("remote-display").classList.remove("d-none");
            document.getElementById("local-display").className = "d-none d-lg-flex col-6 border bg-white rounded";
                
        })
            .catch(error => console.error('Error handling answer', error));
        }
        
    }
    
    function endCall(call){
        localStream.getTracks().forEach(track => track.stop());
        if (pcCallee) pcCallee.close();
        pcCallee = null;
        if (pcCaller) pcCaller.close();
        pcCaller = null;
        document.getElementById("videoBox").classList.add("d-none");
        if (onCall && call.status==="OnCall") {
            fetch(`/call/${call.id}`, {
                method: "DELETE",
                headers: {
                    "Authorization": `Bearer ${tokenCall}`,
                }
            }).then(()=>{callFix=null})
            alert(`저의 서비스 사랑해주셔서 감사합니다!`);
            // stompClientCall.publish({
            //     destination: "/app/hangup",
            //     body: JSON.stringify({id: call.id}),
            // })
        } else if (call.status==="Connecting") {
            // stompClientCall.publish({
            //     destination: "/app/miss-call",
            //     body: JSON.stringify({id: call.id}),
            // })
            fetch(`/call/${call.id}`, {
                method: "PUT",
                headers: {
                    "Authorization": `Bearer ${tokenCall}`,
                }
            }).then(response=>{
                if(response.ok){return response.json()}
            }).then(call=>{
                callFix=call;
                videoBox.classList.add("d-none");
                alert(`대답 못 해서 죄송드립니다! 다시 연학드리겠습니다`);
            })
        }
    }
    window.addEventListener('beforeunload', () => {
        if (onCall && callFix.status==="OnCall") {
            fetch(`/call/${callFix.id}`, {
                method: "DELETE",
                headers: {
                    "Authorization": `Bearer ${tokenCall}`,
                }
            })
        } else if (callFix.status==="Connecting") {
            fetch(`/call/${callFix.id}`, {
                method: "PUT",
                headers: {
                    "Authorization": `Bearer ${tokenCall}`,
                }
            })
        }
    });
    hangup.addEventListener("click", e=>{
        e.preventDefault();
        videoBox.classList.add("d-none");
        endCall(callFix);
        // location.reload();
    })
}






