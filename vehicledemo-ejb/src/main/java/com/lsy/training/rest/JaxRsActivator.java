package com.lsy.training.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * A class extending {@link Application} and annotated with @ApplicationPath is the Java EE 6
 * "no XML" approach to activating JAX-RS.
 * 
 * <p>
 * Resources are served relative to the servlet path specified in the {@link ApplicationPath}
 * annotation.
 * </p>
 */
@ApplicationPath("/rest")       // Pfad relativ zum Web-App-Pfad (Wird in der Konfig des Maven-EAR-Plugins gesetzt)
public class JaxRsActivator extends Application {
   /* class body intentionally left blank */
}
