package gr.uAegean.mockDataGenerator.restController;

import com.fasterxml.jackson.databind.ObjectMapper;
import gr.uAegean.mockDataGenerator.model.*;
import gr.uAegean.mockDataGenerator.service.DBProxyServiceImpl;
import gr.uAegean.mockDataGenerator.service.KafkaService;
import gr.uAegean.mockDataGenerator.util.CryptoUtils;
import gr.uAegean.mockDataGenerator.util.DateUtil;
import gr.uAegean.mockDataGenerator.util.GenerateDataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

import static gr.uAegean.mockDataGenerator.util.Constants.*;

@Slf4j
@RestController
public class MockDataController {

    private final KafkaService kafkaService;

    private final DBProxyServiceImpl dbProxyService;

    private final RestTemplate restTemplate;


    private final static String CONDUCTOR_URI = System.getenv("CONDUCTOR_URI");

    @Autowired
    MockDataController(KafkaService kafkaService, DBProxyServiceImpl dbProxyService, RestTemplate restTemplate) {
        this.kafkaService = kafkaService;
        this.dbProxyService = dbProxyService;
        this.restTemplate = restTemplate;
    }

    //T0_1
    @PostMapping({"/act1_SSS"})
    public void act1MockSSS() {

        LegacySystem legacySystem = GenerateDataUtil.generateLecagySystemERecon("true");
        SmartSafetySystem smartSafetySystem = GenerateDataUtil.generateSmartSafetySystem(
                "Fire", DateUtil.dateToString(LocalDateTime.now()), "5", "13.72", "5.47");
        SmokeDetectorTO smokeDetectorTO = new SmokeDetectorTO();
        String[] detectors = new String[]{"Fire Detector 1 on Garage"};
        smokeDetectorTO.setTriggered(detectors);
        kafkaService.saveToKafkaGeneric(LEGACY_TOPIC, legacySystem);
        kafkaService.saveToKafkaGeneric(SMART_SAFETY_SYSTEM_TOPIC, smartSafetySystem);
        kafkaService.saveToKafkaGeneric(SMOKE_DETECTOR, smokeDetectorTO);

    }

    @PostMapping({"/act1_SSS_escalates"})
    public void act1MockSSSEscalates() {
        LegacySystem legacySystem = GenerateDataUtil.generateLecagySystemERecon("true");
        SmartSafetySystem smartSafetySystem = GenerateDataUtil.generateSmartSafetySystem(
                "Fire", DateUtil.dateToString(LocalDateTime.now()), "5", "13.72", "5.47");
        SmokeDetectorTO smokeDetectorTO = new SmokeDetectorTO();
        String[] detectors = new String[]{"Fire Detector 2 on Garage"};
        smokeDetectorTO.setTriggered(detectors);
        kafkaService.saveToKafkaGeneric(LEGACY_TOPIC, legacySystem);
        kafkaService.saveToKafkaGeneric(SMART_SAFETY_SYSTEM_TOPIC, smartSafetySystem);
        kafkaService.saveToKafkaGeneric(SMOKE_DETECTOR, smokeDetectorTO);
    }




    @PostMapping({"/act5_SSS_new"})
    public void act1_SSS_new() {
        //TODO mock-srap increased risk of delay

        SmokeDetectorTO smokeDetectorTO = new SmokeDetectorTO();
        String[] detectors = new String[]{"Smoke Detector 1 on Muster Station D"};
        smokeDetectorTO.setTriggered(detectors);
        kafkaService.saveToKafkaGeneric(SMOKE_DETECTOR, smokeDetectorTO);

    }


    //T0_2
    @PostMapping({"/act1_ec"})
    public void act1MockEC() {
        EvacuationCoordinator evacCoordinator = GenerateDataUtil.generateEvacuationCoordinator(
                "evacuation-coordinator", 1, DateUtil.dateToString(LocalDateTime.now()));
//        kafkaService.saveToKafkaGeneric(EVAC_COORDINATOR_TOPIC, evacCoordinator);

        LegacySystem legacySystem = GenerateDataUtil.generateLecagySystemERecon("true");
        SmartSafetySystem smartSafetySystem = GenerateDataUtil.generateSmartSafetySystem(
                "Fire", DateUtil.dateToString(LocalDateTime.now()), "5", "13.72", "5.47");
        SmokeDetectorTO smokeDetectorTO = new SmokeDetectorTO();
        String[] detectors = new String[]{"Fire Detector 1 on Garage"};
        smokeDetectorTO.setTriggered(detectors);

        kafkaService.saveToKafkaGeneric(LEGACY_TOPIC, legacySystem);
        kafkaService.saveToKafkaGeneric(SMART_SAFETY_SYSTEM_TOPIC, smartSafetySystem);
        kafkaService.saveToKafkaGeneric(SMOKE_DETECTOR, smokeDetectorTO);
    }


