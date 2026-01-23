window.onload = function(){
    let start = document.getElementById("start");
    let chatDiv = document.getElementById("chatDiv");
    let mesg = document.getElementById("mesg");
    let send = document.getElementById("send");
    let log = document.getElementById("log");

    let webSocket;
	
	start.style.display = "block";
	chatDiv.style.display = "none";
	
	start.addEventListener("click", function(){
		//connect("ws://localhost:8080/BradWeb/myserver");
		connect("ws://10.0.101.187:8080/BradWeb/myserver");
	});
	
	send.addEventListener("click", function(){
	    let message = {
	        message : mesg.value
	    };
	    webSocket.send(JSON.stringify(message));
	});
	
	function connect(url){
	    webSocket = new WebSocket(url);
	    webSocket.onopen = function(){
	        console.log("onOpen");
			
			start.style.display = "none";
			chatDiv.style.display = "block";
	    }
	    webSocket.onmessage = function(event){
			console.log(event);
		}
	    webSocket.onclose = function(){
	        console.log("onClose");
	    }
	    webSocket.onerror = function(event){
	        console.log("onError:" + event);
	    }
	}
}