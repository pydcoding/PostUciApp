package com.pyd.postuciapp.bean;

import java.util.List;
import java.util.Random;

public class Patient {

    // Numero de paciente
    private int id;

    private String dni;
    private String name;

    // Lista de tests pendientes
    private List<Test.TestType> pendingTests;
    // Lista de tests realizados
    private List<Test.TestType> doneTests;

    // Si se ha ingresado más de 7 días, fecha de ingreso menos fecha de alta
    private boolean stayTime;
    // Si ha tenido ventilación mayor a 48 horas
    private boolean mecanicVentilation;
    // Si el delirio es mayor a 48 horas
    private boolean delirium;
    // Tiene o no enfermedad neuromuscular
    private boolean neuromuscularDisease;
    // Dispositivos invasivos o decanulación
    private boolean invasiveDevice;
    // Úlcera por presión
    private boolean pressureUlcer;
    // Nutrición artificial
    private boolean artificialNutrition;
    private boolean doctorPetition;

    public Patient(
            int id,
            String dni,
            String name,
            boolean stayTime,
            boolean mecanicVentilation,
            boolean delirium,
            boolean neuromuscularDisease,
            boolean invasiveDevice,
            boolean pressureUlcer,
            boolean artificialNutrition,
            boolean doctorPetition,
            List<Test.TestType> pendingTests,
            List<Test.TestType> doneTests) {

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
        this.pendingTests = pendingTests;
        this.doneTests = doneTests;
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

    public boolean getStayTime() {
        return stayTime;
    }

    public void setStayTime(boolean stayTime) {
        this.stayTime = stayTime;
    }

    public boolean getMecanicVentilation() {
        return mecanicVentilation;
    }

    public void setMecanicVentilation(boolean mecanicVentilation) {
        this.mecanicVentilation = mecanicVentilation;
    }

    public boolean getDelirium() {
        return delirium;
    }

    public void setDelirium(boolean delirium) {
        this.delirium = delirium;
    }

    public boolean getNeuromuscularDisease() {
        return neuromuscularDisease;
    }

    public void setNeuromuscularDisease(boolean neuromuscularDisease) {
        this.neuromuscularDisease = neuromuscularDisease;
    }

    public boolean getInvasiveDevice() {
        return invasiveDevice;
    }

    public void setInvasiveDevice(boolean invasiveDevice) {
        this.invasiveDevice = invasiveDevice;
    }

    public boolean getPressureUlcer() {
        return pressureUlcer;
    }

    public void setPressureUlcer(boolean pressureUlcer) {
        this.pressureUlcer = pressureUlcer;
    }

    public boolean getArtificialNutrition() {
        return artificialNutrition;
    }

    public void setArtificialNutrition(boolean artificialNutrition) {
        this.artificialNutrition = artificialNutrition;
    }

    public boolean getDoctorPetition() {
        return doctorPetition;
    }

    public void setDoctorPetition(boolean doctorPetition) {
        this.doctorPetition = doctorPetition;
    }

    public List<Test.TestType> getPendingTests() { return pendingTests; }

    public void setPendingTests(List<Test.TestType> pendingTests) { this.pendingTests = pendingTests; }

    public List<Test.TestType> getDoneTests() { return doneTests; }

    public void setDoneTests(List<Test.TestType> doneTests) { this.doneTests = doneTests; }

    public int calculateSeverity() {
        Random random = new Random();
        return random.nextInt(101);
    }
}
