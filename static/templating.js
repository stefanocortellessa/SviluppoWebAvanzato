
$(document).ready(function () {
    
    $("#events").hide(); 
    $("#eventDetail").hide();
    $("#choice").hide();
    

    localStorage.removeItem('token');

    var listEvents = function() {

        $.ajax( {
            headers: { 
                'Accept': 'application/json',
                'Content-Type': 'application/json' 
            }, 
            type: "GET",
            dataType: 'json',
            url: "http://localhost:8080/eventmanager/api/event/allEvents",
            success: function(data) { 

                var events = JSON.parse(JSON.stringify(data));
                $("#events").empty();

                if(events.length > 0) {
                    for(i = 0; i < events.length; i++) {
                        
                        var list = '<article class="style1">';
                        list = list +   '<span class="image">' + 
                                            '<img src="images/pic01.jpg" alt=""/>' +
                                        '</span>' + 
                                        '<a>' +
                                            '<h2 id="prova" onclick="detail(' + events[i].id + ')" >' + events[i].title + '</h2>' +
                                            '<div class="content">' + 
                                                '<p>' + events[i].locality + '</p>' +
                                            '</div>' +
                                        '</a>' +
                                        //'<p><button id="prova" class="button" onclick="detail(' + events[i].id + ')" >' + events[i].title + '</button></p>' + 
                                    '</article>';

                        $("#events").append(list);
                    }
                }
                else {
                    var h1 = '<h1>Non ci sono ancora eventi.</h1>';
                    $("#events").append(h1);
                }
            },
            error: function(x, m) {
                console.log(x);
                console.log(m);
                alert('error!');
            }
        });
    };

    //logo
    $("#logo").click(function() {

        $("#eventDetail").hide();
        $("#events").hide();
        $("#choice").show(); 
    });

    //scelta evento
    $("#eventsChoice").click(function() {

        $("#choice").hide();
        $("#events").show();

        listEvents();   
    });


    //login
    $("#login").click(function() {

        console.log("login");

        var email = $("#email").val();
        var password = $("#password").val();
        
        console.log("email: " + email);
        console.log("password: " + password);

        $.ajax( { 
            headers: { 
                'Accept': 'application/json',
                'Content-Type': 'application/json' 
            },
            type: "POST",
            data: JSON.stringify({ 
                "email" : email,
                "password" : password
            }),
            dataType: 'json',
            url: "http://localhost:8080/accountmanager/api/account/login",
            success: function(data) { 

                localStorage['token'] = JSON.parse(JSON.stringify(data));
                console.log(localStorage['token']);

                $("#LoginRegistrazione").hide();
                $("#choice").show();
   
                //alert(data);
            },
            error: function(x, m) {
                console.log(x);
                console.log(m);
                alert('error!');
            }
        });
        return false;
    });

    //registration
    $("#registration").click(function() {

        console.log("registration");

        var name = $("#name").val();
        var surname = $("#surname").val();
        var mail = $("#mail").val();
        var pass = $("#pass").val();

        console.log("namess: " + name);
        console.log("surnamess: " + surname);
        console.log("mailss: " + mail);
        console.log("passss: " + pass);

        $.ajax({ 
            headers: { 
                'Accept': 'application/json',
                'Content-Type': 'application/json' 
            },
            type: "POST",
            data: JSON.stringify({ 
                "name" : name,
                "surname" : surname,
                "email" : mail,
                "password" : pass
            }),
            dataType: 'json',
            url: "http://localhost:8080/accountmanager/api/account/insertUser",
            success: function(data) {  
                
                if(data == false){
                    alert('E-mail già registrata! Prova con una e-mail diversa');
                } else {
                    if(data == true){
                        alert('Complimenti! Sei registrato correttamente!');
                    }
                }      
            },
            error: function(x, m) {
                console.log(x);
                console.log(m);
                alert('error!');
            }
        });
        return false;
    });
});