    //T1
    @PostMapping({"/act2_teams_sent"})
    public void act2MockTeamsSent() {
        EvacuationCoordinator evacCoordinator = GenerateDataUtil.generateEvacuationCoordinator(
                "evacuation-coordinator", 1, DateUtil.dateToString(LocalDateTime.now()));
//        kafkaService.saveToKafkaGeneric(EVAC_COORDINATOR_TOPIC, evacCoordinator);

        LegacySystem legacySystem = new LegacySystem();
        legacySystem.setMitigationSystemActivated("false");
        legacySystem.setAdjacentDetectors("false");
        legacySystem.setContainmentDoorsClosed("false");
        legacySystem.setGzgm("true");
        legacySystem.setInternalCommunication("true");
        legacySystem.setExternalCommunication("true");
        legacySystem.setPropulsionSystem("true");
        legacySystem.setSteeringSystem("true");
        legacySystem.setNavigationalSystem("true");
        SmartSafetySystem smartSafetySystem = GenerateDataUtil.generateSmartSafetySystem(
                "Fire", DateUtil.dateToString(LocalDateTime.now()), "5", "13.72", "5.47");
        StabilityToolkit stabilityToolkit = GenerateDataUtil.generateStabilityToolkit();
        ShmAlarm shmAlarm = GenerateDataUtil.generateShmAlarm();
        kafkaService.saveToKafkaGeneric(LEGACY_TOPIC, legacySystem);
        kafkaService.saveToKafkaGeneric(SMART_SAFETY_SYSTEM_TOPIC, smartSafetySystem);
        kafkaService.saveToKafkaGeneric(STABILITY_TOOLKIT_TOPIC, stabilityToolkit);
        kafkaService.saveToKafkaGeneric(SHM_ALARM_TOPIC, shmAlarm);
    }


    //T2
    @PostMapping("/act2_fire_escalates")
    public void act2FireEscalates() {
        LegacySystem legacySystem = new LegacySystem();
        legacySystem.setMitigationSystemActivated("true");
        legacySystem.setAdjacentDetectors("true");
        legacySystem.setContainmentDoorsClosed("false");
        legacySystem.setGzgm("true");
        legacySystem.setInternalCommunication("true");
        legacySystem.setExternalCommunication("true");
        legacySystem.setPropulsionSystem("true");
        legacySystem.setSteeringSystem("true");
        legacySystem.setNavigationalSystem("true");
        StabilityToolkit stabilityToolkit = GenerateDataUtil.generateStabilityToolkit();
        ShmAlarm shmAlarm = GenerateDataUtil.generateShmAlarm();

//        SmartSafetySystem smartSafetySystemAlert1 = GenerateDataUtil.generateSmartSafetySystem(
//                "Fire", DateUtil.dateToString(LocalDateTime.now()), "5", "15.72", "5.47");
        SmartSafetySystem smartSafetySystemAlert2 = GenerateDataUtil.generateSmartSafetySystem(
                "Fire", DateUtil.dateToString(LocalDateTime.now()), "5", "13.72", "5.47");

        kafkaService.saveToKafkaGeneric(LEGACY_TOPIC, legacySystem);
        kafkaService.saveToKafkaGeneric(STABILITY_TOOLKIT_TOPIC, stabilityToolkit);
        kafkaService.saveToKafkaGeneric(SHM_ALARM_TOPIC, shmAlarm);
//        kafkaService.saveToKafkaGeneric(SMART_SAFETY_SYSTEM_TOPIC, smartSafetySystemAlert1);
        kafkaService.saveToKafkaGeneric(SMART_SAFETY_SYSTEM_TOPIC, smartSafetySystemAlert2);
    }

    //T3
    @PostMapping("/act3_fire_escalates")
    public void act3FireEscalates() {
        LegacySystem legacySystem = new LegacySystem();
        legacySystem.setMitigationSystemActivated("true");
        legacySystem.setAdjacentDetectors("true");
        legacySystem.setContainmentDoorsClosed("false");
        legacySystem.setGzgm("true");
        legacySystem.setInternalCommunication("true");
        legacySystem.setExternalCommunication("true");
        legacySystem.setPropulsionSystem("true");
        legacySystem.setSteeringSystem("true");
        legacySystem.setNavigationalSystem("true");
        StabilityToolkit stabilityToolkit = GenerateDataUtil.generateStabilityToolkit();
        ShmAlarm shmAlarm = GenerateDataUtil.generateShmAlarm();

        SmartSafetySystem smartSafetySystemAlert1 = GenerateDataUtil.generateSmartSafetySystem(
                "Fire", DateUtil.dateToString(LocalDateTime.now()), "5", "15.72", "5.47");


        kafkaService.saveToKafkaGeneric(LEGACY_TOPIC, legacySystem);
        kafkaService.saveToKafkaGeneric(STABILITY_TOOLKIT_TOPIC, stabilityToolkit);
        kafkaService.saveToKafkaGeneric(SHM_ALARM_TOPIC, shmAlarm);
        kafkaService.saveToKafkaGeneric(SMART_SAFETY_SYSTEM_TOPIC, smartSafetySystemAlert1);
    }


    @PostMapping("/act3_crew_positions")
    public void act3CrewPositions() {
        EvacuationCoordinatorEventTO eventTO = new EvacuationCoordinatorEventTO();
        eventTO.setEvacuationStatus(2);
        eventTO.setOriginator("evacuation-coordinator");
        eventTO.setTimestamp((new Timestamp(System.currentTimeMillis())).toString());
        kafkaService.saveToKafkaGeneric(EVAC_COORDINATOR_TOPIC, eventTO);
    }

    @PostMapping("/act3_alert_passengers")
    public void act3AlertPassengers() {
        PhaseTaskTO phaseTaskTO = new PhaseTaskTO();
        phaseTaskTO.setPhase("5");
        phaseTaskTO.setTaskId("5.1");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<PhaseTaskTO> request =
                new HttpEntity<PhaseTaskTO>(phaseTaskTO, headers);
        String uri = CONDUCTOR_URI + "workflow/alert_passengers?priority=0";
        ResponseEntity<String> responseEntityStr = restTemplate.
                postForEntity(uri, request, String.class);
    }


    @PostMapping("/act4_send_mustering_instructions")
    public void act4MusteringInstructions() {
        EvacuationCoordinatorEventTO eventTO = new EvacuationCoordinatorEventTO();
        eventTO.setEvacuationStatus(3);
        eventTO.setOriginator("evacuation-coordinator");
        eventTO.setTimestamp((new Timestamp(System.currentTimeMillis())).toString());
        kafkaService.saveToKafkaGeneric(EVAC_COORDINATOR_TOPIC, eventTO);
    }

