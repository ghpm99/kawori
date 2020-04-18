var stompClient = null;
var subscribeAll = null;
var subscribeLog = null;

function connect() {
    var socket = new SockJS('/log');
   
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function (frame) {
        
        subscribeAll = stompClient.subscribe('/bot/all', function (log) {
            showAllLog(JSON.parse(log.body));
        });

        subscribeLog = stompClient.subscribe('/bot/log', function (log) {
            showLog(JSON.parse(log.body));
        });

        sendMessage();
    });

}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
}

function sendMessage() {
    stompClient.send("/bot/input", {});
}

function showAllLog(message) {
    var final;
    for (var i = 0; i < message.length; i++) {
        var log = message[i];
        final += "<tr class=\"row100 body\"><td class=\"cell100 column1\">" + new Date(log.hour).toLocaleString() 
                + "</td><td class=\"cell100 column2\">" + log.event 
                + "</td><td class=\"cell100 column3\">"+ log.guild 
                + "</td><td class=\"cell100 column4\">"+ log.user 
                + "</td><td class=\"cell100 column5\">"+ log.status + "</td></tr>";
    }
    $("#event").append(final);
    
    if(subscribeAll !== null){
        subscribeAll.unsubscribe();
    }
}

function showLog(message) {
    $("#event").append("<tr class=\"row100 body\"><td class=\"cell100 column1\">" + new Date(message.hour).toLocaleString() 
                + "</td><td class=\"cell100 column2\">" + message.event 
                + "</td><td class=\"cell100 column3\">"+ message.guild 
                + "</td><td class=\"cell100 column4\">"+ message.user 
                + "</td><td class=\"cell100 column5\">"+ message.status + "</td></tr>");
}
