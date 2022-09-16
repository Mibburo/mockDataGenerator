package gr.uAegean.mockDataGenerator.util;

import gr.uAegean.mockDataGenerator.model.*;

import java.time.LocalDateTime;

public class GenerateDataUtil {

    public static LegacySystem generateLecagySystemERecon(String mitigationSystemActivated){
        LegacySystem legacySystem = new LegacySystem();
        legacySystem.setMitigationSystemActivated(mitigationSystemActivated);
        return legacySystem;
    }

    public static LegacySystem generateLecagySystemSitAssessment(){
        LegacySystem legacySystem = new LegacySystem();
        legacySystem.setMitigationSystemActivated("true");
        legacySystem.setAdjacentDetectors("true");
        legacySystem.setContainmentDoorsClosed("true");
        legacySystem.setGzgm("true");
        legacySystem.setInternalCommunication("false");
        legacySystem.setExternalCommunication("true");
        legacySystem.setPropulsionSystem("true");
        legacySystem.setSteeringSystem("true");
        legacySystem.setNavigationalSystem("true");

        return legacySystem;
    }

    public static LegacySystem generateLegacySystemHazard(String hazard, String statusPa, String statusWay){
        LegacySystem legacySystem = new LegacySystem();
        legacySystem.setPresenceHazards(hazard);
        legacySystem.setStatusPA(statusPa);
        legacySystem.setStatusWay(statusWay);

        return legacySystem;
    }

    public static LegacySystem generateLecagySystemPreAbandon(){
        LegacySystem legacySystem = new LegacySystem();
        legacySystem.setMitigationSystemActivated("true");
        legacySystem.setAdjacentDetectors("true");
        legacySystem.setContainmentDoorsClosed("true");
        legacySystem.setGzgm("true");
        legacySystem.setProximityAssistance("false");
        legacySystem.setExternalCommunication("true");
        legacySystem.setPropulsionSystem("true");
        legacySystem.setSteeringSystem("true");
        legacySystem.setNavigationalSystem("true");

        return legacySystem;
    }

    public static EvacuationCoordinator generateEvacuationCoordinator(String originator, String status, String timestamp){
        EvacuationCoordinator evacCoordinator = new EvacuationCoordinator();
        evacCoordinator.setOriginator(originator);
        evacCoordinator.setEvacStatus(status);
        evacCoordinator.setTimestamp(timestamp);

        return evacCoordinator;
    }

    public static SmartSafetySystem generateSmartSafetySystem(String type, String timestamp, String deck,
                                                              String positionX, String positionY){
        SmartSafetySystem smartSafetySystem = new SmartSafetySystem();
        smartSafetySystem.setType(type);
        smartSafetySystem.setTimestamp(timestamp);
        smartSafetySystem.setDeck(deck);
        smartSafetySystem.setPositionX(positionX);
        smartSafetySystem.setPositionY(positionY);

        return smartSafetySystem;
    }

    public static Camera generateCameraData(String evevntCode, String id, String timestamp){
        Camera camera = new Camera();
        camera.setEventCode(evevntCode);
        camera.setID(id);
        camera.setTimestamp(timestamp);

        return camera;
    }

    public static StabilityToolkit generateStabilityToolkit(){
        StabilityToolkit stabilityToolkit = new StabilityToolkit();
        stabilityToolkit.setState("0");
        stabilityToolkit.setTimestamp(DateUtil.dateToString(LocalDateTime.now()));
        stabilityToolkit.setFn("0");
        stabilityToolkit.setT("12");
        stabilityToolkit.setHs("8");
        stabilityToolkit.setL0("225");
        stabilityToolkit.setBeta("120");
        stabilityToolkit.setHead("180");
        stabilityToolkit.setSurge("3.087589");
        stabilityToolkit.setSway("5.388004");
        stabilityToolkit.setHeave("7.445331");
        stabilityToolkit.setRoll("32.617599");
        stabilityToolkit.setPitch("7.419556");
        stabilityToolkit.setYaw("3.533860");

        return stabilityToolkit;
    }

    public static ShmAlarm generateShmAlarm(){
        ShmAlarm shmAlarm = new ShmAlarm();
        shmAlarm.setTimestamp(DateUtil.dateToString(LocalDateTime.now()));
        shmAlarm.setType("grounding");
        shmAlarm.setComponentId("shm_01");
        shmAlarm.setAccelerometerX("0.1");
        shmAlarm.setAccelerometerY("-0.0");
        shmAlarm.setAccelerometerZ("-9.8");
        shmAlarm.setHeaveVelocity("0.0");
        shmAlarm.setHeaveAcceleration("0.0");
        shmAlarm.setHeaveShipMotion("0.0");
        shmAlarm.setYaw("0.0");
        shmAlarm.setPitch("0.0");
        shmAlarm.setRoll("0.0");

        return shmAlarm;
    }

    public static Weather generateWeather(){
        Weather weather = new Weather();
        weather.setTimestamp(DateUtil.dateToString(LocalDateTime.now()));
        weather.setCombDirectionDegrees("173.28");
        weather.setCombPeriod("3.9");
        weather.setCombSWHMeters("1.07");
        weather.setCurrentsDirectionDegrees("45.0");
        weather.setCurrentsSpeedKnots("0.05832");
        weather.setHumPercentage("68.5");
        weather.setIceCover("0.0");
        weather.setLat("54");
        weather.setLon("21");
        weather.setMslhPa("1016.26");
        weather.setSea("true");
        weather.setSwellDirectionMeters("117.32");
        weather.setSwellPeriod("4.17");
        weather.setSwellSWHMeters("0.13");
        weather.setTempCelciousDegrees("17.5");
        weather.setVisKm("24.135");
        weather.setWavesDirectionDegrees("173.89");
        weather.setWavesPeriod("3.9");
        weather.setWavesSWHMeters("1.06");
        weather.setWindDirectionDegrees("204.63");
        weather.setWindSpeedKnots("22.20048");

        return weather;
    }
}
