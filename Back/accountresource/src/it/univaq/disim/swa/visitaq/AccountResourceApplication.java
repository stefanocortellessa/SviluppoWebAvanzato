package it.univaq.disim.swa.visitaq;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

@ApplicationPath("api")
public class AccountResourceApplication extends ResourceConfig {

	public AccountResourceApplication() {
		//Scanning package api per definizione dei componenti REST
		packages("it.univaq.disim.swa.visitaq.api");
		
		//registrazione per logging delle richieste http
		register(new LoggingFeature(Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME),
				Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, 10000));

		//registrazione del provider per serializzazione/deserializzazione oggetti in json tramite jackson
		register(JacksonJsonProvider.class);
		
	}
}
