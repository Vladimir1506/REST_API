package com.vladimir1506.rest;

import com.vladimir1506.rest.util.FlywayIntegrator;

public class Start {
    public static void main(String[] args) {
        new FlywayIntegrator().migrate();
    }
}
