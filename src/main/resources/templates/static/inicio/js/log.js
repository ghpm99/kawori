var stompClient = null;
var subscribeAll = null;
var subscribeLog = null;

function connect() {
    var socket = new SockJS('/log');
    var stompStatus = false;
    stompClient = Stomp.over(socket);




    stompClient.connect({}, function (frame) {
        console.log('Connected' + frame);
        
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
    console.log("disconnected");
}

function sendMessage() {
    stompClient.send("/bot/input", {});
}

function showAllLog(message) {
    var final;
    for (var i = 0; i < message.length; i++) {
        var log = message[i];
        final += "<tr><td>" + new Date(log.hour) + "</td><td>"
                + log.event + "</td><td>"
                + log.guild + "</td><td>"
                + log.user + "</td><td>"
                + log.status + "</td></tr>";
    }
    $("#event").append(final);
    
    if(subscribeAll !== null){
        subscribeAll.unsubscribe();
    }
}

function showLog(message) {
    console.log(message);
    $("#event").append("<tr><td>" + new Date(message.hour) + "</td><td>"
            + message.event + "</td><td>"
            + message.guild + "</td><td>"
            + message.user + "</td><td>"
            + message.status + "</td></tr>");
}

$(function () {
    console.log("start");    
});