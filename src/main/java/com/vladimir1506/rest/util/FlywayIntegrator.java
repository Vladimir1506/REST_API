package com.vladimir1506.rest.util;


import com.googlecode.flyway.core.Flyway;

public class FlywayIntegrator {
    public void migrate(){
        Flyway flyway = new Flyway();
        flyway.migrate();
    }
}
