<!DOCTYPE html>
<html>
<head>
<title>Testing websockets</title>
</head>
<body>
  <div>
  	<input type="text" id="sms"/>
    <input type="submit" value="Start" onclick="start()" />
  </div>
  <div id="messages"></div>
  <script type="text/javascript">
    var webSocket = new WebSocket('ws://localhost:8080/java/websocket/333');
  /*   if ('WebSocket' in window)    
    	webSocket = new WebSocket("ws://localhost:8080/java/websocket/333");    
    else if ('MozWebSocket' in window)    
    	webSocket = new MozWebSocket("ws://localhost:8080/java/websocket");    
    else    
        alert("not support");    */ 
 
    webSocket.onerror = function(event) {
      onError(event)
    };
 
    webSocket.onopen = function(event) {
      onOpen(event)
    };
 
    webSocket.onmessage = function(event) {
      onMessage(event)
    };
 
    function onMessage(event) {
      document.getElementById('messages').innerHTML
        += '<br />' + event.data;
    }
 
    function onOpen(event) {
      document.getElementById('messages').innerHTML
        = 'Connection established';
    }
 
    function onError(event) {
      alert(event.data);
    }
 
    function start() {
      webSocket.send(document.getElementById("sms").value);
      return false;
    }
  </script>
</body>
</html>