function eventDetail(eventId) {

    //console.log('eventDetail');
    $("#events").hide();

    $.ajax({
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        }, 
        type: "GET",
        dataType: 'json',
        url: "http://localhost:8080/visitaq/api/users/" + localStorage.getItem('token') + "/events/" + eventId,
        success: function(data) { 

        	$("#Detail").show();
            $("#Detail").empty();

            var event = JSON.parse(JSON.stringify(data));
            var startDate = new Date(event.startDate);
            var endDate = new Date(event.endDate);

            var ev = '<div id="wrapper">';
            ev = ev +	'<div id="main">' +
	                		'<div class="inner">' + 
                                '<div class="row">' +
                                    '<div class="col-8">' +
                                    '<h1>' + event.title + '</h1>' +
                                    '<span class="image main"><img src="images/event.jpg" alt="" height="300px" /></span>' +
                                    '<p>' + event.description + '</p>' +
                                '</div>' +
                                '<div class="col-4">' +
                                    '<h1> More Info.. </h1>' +
                                    '<p><em><strong> Locality: </strong></em>' + event.locality + '</p>' +
                                    '<p><em><strong> The event is starting: </strong></em>' + startDate.toLocaleString() + '</p>' +
                                    '<p><em><strong> The event is ending: </strong></em>' + endDate.toLocaleString() + '</p>' +
                                '</div>' +
                                '<div class="col-8">' + 
                                    '<div id="map" style="width:100%;height:300px;"></div>' +
                                '</div>' +
	                        '</div>' + 
	                    '</div>' +
	                 '</div>';

            $("#Detail").append(ev);
            $("#map").hide();

            //impostazioni google maps
            var coordinate = new google.maps.LatLng(Number(event.lat), Number(event.lng));
            var mapOptions = { zoom:14 , mapTypeId: google.maps.MapTypeId.ROADMAP, center: coordinate }
            map = new google.maps.Map(document.getElementById('map'), mapOptions);
            var marker = new google.maps.Marker({position: coordinate, map: map});

            $("#map").show();
        },
        error: function(x, m) {
            console.log(x);
            console.log(m);
            alert('error!');
        }
    });
}

function attractionDetail(attractionId) {

    //console.log('attractionDetail');
    $("#attractions").hide();

    $.ajax({
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        }, 
        type: "GET",
        dataType: 'json',
        url: "http://localhost:8080/visitaq/api/users/" + localStorage.getItem('token') + "/attractions/" + attractionId,
        success: function(data) { 

            $("#Detail").show();
            var attraction = JSON.parse(JSON.stringify(data));
            $("#Detail").empty();
            
            var att = '<div id="wrapper">';
            att = att +   '<div id="main">' +
                            '<div class="inner">' + 
                                '<div class="row">' +
                                    '<div class="col-8">' +
                                    '<h1>' + attraction.name + '</h1>' +
                                    '<span class="image main"><img src="images/' + attraction.image + '" alt="" height="300px" /></span>' +
                                    '<p>' + attraction.description + '</p>' +
                                '</div>' +
                                '<div class="col-4">' +
                                    '<h1> More Info.. </h1>' +
                                    '<p><em><strong> Locality: </strong></em>' + attraction.locality + '</p>' +
                                '</div>' +
                                '<div class="col-8">' + 
                                    '<div id="map" style="width:100%;height:300px;"></div>' +
                                '</div>' +
                            '</div>' + 
                        '</div>' +
                     '</div>';

            $("#Detail").append(att);
            $("#map").hide();

            //impostazioni google maps
            var coordinate = new google.maps.LatLng(Number(attraction.lat), Number(attraction.lng));
            var mapOptions = { zoom:14 , mapTypeId: google.maps.MapTypeId.ROADMAP, center: coordinate }
            map = new google.maps.Map(document.getElementById('map'), mapOptions);
            var marker = new google.maps.Marker({position: coordinate, map: map});

            $("#map").show();                
        },
        error: function(x, m) {
            console.log(x);
            console.log(m);
            alert('error!');
        }
    });
}