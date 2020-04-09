var stompClient = null;

function connect() {
    var socket = new SockJS('/log');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected' + frame);
        stompClient.subscribe('/bot/output', function (log) {
            console.log(log.body);
            showLog(JSON.parse(log.body));
        });
    });

}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    console.log("disconnected");
}

function sendMessage() {
    stompClient.send("/bot/input", {}, JSON.stringify({'name': $("").val()}));
}

function showLog(message) {
    var final;
    for (var i = 0; i < message.length; i++) {
        var log = message[i];
        final += "<tr><td>" + log.hour + "</td><td>"
                + log.event + "</td><td>"
                + log.guild + "</td><td>"
                + log.user + "</td><td>"
                + log.status +"</td></tr>";
    }
    $("#event").append(final);
}

$(function () {
    console.log("start");
    connect();
    $("#connect").click(function () {
        sendMessage();
    });
});