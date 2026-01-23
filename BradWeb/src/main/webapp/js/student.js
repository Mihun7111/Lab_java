window.onload = function(){
	let myDrawer = document.getElementById("myDrawer");
	let webSocket = new WebSocket("ws://10.0.101.187:8080/BradWeb/mycenter");
	let isConnect = false;
	
	webSocket.onopen = function(){
		isConnect = true;
	}
	webSocket.onmessage = function(event){
	    if (isConnect){
	        let mesgObj = JSON.parse(event.data); // 將收到的 JSON 字串轉成物件
	        if (mesgObj.isClear){
	            clear(); // 如果是清除指令，執行清除畫布
	        }else{
	            if (mesgObj.isNewLine){
	                newLine(mesgObj.x, mesgObj.y); // 新線條起點
	            }else{
	                drawLine(mesgObj.x, mesgObj.y); // 繪製線段
	            }
	        }
	    }
	}
	// WebSocket 關閉時的處理
	webSocket.onclose = function(){
	    isConnect = false;
	}

	// WebSocket 錯誤處理
	webSocket.onerror = function(event){
	    console.log("onError:" + event);
	}

	//-------------------------------
	// 🎨 Canvas 繪圖相關函式
	let ctx = myDrawer.getContext("2d");

	// 清除整個畫布
	function clear(){
	    ctx.clearRect(0, 0, myDrawer.width, myDrawer.height);
	}

	// 開始新的線條（起點）
	function newLine(x, y){
	    ctx.beginPath();
	    ctx.lineWidth = 4;
	    ctx.moveTo(x, y);
	}

	// 繪製線段到指定座標
	function drawLine(x, y){
	    ctx.lineTo(x, y);
	    ctx.stroke();
	}
}