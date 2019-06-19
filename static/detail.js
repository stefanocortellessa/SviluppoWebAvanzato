function eventDetail(eventId) {

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

            	$("#Detail").show();
                var event = JSON.parse(JSON.stringify(data));
                $("#Detail").empty();
                
                var ev = '<div id="wrapper">';
                ev = ev +	'<div id="main">' +
		                		'<div class="inner">' + 
		                            '<h1>' + event.title + '</h1>' +
		                            '<span class="image main"><img src="images/event.jpg" alt="" height="400px" /></span>' +
		                            '<p>' + event.locality + '</p>' + 
		                            '<p>' + event.description + '</p>' + 
		                            '<p>' + event.startDate + '</p>' + 
		                            '<p>' + event.endDate + '</p>' + 
		                        '</div>' + 
		                    '</div>' +
		                 '</div>';

                $("#Detail").append(ev);
                
            },
            error: function(x, m) {
                console.log(x);
                console.log(m);
                alert('error!');
            }
        });
}

function attractionDetail(attractionId) {

    console.log('attractionDetail');

    $("#attractions").hide();

    $.ajax( {
            headers: { 
                'Accept': 'application/json',
                'Content-Type': 'application/json' 
            }, 
            type: "GET",
            dataType: 'json',
            url: "http://localhost:8080/attractionmanager/api/attraction/detail/" + attractionId,
            success: function(data) { 

                $("#Detail").show();
                var attraction = JSON.parse(JSON.stringify(data));
                $("#Detail").empty();
                
                var att = '<div id="wrapper">';
                att = att +   '<div id="main">' +
                                '<div class="inner">' + 
                                    '<h1>' + attraction.name + '</h1>' +
                                    '<span class="image main"><img src="images/attraction.jpg" alt="" height="400px" /></span>' +
                                    '<p>' + attraction.locality + '</p>' + 
                                    '<p>' + attraction.description + '</p>' + 
                                    '<p>' + attraction.lat + '</p>' + 
                                    '<p>' + attraction.lng + '</p>' + 
                                '</div>' + 
                            '</div>' +
                         '</div>';


                console.log(att);
                $("#Detail").append(att);
                
            },
            error: function(x, m) {
                console.log(x);
                console.log(m);
                alert('error!');
            }
        });
}