    //T4
    @PostMapping("/act4_fire_no_congestion")
    public void act4FireNoCongestion() {
        LegacySystem legacySystem12 = GenerateDataUtil.generateLegacySystemHazard("No hazard",
                "True", "True", "9");


        Camera camera12 = GenerateDataUtil.generateCameraData("0",
                "camera-01", DateUtil.dateToString(LocalDateTime.now()));

        kafkaService.saveToKafkaGeneric(LEGACY_TOPIC, legacySystem12);
        kafkaService.saveToKafkaGeneric(CAMERAS_TOPIC, camera12);
        kafkaService.saveToKafkaGeneric(VRD_CAMERA_TOPIC, camera12);
    }


    //T6
    @PostMapping("/act4_fire_delays_Z2D8")
    public void act4FireDelaysZ2D8() {

        LegacySystem legacySystem3 = GenerateDataUtil.generateLegacySystemHazard("No hazard",
                "True", "True", "8");

        Camera camera3 = GenerateDataUtil.generateCameraData("1",
                "camera-01", DateUtil.dateToString(LocalDateTime.now()));

        kafkaService.saveToKafkaGeneric(LEGACY_TOPIC, legacySystem3);
        kafkaService.saveToKafkaGeneric(CAMERAS_TOPIC, camera3);
        kafkaService.saveToKafkaGeneric(VRD_CAMERA_TOPIC, camera3);
    }

    //T8
    @PostMapping("/act5")
    public void act5() {

        LegacySystem legacySystem = GenerateDataUtil.generateLegacySystemHazard("Smoke",
                "True", "True", "8");
//        SmartSafetySystem smartSafetySystemAlert = GenerateDataUtil.generateSmartSafetySystem(
//                "Fire", DateUtil.dateToString(LocalDateTime.now()), "7", "7.2", "5.47");

        Camera camera3 = GenerateDataUtil.generateCameraData("1",
                "camera-01", DateUtil.dateToString(LocalDateTime.now()));
        kafkaService.saveToKafkaGeneric(LEGACY_TOPIC, legacySystem);
        kafkaService.saveToKafkaGeneric(CAMERAS_TOPIC, camera3);
        kafkaService.saveToKafkaGeneric(VRD_CAMERA_TOPIC, camera3);
//        kafkaService.saveToKafkaGeneric(SMART_SAFETY_SYSTEM_TOPIC, smartSafetySystemAlert);
    }

