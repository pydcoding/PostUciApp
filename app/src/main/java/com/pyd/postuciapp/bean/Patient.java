package com.pyd.postuciapp.bean;

public class Patient {
    // Numero de paciente
    private int id;

    private String dni;
    private String name;

    // Si se ha ingresado más de 7 días
    private int stayTime;
    // Si ha tenido ventilación mayor a 48 horas
    private int mecanicVentilation;
    //Si el delirio es mayor a 48 horas
    private int delirium;
    // Tiene o no enfermedad neuromuscular
    private int neuromuscularDisease;
    // Dispositivos invasivos o decanulación
    private int invasiveDevice;
    // Úlcera por presión
    private int pressureUlcer;
    // Nutrición artificial
    private int artificialNutrition;
    private int doctorPetition;

    public Patient(
            int id,
            String dni,
            String name,
            int stayTime,
            int mecanicVentilation,
            int delirium,
            int neuromuscularDisease,
            int invasiveDevice,
            int pressureUlcer,
            int artificialNutrition,
            int doctorPetition) {

        this.id = id;
        this.dni = dni;
        this.name = name;
        this.stayTime = stayTime;
        this.mecanicVentilation = mecanicVentilation;
        this.delirium = delirium;
        this.neuromuscularDisease = neuromuscularDisease;
        this.invasiveDevice = invasiveDevice;
        this.pressureUlcer = pressureUlcer;
        this.artificialNutrition = artificialNutrition;
        this.doctorPetition = doctorPetition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStayTime() {
        return stayTime;
    }

    public void setStayTime(int stayTime) {
        this.stayTime = stayTime;
    }

    public int getMecanicVentilation() {
        return mecanicVentilation;
    }

    public void setMecanicVentilation(int mecanicVentilation) {
        this.mecanicVentilation = mecanicVentilation;
    }

    public int getDelirium() {
        return delirium;
    }

    public void setDelirium(int delirium) {
        this.delirium = delirium;
    }

    public int getNeuromuscularDisease() {
        return neuromuscularDisease;
    }

    public void setNeuromuscularDisease(int neuromuscularDisease) {
        this.neuromuscularDisease = neuromuscularDisease;
    }

    public int getInvasiveDevice() {
        return invasiveDevice;
    }

    public void setInvasiveDevice(int invasiveDevice) {
        this.invasiveDevice = invasiveDevice;
    }

    public int getPressureUlcer() {
        return pressureUlcer;
    }

    public void setPressureUlcer(int pressureUlcer) {
        this.pressureUlcer = pressureUlcer;
    }

    public int getArtificialNutrition() {
        return artificialNutrition;
    }

    public void setArtificialNutrition(int artificialNutrition) {
        this.artificialNutrition = artificialNutrition;
    }

    public int getDoctorPetition() {
        return doctorPetition;
    }

    public void setDoctorPetition(int doctorPetition) {
        this.doctorPetition = doctorPetition;
    }
}
