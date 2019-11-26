package com.pyd.postuciapp.beans;

public class Patient {
    // Numero de paciente
    private int id;

    private String dni;
    private String name;

    private int stayTime; // Si se a ingresado más de 7 días
    private int mecanicVentilation; // Si ha tenido ventialción mayor a 48 horas
    private int delirium; //Si el delirio es mayor a 48 horas
    private int neuromuscularDisease; // Tiene o no enfermedad neuromuscular
    private int pressureUlcer;
    private int artificialNutrition;
    private int doctorPetition;

    public Patient() {}
}
