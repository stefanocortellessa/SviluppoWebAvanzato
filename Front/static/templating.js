
$(document).ready( function () {
    
    $("#events").hide(); 
    $("#Detail").hide();
    $("#choice").hide();
    $("#attractions").hide();
    $("#logout").hide();
    $("#BottoneRegistrazione").hide();
    $("#LoginForm").hide();

    localStorage.removeItem('token');

    var listEvents = function() {

    	//console.log('listEvents');
        $.ajax( {
            headers: { 
                'Accept': 'application/json',
                'Content-Type': 'application/json' 
            }, 
            type: "GET",
            dataType: 'json',
            url: "http://localhost:8080/visitaq/api/users/" + localStorage.getItem('token') + "/events",
            success: function(data) { 

                var events = JSON.parse(JSON.stringify(data));
                $("#events").empty();

                if(events.length > 0) {
                    for(i = 0; i < events.length; i++) {
                        
                        var list = '<article class="style1">';
                        list = list +   '<span class="image">' + 
                                            '<img src="images/' + events[i].image + '" height="300"alt=""/>' +
                                        '</span>' + 
                                        '<a>' +
                                            '<h2 id="prova" onclick="eventDetail(' + events[i].id + ')" >' + events[i].title + '</h2>' +
                                            '<div class="content">' + 
                                                '<p>' + events[i].locality + '</p>' +
                                            '</div>' +
                                        '</a>' +
                                        '</article>';

                        $("#events").append(list);
                    }
                }
                else {
                    var h1 = '<h1>Non ci sono ancora eventi.</h1>';
                    $("#events").append(h1);
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
	            
            	if(jqXHR.status == 401){
            		alert('Non Hai effetuato il login! Inserisci le tue credenziali!');
		        	homeLogin();
            	}else{
            		alert("Qualcosa è andato storto.. errore: " + jqXHR.status);
            	}
            }
        });
    };

    var listAttractions = function() {

    	//console.log('listAttractions');
        $.ajax( {
            headers: { 
                'Accept': 'application/json',
                'Content-Type': 'application/json' 
            }, 
            type: "GET",
            dataType: 'json',
            url: "http://localhost:8080/visitaq/api/users/" + localStorage.getItem('token') + "/attractions",
            success: function(data) { 

                var attractions = JSON.parse(JSON.stringify(data));
                $("#attractions").empty();

                if(attractions.length > 0) {
                    for(i = 0; i < attractions.length; i++) {
                        
                        var list = '<article class="style1">';
                        list = list +   '<span class="image">' + 
                                            '<img src="images/' + attractions[i].image + '" height="300"alt=""/>' +
                                        '</span>' + 
                                        '<a>' +
                                            '<h2 id="prova" onclick="attractionDetail(' + attractions[i].id + ')" >' + attractions[i].name + '</h2>' +
                                            '<div class="content">' + 
                                                '<p>' + attractions[i].locality + '</p>' +
                                            '</div>' +
                                        '</a>' +
                                        '</article>';

                        $("#attractions").append(list);
                    }
                }
                else {
                    var h1 = '<h1>Non ci sono ancora attrazioni.</h1>';
                    $("#attractions").append(h1);
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
	            
            	if(jqXHR.status == 401){
            		alert('Non Hai effetuato il login! Inserisci le tue credenziali!');
		        	homeLogin();
            	}else{
            		alert("Qualcosa è andato storto.. errore: " + jqXHR.status);
            	}
            }
        });
    };

    //logout
    $("#logout").click(function() {

        //console.log('logout');
        $.ajax( { 
            headers: { 
                'Accept': 'application/json',
                'Content-Type': 'application/json' 
            },
            type: "DELETE",
            dataType: 'json',
            url: "http://localhost:8080/visitaq/api/users/logout/" + localStorage.getItem('token'),
            success: function(data) { 

                localStorage.removeItem('token')

                $("#RegistrazioneForm").show();
                $("#BottoneLogin").show();
                $("#WelcomeDescription").show();
                $("#events").hide();
                $("#attractions").hide(); 
                $("#Detail").hide();
                $("#choice").hide();
                $("#logout").hide();
            },
            error: function(x, m) {
                console.log(x);
                console.log(m);
                alert('error!');

                homeLogin();
            }
        });
        return false;
    });

    //login
    $("#login").click(function() { 

        //console.log("login");
        var message="";
        var email = $("#email").val();
        var password = $("#password").val();
        
        if (email == "") message += "Inserire Email!\n";
        if (password == "") message += "Inserire Password!";
        if (message != "") {
            alert(message);
            return false;
        }
        //console.log("email: " + email);
        //console.log("password: " + password);
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
            contentType: "application/json; charset=utf-8",
            url: "http://localhost:8080/visitaq/api/users/login",
            success: function(data) { 

                localStorage['token'] = JSON.parse(JSON.stringify(data));
                
                $("#LoginForm").hide();
                $("#BottoneRegistrazione").hide();
                $("#WelcomeDescription").hide();
                $("#choice").show();
                $("#logout").show();
            },
            error: function(jqXHR, textStatus, errorThrown) {
	            
            	if(jqXHR.status == 403){
            		alert('Sicuro di essere registrato? E-mail o Password non sono corrette.');
		        	homeLogin();
            	}else{
            		alert("Qualcosa è andato storto.. errore: " + jqXHR.status);
            	}
            }
        });
        return false;
    });

    //registration
    $("#registration").click(function() {

        console.log("registration");

        var message = "";
        var name = $("#name").val();
        var surname = $("#surname").val();
        var validCharact = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
        var mail = $("#mail").val();
        var pass = $("#pass").val();

        if (name == "") message += "Inserire Nome!\n";
        if (surname == "") message += "Inserire Cognome!\n";
        if (mail == "") message += "Inserire Email!\n";
        if (!validCharact.test(mail)) message += "Email non valida!\n";
        if (pass == "") message += "Inserire Password!\n";
        if (message != "") {
            alert(message);
            return false;
        }

        //console.log("namess: " + name);
        //console.log("surnamess: " + surname);
        //console.log("mailss: " + mail);
        //console.log("passss: " + pass);
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
            url: "http://localhost:8080/visitaq/api/users",
            success: function(data) {  
                
                if(data == false){
                    alert('E-mail già registrata! Prova con una e-mail diversa');
                } else {
                    if(data == true){
                        alert('Complimenti! Sei registrato correttamente!');

                        $("#RegistrazioneForm").hide();
                        $("#BottoneLogin").hide();

                        $("#BottoneRegistrazione").show();
                        $("#LoginForm").show();
                    }
                }      
            },
            error: function(jqXHR, textStatus, errorThrown) {
	            
            	if(jqXHR.status == 403){
            		alert('Qualcosa è andato storto.. Riprova!');
            	}else{
            		alert("Errore: " + jqXHR.status);
            	}
            }
        });
        return false;
    });

    //azione quando si clicca sul logo
    $("#logo").click(function() {

    	console.log("URL: http://localhost:8080/visitaq/api/users/auth/" + localStorage.getItem('token'));

    	$.ajax( { 
            headers: { 
                'Accept': 'application/json',
                'Content-Type': 'application/json' 
            },
            type: "POST",
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            url: "http://localhost:8080/visitaq/api/users/auth/" + localStorage.getItem('token'),
            success: function(data) { 

                $("#Detail").hide();
		        $("#attractions").hide();
		        $("#events").hide();
		        $("#choice").show(); 
            },
            error: function(jqXHR, textStatus, errorThrown) {
	            
            	if(jqXHR.status == 401){
            		alert('Non Hai effetuato il login! Inserisci le tue credenziali!');
		        	homeLogin();
            	}else{
            		alert("Qualcosa è andato storto.. errore: " + jqXHR.status);
            	}
            }
        });
        return false;
    });

    //presentazione home per registrazione
    var homeRegistrazione = function(){
        $("#events").hide(); 
        $("#Detail").hide();
        $("#choice").hide();
        $("#attractions").hide();
        $("#logout").hide();
        $("#BottoneRegistrazione").hide();
        $("#LoginForm").hide();

        $("#RegistrazioneForm").show();
        $("#BottoneLogin").show();
        $("#WelcomeDescription").show();
    };

    //presentazione home per login
    var homeLogin = function(){
        $("#events").hide(); 
        $("#Detail").hide();
        $("#choice").hide();
        $("#attractions").hide();
        $("#logout").hide();
        $("#BottoneRegistrazione").hide();
        $("#LoginForm").hide();

        $("#LoginForm").show();
        $("#BottoneRegistrazione").show();
        $("#WelcomeDescription").show();
    };

    //azione quando si clicca sul Bottone Login
    $("#LoginButton").click(function() {

        $("#RegistrazioneForm").hide();
        $("#BottoneLogin").hide();

        $("#BottoneRegistrazione").show();
        $("#LoginForm").show();
    });

    //azione quando si clicca sul Bottone Registrazione
    $("#RegistrationButton").click(function() {

        $("#RegistrazioneForm").show();
        $("#BottoneLogin").show();

        $("#BottoneRegistrazione").hide();
        $("#LoginForm").hide();
    });

    //azione quando si sceglie l'evento
    $("#eventsChoice").click(function() {

        $("#choice").hide();
        $("#events").show();

        listEvents();   
    });

    //azione quando si sceglie l'attrazione
    $("#attractionChoice").click(function() {

        $("#choice").hide();
        $("#attractions").show();

        listAttractions();   
    });
});