window.onload = function(){
    // 🎯 取得畫布與按鈕元素
    let clear = document.getElementById("clear");
    let myDrawer = document.getElementById("myDrawer");

    // 🌐 建立 WebSocket 連線
    let webSocket = new WebSocket("ws://10.0.101.187:8080/BradWeb/mycenter");
    let isConnect = false;

    webSocket.onopen = function(){
        isConnect = true;
        // 宣告自己是教師端
        webSocket.send(JSON.stringify({isTeacher:true}));
    }

    webSocket.onclose = function(){
        isConnect = false;
    }

    webSocket.onerror = function(event){
        console.log("onError:" + event);
    }

    // 🖌️ Canvas 繪圖邏輯
    let ctx = myDrawer.getContext("2d");
    let isDrag = false;

    myDrawer.onmousedown = function(e){
        isDrag = true;
        let x = e.offsetX, y = e.offsetY;
        ctx.beginPath();
        ctx.lineWidth = 4;
        ctx.moveTo(x, y);

        // 可選：通知學生這是新線條起點
        if (isConnect) {
            let data = {
                isClear: false,
                isNewLine: true,
                x: x,
                y: y
            };
            webSocket.send(JSON.stringify(data));
        }
    }

    myDrawer.onmouseup = function(e){
        isDrag = false;
    }

    myDrawer.onmousemove = function(e){
        if (isDrag){
            let x = e.offsetX, y = e.offsetY;
            ctx.lineTo(x, y);
            ctx.stroke();

            // 傳送座標給學生端
            if (isConnect) {
                let data = {
                    isClear: false,
                    isNewLine: false,
                    x: x,
                    y: y
                };
                webSocket.send(JSON.stringify(data));
            }
        }
    }

    // 🧹 清除畫布並通知學生端
    clear.addEventListener("click", function(){
        ctx.clearRect(0, 0, myDrawer.width, myDrawer.height);
        if (isConnect) {
            let data = {
                isClear: true
            };
            webSocket.send(JSON.stringify(data));
        }
    });
}