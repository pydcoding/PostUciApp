package com.pyd.postuciapp.bean;

public class Patient {
    // Numero de paciente
    private int id;

    private String dni;
    private String name;

    private int stayTime; // Si se a ingresado más de 7 días
    private int mecanicVentilation; // Si ha tenido ventialción mayor a 48 horas
    private int delirium; //Si el delirio es mayor a 48 horas
    private int neuromuscularDisease; // Tiene o no enfermedad neuromuscular
    private int invasiveDevice; //Dispositivos invasivos o decanulación
    private int pressureUlcer; // Úlcera por presión
    private int artificialNutrition; //Nutrición artificial
    private int doctorPetition;

    public Patient() {}
}