    @PostMapping("/act5_block_MS")
    public void act5BlockMS() {
        ObjectMapper mapper = new ObjectMapper();
        HttpRequest request = null;
        BlockedGeofenceTO blockedGeofenceTO = new BlockedGeofenceTO();
        blockedGeofenceTO.setGeofence("7DG4");
        blockedGeofenceTO.setStatus("blocked");

        try {
            request = HttpRequest.newBuilder()
                    .uri(URI.create(CONDUCTOR_URI + "workflow/detect_blocked_geofence?priority=0"))
                    .header("Content-Type", "application/json")
                    .method("POST", HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(blockedGeofenceTO)))
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            log.info("made a call to {}detect_blocked_geofence", CONDUCTOR_URI);
            log.info("response {}", response.body());
        } catch (IOException | InterruptedException e) {
            log.error(e.getMessage());
        }
    }

    //T9
    @PostMapping("/act5_update_recommendations")
    public void act5UpdateRecommendations() {
        LegacySystem legacySystem = GenerateDataUtil.generateLegacySystemHazard("Smoke",
                "True", "True", "8");
        Camera camera3 = GenerateDataUtil.generateCameraData("1",
                "camera-01", DateUtil.dateToString(LocalDateTime.now()));
        kafkaService.saveToKafkaGeneric(LEGACY_TOPIC, legacySystem);
        kafkaService.saveToKafkaGeneric(CAMERAS_TOPIC, camera3);
        kafkaService.saveToKafkaGeneric(VRD_CAMERA_TOPIC, camera3);
    }

    //T11
    @PostMapping("/act6_z4d9_issue")
    public void passengerIssue() {
        LegacySystem legacySystem1 = GenerateDataUtil.generateLegacySystemHazard("Smoke",
                "True", "True", "8");

        final List<String> zone9 = Arrays.asList("9BG1", "9BG2", "S9-8.1", "C9.1");

        Optional<PameasPerson> randomPersonOnD9 = dbProxyService.getPassengerDetails().stream().filter(pameasPerson -> {
            if (pameasPerson.getLocationInfo() == null || pameasPerson.getLocationInfo().getGeofenceHistory() == null
                    || pameasPerson.getLocationInfo().getGeofenceHistory().size() == 0) {
                return false;
            } else {
                return zone9.contains(pameasPerson.getLocationInfo().getGeofenceHistory().get(0).getGfName());
            }
        }).findFirst();
        //store update person data
        randomPersonOnD9.ifPresent(person -> {
            try {
                CryptoUtils cryptoUtils = new CryptoUtils();
                person.getPersonalInfo().setPersonalId(cryptoUtils.encryptBase64(person.getPersonalInfo().getPersonalId()));
                person.getLocationInfo().setSpeed("0.1");
                person.getPersonalInfo().setHeartBeat("180");
                person.getPersonalInfo().setOxygenSaturation("70");
            } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException |
                     IllegalBlockSizeException | BadPaddingException | IOException | InvalidKeySpecException e) {
                log.error(e.getMessage());
            }
            dbProxyService.updatePerson(person);

        });
        Camera camera3 = GenerateDataUtil.generateCameraData("1",
                "camera-01", DateUtil.dateToString(LocalDateTime.now()));
        kafkaService.saveToKafkaGeneric(LEGACY_TOPIC, legacySystem1);
        kafkaService.saveToKafkaGeneric(CAMERAS_TOPIC, camera3);
        kafkaService.saveToKafkaGeneric(VRD_CAMERA_TOPIC, camera3);
    }

    @PostMapping("/mock-srap-issue")
    public void mockSrapIssue() {
        final List<String> zone9 = Arrays.asList("9BG1", "9BG2", "S9-8.1", "C9.1");

        Optional<PameasPerson> randomPersonOnD9 = dbProxyService.getPassengerDetails().stream().filter(pameasPerson -> {
            if (pameasPerson.getLocationInfo() == null || pameasPerson.getLocationInfo().getGeofenceHistory() == null
                    || pameasPerson.getLocationInfo().getGeofenceHistory().size() == 0) {
                return false;
            } else {
                return zone9.contains(pameasPerson.getLocationInfo().getGeofenceHistory().get(0).getGfName());
            }
        }).findFirst();

        if (randomPersonOnD9.isPresent()) {
            SrapTO srapTO = new SrapTO();
            HashMap<String, String> indivStatuses = new HashMap<>();
            srapTO.setMessageId("1");
            srapTO.setAbandonVessel("false");
            srapTO.setTimeout("10");

            indivStatuses.put(randomPersonOnD9.get().getPersonalInfo().getPersonalId(), "assistance_required");
            srapTO.setIndividualStatus(indivStatuses);
            kafkaService.saveToKafkaGeneric(SRAP_TOPIC, srapTO);
        }

        //store update person data
        randomPersonOnD9.ifPresent(person -> {
            try {
                CryptoUtils cryptoUtils = new CryptoUtils();
                person.getPersonalInfo().setPersonalId(cryptoUtils.encryptBase64(person.getPersonalInfo().getPersonalId()));
            } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException |
                     IllegalBlockSizeException | BadPaddingException | IOException | InvalidKeySpecException e) {
                log.error(e.getMessage());
            }
            dbProxyService.updatePerson(person);

        });

    }


    @PostMapping("/braceletIssue")
    public void braceletIssue() {
        BraceletFallTO braceletFallTO = new BraceletFallTO();
        braceletFallTO.setId("SB0015");
        braceletFallTO.setEventId(1);
        braceletFallTO.setTimestamp(new Timestamp(System.currentTimeMillis()).toString());
        kafkaService.saveToKafkaGeneric(BRACELET_FALL, braceletFallTO);

    }


    //T13
    @PostMapping("/act7_preAbandonmentAssessment")
    public void preAbandonmentAssessment() {
        LegacySystem legacySystem = new LegacySystem();
        legacySystem.setMitigationSystemActivated("true");
        legacySystem.setAdjacentDetectors("true");
        legacySystem.setContainmentDoorsClosed("false");
        legacySystem.setGzgm("true");
        legacySystem.setInternalCommunication("true");
        legacySystem.setExternalCommunication("true");
        legacySystem.setPropulsionSystem("false");
        legacySystem.setSteeringSystem("true");
        legacySystem.setNavigationalSystem("true");

        StabilityToolkit stabilityToolkit = GenerateDataUtil.generateStabilityToolkit();
        ShmAlarm shmAlarm = GenerateDataUtil.generateShmAlarm();
        Weather weather = GenerateDataUtil.generateWeather();
        kafkaService.saveToKafkaGeneric(LEGACY_TOPIC, legacySystem);
        kafkaService.saveToKafkaGeneric(STABILITY_TOOLKIT_TOPIC, stabilityToolkit);
        kafkaService.saveToKafkaGeneric(SHM_ALARM_TOPIC, shmAlarm);
        kafkaService.saveToKafkaGeneric(WEATHER_TOPIC, weather);
    }

    @PostMapping("/act7_abandon")
    public void abandon() {
        EvacuationCoordinatorEventTO eventTO = new EvacuationCoordinatorEventTO();
        eventTO.setEvacuationStatus(4);
        eventTO.setOriginator("evacuation-coordinator");
        eventTO.setTimestamp((new Timestamp(System.currentTimeMillis())).toString());
        kafkaService.saveToKafkaGeneric(EVAC_COORDINATOR_TOPIC, eventTO);
    }






    @PostMapping("/dontAbandon")
    public void dontAbandon() {
        SrapTO srapTO = new SrapTO();
        srapTO.setAbandonVessel("Do not Abandon");
        srapTO.setSender("t");
        srapTO.setPassengerId("1");
        srapTO.setStatus("Do not Abandon");
        srapTO.setIndividualStatus(new HashMap<>());
        srapTO.setTimeout("");
        srapTO.setMessageId("");
        srapTO.setEscapeRoutes(new HashMap<>());
        srapTO.setAbilityToCommunicate("");
        srapTO.setCriticalSystemStatus("");
        srapTO.setEffectivenessOfMitigationMeasures("");
        srapTO.setZoneId("");
        srapTO.setVesselStatus("");
        srapTO.setUrgencyOfAbandonment("");
        srapTO.setTimestamp("");
        srapTO.setStructuralIntegrity("");
        srapTO.setStatusOfPassiveContainment("");
        srapTO.setStability("");
        srapTO.setSrapModel("");
        srapTO.setSpreading("");
        srapTO.setHullStatus("");
        srapTO.setSituationAssessment("");
        srapTO.setRiskOfDelay(new HashMap<>());
        srapTO.setPaxVulnerabilityOnboard("");
        srapTO.setPassengersProxToHazards("");
        srapTO.setGroupPerformance(new HashMap<>());
        kafkaService.saveToKafkaGeneric(SRAP_TOPIC, srapTO);
    }



    @PostMapping("/abandon")
    public void abandonMock() {
        SrapTO srapTO = new SrapTO();
        srapTO.setAbandonVessel("Abandon");
        srapTO.setSender("t");
        srapTO.setPassengerId("1");
        srapTO.setStatus("Abandon");
        srapTO.setIndividualStatus(new HashMap<>());
        srapTO.setTimeout("");
        srapTO.setMessageId("");
        srapTO.setEscapeRoutes(new HashMap<>());
        srapTO.setAbilityToCommunicate("");
        srapTO.setCriticalSystemStatus("");
        srapTO.setEffectivenessOfMitigationMeasures("");
        srapTO.setZoneId("");
        srapTO.setVesselStatus("");
        srapTO.setUrgencyOfAbandonment("");
        srapTO.setTimestamp("");
        srapTO.setStructuralIntegrity("");
        srapTO.setStatusOfPassiveContainment("");
        srapTO.setStability("");
        srapTO.setSrapModel("");
        srapTO.setSpreading("");
        srapTO.setHullStatus("");
        srapTO.setSituationAssessment("");
        srapTO.setRiskOfDelay(new HashMap<>());
        srapTO.setPaxVulnerabilityOnboard("");
        srapTO.setPassengersProxToHazards("");
        srapTO.setGroupPerformance(new HashMap<>());
        kafkaService.saveToKafkaGeneric(SRAP_TOPIC, srapTO);
    }


    @PostMapping("/mockSrapCongestions1")
    public void mockSrapCongestions() {
        SrapTO srapTO = new SrapTO();
        srapTO.setAbandonVessel("Abandon");
        srapTO.setSender("t");
        srapTO.setPassengerId("1");
        srapTO.setStatus("Do not Abandon");
        srapTO.setIndividualStatus(new HashMap<>());
        srapTO.setTimeout("");
        srapTO.setMessageId("");
        srapTO.setEscapeRoutes(new HashMap<>());
        srapTO.setAbilityToCommunicate("");
        srapTO.setCriticalSystemStatus("");
        srapTO.setEffectivenessOfMitigationMeasures("");
        srapTO.setZoneId("");
        srapTO.setVesselStatus("");
        srapTO.setUrgencyOfAbandonment("");
        srapTO.setTimestamp("");
        srapTO.setStructuralIntegrity("");
        srapTO.setStatusOfPassiveContainment("");
        srapTO.setStability("");
        srapTO.setSrapModel("");
        srapTO.setSpreading("");
        srapTO.setHullStatus("");
        srapTO.setSituationAssessment("");
        srapTO.setRiskOfDelay(new HashMap<>());
        srapTO.setPaxVulnerabilityOnboard("");
        srapTO.setPassengersProxToHazards("");
        srapTO.setGroupPerformance(new HashMap<>());
        Map<String, String> riskOfDelay = new HashMap<>();
        riskOfDelay.put("Z4D9","Low");
        riskOfDelay.put("Z4D8","Medium");
        riskOfDelay.put("Z1D8","Medium");
        srapTO.setRiskOfDelay(riskOfDelay);
        kafkaService.saveToKafkaGeneric(SRAP_TOPIC, srapTO);
    }


    @PostMapping("/mockSrapCongestions-high")
    public void mockSrapCongestions2() {
        SrapTO srapTO = new SrapTO();
        srapTO.setAbandonVessel("Abandon");
        srapTO.setSender("t");
        srapTO.setPassengerId("1");
        srapTO.setStatus("Do not Abandon");
        srapTO.setIndividualStatus(new HashMap<>());
        srapTO.setTimeout("");
        srapTO.setMessageId("");
        srapTO.setEscapeRoutes(new HashMap<>());
        srapTO.setAbilityToCommunicate("");
        srapTO.setCriticalSystemStatus("");
        srapTO.setEffectivenessOfMitigationMeasures("");
        srapTO.setZoneId("");
        srapTO.setVesselStatus("");
        srapTO.setUrgencyOfAbandonment("");
        srapTO.setTimestamp("");
        srapTO.setStructuralIntegrity("");
        srapTO.setStatusOfPassiveContainment("");
        srapTO.setStability("");
        srapTO.setSrapModel("");
        srapTO.setSpreading("");
        srapTO.setHullStatus("");
        srapTO.setSituationAssessment("");
        srapTO.setRiskOfDelay(new HashMap<>());
        srapTO.setPaxVulnerabilityOnboard("");
        srapTO.setPassengersProxToHazards("");
        srapTO.setGroupPerformance(new HashMap<>());
        Map<String, String> riskOfDelay = new HashMap<>();
        riskOfDelay.put("Z4D9","High");
        riskOfDelay.put("Z4D8","High");
        riskOfDelay.put("Z1D3","High");
        riskOfDelay.put("Z1D8","High");
        srapTO.setRiskOfDelay(riskOfDelay);
        kafkaService.saveToKafkaGeneric(SRAP_TOPIC, srapTO);
    }


    @PostMapping("/mockSrapCongestions3")
    public void mockSrapCongestions3() {
        SrapTO srapTO = new SrapTO();
        srapTO.setAbandonVessel("Abandon");
        srapTO.setSender("t");
        srapTO.setPassengerId("1");
        srapTO.setStatus("Do not Abandon");
        srapTO.setIndividualStatus(new HashMap<>());
        srapTO.setTimeout("");
        srapTO.setMessageId("");
        srapTO.setEscapeRoutes(new HashMap<>());
        srapTO.setAbilityToCommunicate("");
        srapTO.setCriticalSystemStatus("");
        srapTO.setEffectivenessOfMitigationMeasures("");
        srapTO.setZoneId("");
        srapTO.setVesselStatus("");
        srapTO.setUrgencyOfAbandonment("");
        srapTO.setTimestamp("");
        srapTO.setStructuralIntegrity("");
        srapTO.setStatusOfPassiveContainment("");
        srapTO.setStability("");
        srapTO.setSrapModel("");
        srapTO.setSpreading("");
        srapTO.setHullStatus("");
        srapTO.setSituationAssessment("");
        srapTO.setRiskOfDelay(new HashMap<>());
        srapTO.setPaxVulnerabilityOnboard("");
        srapTO.setPassengersProxToHazards("");
        srapTO.setGroupPerformance(new HashMap<>());
        Map<String, String> riskOfDelay = new HashMap<>();
        riskOfDelay.put("Z4D9","");
        riskOfDelay.put("Z4D8","");
        riskOfDelay.put("Z1D8","");
        srapTO.setRiskOfDelay(riskOfDelay);
        kafkaService.saveToKafkaGeneric(SRAP_TOPIC, srapTO);
    }




    @PostMapping("/srap-recomend-no-ga")
    public void mockSrapNoGA() {
        SrapTO srapTO = new SrapTO();
        srapTO.setAbandonVessel("");
        srapTO.setSender("t");
        srapTO.setPassengerId("1");
        //
        srapTO.setStatus("Stay Check");
        srapTO.setSituationAssessment("no sound ga");
        //
        srapTO.setIndividualStatus(new HashMap<>());
        srapTO.setTimeout("");
        srapTO.setMessageId("");
        srapTO.setEscapeRoutes(new HashMap<>());
        srapTO.setAbilityToCommunicate("");
        srapTO.setCriticalSystemStatus("");
        srapTO.setEffectivenessOfMitigationMeasures("");
        srapTO.setZoneId("");
        srapTO.setVesselStatus("");
        srapTO.setUrgencyOfAbandonment("");
        srapTO.setTimestamp("");
        srapTO.setStructuralIntegrity("");
        srapTO.setStatusOfPassiveContainment("");
        srapTO.setStability("");
        srapTO.setSrapModel("");
        srapTO.setSpreading("");
        srapTO.setHullStatus("");
        srapTO.setRiskOfDelay(new HashMap<>());
        srapTO.setPaxVulnerabilityOnboard("");
        srapTO.setPassengersProxToHazards("");
        srapTO.setGroupPerformance(new HashMap<>());
        Map<String, String> riskOfDelay = new HashMap<>();
//        riskOfDelay.put("Z4D9","High");
//        riskOfDelay.put("Z4D8","High");
//        riskOfDelay.put("Z1D8","High");
        srapTO.setRiskOfDelay(riskOfDelay);
        kafkaService.saveToKafkaGeneric(SRAP_TOPIC, srapTO);
    }



    @PostMapping("/srap-recomend-sound-ga")
    public void mockSrapSoundGA() {
        SrapTO srapTO = new SrapTO();
        srapTO.setAbandonVessel("");
        srapTO.setSender("t");
        srapTO.setPassengerId("1");
        //
        srapTO.setStatus("Stay Check");
        srapTO.setSituationAssessment("sound ga");
        //
        srapTO.setIndividualStatus(new HashMap<>());
        srapTO.setTimeout("");
        srapTO.setMessageId("");
        srapTO.setEscapeRoutes(new HashMap<>());
        srapTO.setAbilityToCommunicate("");
        srapTO.setCriticalSystemStatus("");
        srapTO.setEffectivenessOfMitigationMeasures("");
        srapTO.setZoneId("");
        srapTO.setVesselStatus("");
        srapTO.setUrgencyOfAbandonment("");
        srapTO.setTimestamp("");
        srapTO.setStructuralIntegrity("");
        srapTO.setStatusOfPassiveContainment("");
        srapTO.setStability("");
        srapTO.setSrapModel("");
        srapTO.setSpreading("");
        srapTO.setHullStatus("");
        srapTO.setRiskOfDelay(new HashMap<>());
        srapTO.setPaxVulnerabilityOnboard("");
        srapTO.setPassengersProxToHazards("");
        srapTO.setGroupPerformance(new HashMap<>());
        Map<String, String> riskOfDelay = new HashMap<>();
//        riskOfDelay.put("Z4D9","High");
//        riskOfDelay.put("Z4D8","High");
//        riskOfDelay.put("Z1D8","High");
        srapTO.setRiskOfDelay(riskOfDelay);
        kafkaService.saveToKafkaGeneric(SRAP_TOPIC, srapTO);
    }




    @PostMapping("/srap-recomend-ga-no-congestion")
    public void srapGANoCongestion() {
        SrapTO srapTO = new SrapTO();
        srapTO.setAbandonVessel("");
        srapTO.setSender("t");
        srapTO.setPassengerId("1");
        //
        srapTO.setStatus("Stay Check");
        srapTO.setSituationAssessment("sound ga");
        //
        srapTO.setIndividualStatus(new HashMap<>());
        srapTO.setTimeout("");
        srapTO.setMessageId("");
        srapTO.setEscapeRoutes(new HashMap<>());
        srapTO.setAbilityToCommunicate("");
        srapTO.setCriticalSystemStatus("");
        srapTO.setEffectivenessOfMitigationMeasures("");
        srapTO.setZoneId("");
        srapTO.setVesselStatus("");
        srapTO.setUrgencyOfAbandonment("");
        srapTO.setTimestamp("");
        srapTO.setStructuralIntegrity("");
        srapTO.setStatusOfPassiveContainment("");
        srapTO.setStability("");
        srapTO.setSrapModel("");
        srapTO.setSpreading("");
        srapTO.setHullStatus("");
        srapTO.setRiskOfDelay(new HashMap<>());
        srapTO.setPaxVulnerabilityOnboard("");
        srapTO.setPassengersProxToHazards("");
        srapTO.setGroupPerformance(new HashMap<>());
        Map<String, String> riskOfDelay = new HashMap<>();
        riskOfDelay.put("Z4D9","Low");
        riskOfDelay.put("Z1D3","Low");
        riskOfDelay.put("Z2D4","Low");
        srapTO.setRiskOfDelay(riskOfDelay);
        kafkaService.saveToKafkaGeneric(SRAP_TOPIC, srapTO);
    }


