function detail(eventId) {

    $("#events").hide();

    $.ajax( {
            headers: { 
                'Accept': 'application/json',
                'Content-Type': 'application/json' 
            }, 
            type: "GET",
            dataType: 'json',
            url: "http://localhost:8080/eventmanager/api/event/detail/" + eventId,
            success: function(data) { 

            	$("#eventDetail").show();
                var event = JSON.parse(JSON.stringify(data));
                $("#eventDetail").empty();
                
                var ev = '<div id="wrapper">';
                ev = ev +	'<div id="main">' +
		                		'<div class="inner">' + 
		                            '<h1>' + event.title + '</h1>' +
		                            '<span class="image main"><img src="images/pic01.jpg" alt="" height="400px" /></span>' +
		                            '<p>' + event.locality + '</p>' + 
		                            '<p>' + event.description + '</p>' + 
		                            '<p>' + event.startDate + '</p>' + 
		                            '<p>' + event.endDate + '</p>' + 
		                        '</div>' + 
		                    '</div>' +
		                 '</div>';

                $("#eventDetail").append(ev);
                
            },
            error: function(x, m) {
                console.log(x);
                console.log(m);
                alert('error!');
            }
        });
}