//srap-all-areas-medium
@PostMapping("/srap-ga-all-medium")
public void srapGAAllMed() {
    SrapTO srapTO = new SrapTO();
    srapTO.setAbandonVessel("");
    srapTO.setSender("t");
    srapTO.setPassengerId("1");
    //
    srapTO.setStatus("Stay Check");
    srapTO.setSituationAssessment("sound ga");
    //
    srapTO.setIndividualStatus(new HashMap<>());
    srapTO.setTimeout("");
    srapTO.setMessageId("");
    srapTO.setEscapeRoutes(new HashMap<>());
    srapTO.setAbilityToCommunicate("");
    srapTO.setCriticalSystemStatus("");
    srapTO.setEffectivenessOfMitigationMeasures("");
    srapTO.setZoneId("");
    srapTO.setVesselStatus("");
    srapTO.setUrgencyOfAbandonment("");
    srapTO.setTimestamp("");
    srapTO.setStructuralIntegrity("");
    srapTO.setStatusOfPassiveContainment("");
    srapTO.setStability("");
    srapTO.setSrapModel("");
    srapTO.setSpreading("");
    srapTO.setHullStatus("");
    srapTO.setRiskOfDelay(new HashMap<>());
    srapTO.setPaxVulnerabilityOnboard("");
    srapTO.setPassengersProxToHazards("");
    srapTO.setGroupPerformance(new HashMap<>());
    Map<String, String> riskOfDelay = new HashMap<>();
    riskOfDelay.put("Z4D9","Moderate");
    riskOfDelay.put("Z1D3","Moderate");
    riskOfDelay.put("Z2D4","Moderate");
    srapTO.setRiskOfDelay(riskOfDelay);
    kafkaService.saveToKafkaGeneric(SRAP_TOPIC, srapTO);
}

    //T0_2
    @PostMapping({"/ec1"})
    public void ec1() {
        EvacuationCoordinator evacCoordinator = GenerateDataUtil.generateEvacuationCoordinator(
                "evacuation-coordinator", 1, DateUtil.dateToString(LocalDateTime.now()));
        kafkaService.saveToKafkaGeneric(EVAC_COORDINATOR_TOPIC, evacCoordinator);
    }
    @PostMapping({"/ec2"})
    public void ec2() {
        EvacuationCoordinator evacCoordinator = GenerateDataUtil.generateEvacuationCoordinator(
                "evacuation-coordinator", 2, DateUtil.dateToString(LocalDateTime.now()));
        kafkaService.saveToKafkaGeneric(EVAC_COORDINATOR_TOPIC, evacCoordinator);
    }

    @PostMapping({"/ec21"})
    public void ec21() {
        EvacuationCoordinator evacCoordinator = GenerateDataUtil.generateEvacuationCoordinator(
                "evacuation-coordinator", 21, DateUtil.dateToString(LocalDateTime.now()));
        kafkaService.saveToKafkaGeneric(EVAC_COORDINATOR_TOPIC, evacCoordinator);
    }
    @PostMapping({"/ec3"})
    public void ec3() {
        EvacuationCoordinator evacCoordinator = GenerateDataUtil.generateEvacuationCoordinator(
                "evacuation-coordinator", 3, DateUtil.dateToString(LocalDateTime.now()));
        kafkaService.saveToKafkaGeneric(EVAC_COORDINATOR_TOPIC, evacCoordinator);
    }
    @PostMapping({"/ec31"})
    public void ec31() {
        EvacuationCoordinator evacCoordinator = GenerateDataUtil.generateEvacuationCoordinator(
                "evacuation-coordinator", 31, DateUtil.dateToString(LocalDateTime.now()));
        kafkaService.saveToKafkaGeneric(EVAC_COORDINATOR_TOPIC, evacCoordinator);
    }
    @PostMapping({"/ec4"})
    public void ec4() {
        EvacuationCoordinator evacCoordinator = GenerateDataUtil.generateEvacuationCoordinator(
                "evacuation-coordinator", 4, DateUtil.dateToString(LocalDateTime.now()));
        kafkaService.saveToKafkaGeneric(EVAC_COORDINATOR_TOPIC, evacCoordinator);
    }
    @PostMapping({"/ec5"})
    public void ec5() {
        EvacuationCoordinator evacCoordinator = GenerateDataUtil.generateEvacuationCoordinator(
                "evacuation-coordinator", 5, DateUtil.dateToString(LocalDateTime.now()));
        kafkaService.saveToKafkaGeneric(EVAC_COORDINATOR_TOPIC, evacCoordinator);
    }





    ///////////


    @PostMapping({"/emergencyRecon", "/act1"})
    public void emergencyRecon() {
        EvacuationCoordinator evacCoordinator = GenerateDataUtil.generateEvacuationCoordinator(
                "evacuation-coordinator", 1, DateUtil.dateToString(LocalDateTime.now()));

        LegacySystem legacySystem = GenerateDataUtil.generateLecagySystemERecon("true");

        SmartSafetySystem smartSafetySystem = GenerateDataUtil.generateSmartSafetySystem(
                "Fire", DateUtil.dateToString(LocalDateTime.now()), "5", "13.72", "5.47");
        kafkaService.saveToKafkaGeneric(EVAC_COORDINATOR_TOPIC, evacCoordinator);
        kafkaService.saveToKafkaGeneric(LEGACY_TOPIC, legacySystem);
        kafkaService.saveToKafkaGeneric(SMART_SAFETY_SYSTEM_TOPIC, smartSafetySystem);
    }


    @PostMapping("/situationAssessment")
    public void situationAssessment() {
        LegacySystem legacySystem = GenerateDataUtil.generateLecagySystemSitAssessment();
        StabilityToolkit stabilityToolkit = GenerateDataUtil.generateStabilityToolkit();
        ShmAlarm shmAlarm = GenerateDataUtil.generateShmAlarm();
        SmartSafetySystem smartSafetySystem = GenerateDataUtil.generateSmartSafetySystem(
                "Fire", DateUtil.dateToString(LocalDateTime.now()), "3", "13.72", "5.47");

        kafkaService.saveToKafkaGeneric(LEGACY_TOPIC, legacySystem);
        kafkaService.saveToKafkaGeneric(STABILITY_TOOLKIT_TOPIC, stabilityToolkit);
        kafkaService.saveToKafkaGeneric(SHM_ALARM_TOPIC, shmAlarm);
        kafkaService.saveToKafkaGeneric(SMART_SAFETY_SYSTEM_TOPIC, smartSafetySystem);
    }


    @PostMapping("/musteringAssessmentFull")
    public void musteringAssessmentFull() {
        EvacuationCoordinator evacCoordinator = GenerateDataUtil.generateEvacuationCoordinator(
                "evacuation-coordinator", 2, DateUtil.dateToString(LocalDateTime.now()));
        LegacySystem legacySystem12 = GenerateDataUtil.generateLegacySystemHazard("No hazard", "True", "True", "9");
        Camera camera12 = GenerateDataUtil.generateCameraData("0/1/2",
                "camera-01", DateUtil.dateToString(LocalDateTime.now()));
        LegacySystem legacySystem3 = GenerateDataUtil.generateLegacySystemHazard("No hazard", "True", "True", "9");
        Camera camera3 = GenerateDataUtil.generateCameraData("0/1/2",
                "camera-01", DateUtil.dateToString(LocalDateTime.now()));

        kafkaService.saveToKafkaGeneric(EVAC_COORDINATOR_TOPIC, evacCoordinator);
        kafkaService.saveToKafkaGeneric(LEGACY_TOPIC, legacySystem12);
        kafkaService.saveToKafkaGeneric(CAMERAS_TOPIC, camera12);
        kafkaService.saveToKafkaGeneric(VRD_CAMERA_TOPIC, camera12);
        kafkaService.saveToKafkaGeneric(LEGACY_TOPIC, legacySystem3);
        kafkaService.saveToKafkaGeneric(CAMERAS_TOPIC, camera3);
        kafkaService.saveToKafkaGeneric(VRD_CAMERA_TOPIC, camera3);
    }


    @PostMapping({"/musteringAssessment3",})
    public void musteringAssessment3() {
        EvacuationCoordinator evacCoordinator = GenerateDataUtil.generateEvacuationCoordinator(
                "evacuation-coordinator", 2, DateUtil.dateToString(LocalDateTime.now()));
        LegacySystem legacySystem3 = GenerateDataUtil.generateLegacySystemHazard("No hazard",
                "True", "True", "9");
        Camera camera3 = GenerateDataUtil.generateCameraData("0/1/2",
                "camera-01", DateUtil.dateToString(LocalDateTime.now()));

        kafkaService.saveToKafkaGeneric(EVAC_COORDINATOR_TOPIC, evacCoordinator);
        kafkaService.saveToKafkaGeneric(LEGACY_TOPIC, legacySystem3);
        kafkaService.saveToKafkaGeneric(CAMERAS_TOPIC, camera3);
        kafkaService.saveToKafkaGeneric(VRD_CAMERA_TOPIC, camera3);
    }

    @PostMapping("/musteringUpdate")
    public void musteringUpdate() {

        LegacySystem legacySystem = GenerateDataUtil.generateLegacySystemHazard("Smoke",
                "True", "True", "7");

        kafkaService.saveToKafkaGeneric(LEGACY_TOPIC, legacySystem);
    }


    @PostMapping("/incidentManagement1")
    public void incidentManagement1() {
        LegacySystem legacySystem1 = GenerateDataUtil.generateLegacySystemHazard("Smoke",
                "True", "True", "9");

        kafkaService.saveToKafkaGeneric(LEGACY_TOPIC, legacySystem1);
    }

    @PostMapping("/incidentManagement2")
    public void incidentManagement2() {
        LegacySystem legacySystem2 = GenerateDataUtil.generateLegacySystemHazard("Smoke",
                "True", "True", "9");

        kafkaService.saveToKafkaGeneric(LEGACY_TOPIC, legacySystem2);
    }

    @PostMapping("/incidentManagementFull")
    public void incidentManagementFull() {
        incidentManagement1();
        incidentManagement2();
    }


    @PostMapping("/srapDataFull")
    public void srapDataFull() {
        emergencyRecon();
        situationAssessment();
        musteringAssessmentFull();
        musteringUpdate();
        incidentManagementFull();
        preAbandonmentAssessment();
    }

}
