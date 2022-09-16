package gr.uAegean.mockDataGenerator.restController;

import gr.uAegean.mockDataGenerator.model.*;
import gr.uAegean.mockDataGenerator.service.KafkaService;
import gr.uAegean.mockDataGenerator.util.DateUtil;
import gr.uAegean.mockDataGenerator.util.GenerateDataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import static gr.uAegean.mockDataGenerator.util.Constants.*;

@Slf4j
@RestController
public class MockDataController {

    private KafkaService kafkaService;

    @Autowired
    MockDataController(KafkaService kafkaService){
        this.kafkaService = kafkaService;
    }

    @PostMapping("/emergencyRecon")
    public void emergencyRecon(){
        EvacuationCoordinator evacCoordinator = GenerateDataUtil.generateEvacuationCoordinator(
                "evacuation-coordinator", "1", DateUtil.dateToString(LocalDateTime.now()));
        LegacySystem legacySystem = GenerateDataUtil.generateLecagySystemERecon("true");
        SmartSafetySystem smartSafetySystem = GenerateDataUtil.generateSmartSafetySystem(
                "Fire", DateUtil.dateToString(LocalDateTime.now()), "3", "13.72", "5.47");

        kafkaService.saveToKafkaGeneric(EVAC_COORDINATOR_TOPIC, evacCoordinator);
        kafkaService.saveToKafkaGeneric(LEGACY_TOPIC, legacySystem);
        kafkaService.saveToKafkaGeneric(SMART_SAFETY_SYSTEM_TOPIC, smartSafetySystem);
    }

    @PostMapping("/situationAssessment")
    public void situationAssessment(){
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
    public void musteringAssessmentFull(){
        EvacuationCoordinator evacCoordinator = GenerateDataUtil.generateEvacuationCoordinator(
                "evacuation-coordinator", "2", DateUtil.dateToString(LocalDateTime.now()));
        LegacySystem legacySystem12 = GenerateDataUtil.generateLegacySystemHazard("No hazard", "True", "True");
        Camera camera12 = GenerateDataUtil.generateCameraData("0/1/2",
                "camera-01", DateUtil.dateToString(LocalDateTime.now()));
        LegacySystem legacySystem3 = GenerateDataUtil.generateLegacySystemHazard("No hazard", "True", "True");
        Camera camera3 = GenerateDataUtil.generateCameraData("0/1/2",
                "camera-01", DateUtil.dateToString(LocalDateTime.now()));

        kafkaService.saveToKafkaGeneric(EVAC_COORDINATOR_TOPIC, evacCoordinator);
        kafkaService.saveToKafkaGeneric(LEGACY_TOPIC, legacySystem12);
        kafkaService.saveToKafkaGeneric(CAMERAS_TOPIC, camera12);
        kafkaService.saveToKafkaGeneric(LEGACY_TOPIC, legacySystem3);
        kafkaService.saveToKafkaGeneric(CAMERAS_TOPIC, camera3);
    }

    @PostMapping("/musteringAssessment12")
    public void musteringAssessment12(){
        EvacuationCoordinator evacCoordinator = GenerateDataUtil.generateEvacuationCoordinator(
                "evacuation-coordinator", "2", DateUtil.dateToString(LocalDateTime.now()));
        LegacySystem legacySystem12 = GenerateDataUtil.generateLegacySystemHazard("No hazard",
                "True", "True");
        Camera camera12 = GenerateDataUtil.generateCameraData("0/1/2",
                "camera-01", DateUtil.dateToString(LocalDateTime.now()));

        kafkaService.saveToKafkaGeneric(EVAC_COORDINATOR_TOPIC, evacCoordinator);
        kafkaService.saveToKafkaGeneric(LEGACY_TOPIC, legacySystem12);
        kafkaService.saveToKafkaGeneric(CAMERAS_TOPIC, camera12);
    }

    @PostMapping("/musteringAssessment3")
    public void musteringAssessment3(){
        EvacuationCoordinator evacCoordinator = GenerateDataUtil.generateEvacuationCoordinator(
                "evacuation-coordinator", "2", DateUtil.dateToString(LocalDateTime.now()));
        LegacySystem legacySystem3 = GenerateDataUtil.generateLegacySystemHazard("No hazard",
                "True", "True");
        Camera camera3 = GenerateDataUtil.generateCameraData("0/1/2",
                "camera-01", DateUtil.dateToString(LocalDateTime.now()));

        kafkaService.saveToKafkaGeneric(EVAC_COORDINATOR_TOPIC, evacCoordinator);
        kafkaService.saveToKafkaGeneric(LEGACY_TOPIC, legacySystem3);
        kafkaService.saveToKafkaGeneric(CAMERAS_TOPIC, camera3);
    }

    @PostMapping("/musteringUpdate")
    public void musteringUpdate(){

        LegacySystem legacySystem = GenerateDataUtil.generateLegacySystemHazard("Smoke",
                "True", "True");

        kafkaService.saveToKafkaGeneric(LEGACY_TOPIC, legacySystem);
    }

    @PostMapping("/incidentManagement1")
    public void incidentManagement1(){
        LegacySystem legacySystem1 = GenerateDataUtil.generateLegacySystemHazard("Smoke, Fire, Water, No Hazard",
                "True", "True");

        kafkaService.saveToKafkaGeneric(LEGACY_TOPIC, legacySystem1);
    }

    @PostMapping("/incidentManagement2")
    public void incidentManagement2(){
        LegacySystem legacySystem2 = GenerateDataUtil.generateLegacySystemHazard("Smoke, Fire, Water, No Hazard",
                "True", "True");

        kafkaService.saveToKafkaGeneric(LEGACY_TOPIC, legacySystem2);
    }

    @PostMapping("/incidentManagementFull")
    public void incidentManagementFull(){
        incidentManagement1();
        incidentManagement2();
    }

    @PostMapping("/preAbandonmentAssessment")
    public void preAbandonmentAssessment(){
        LegacySystem legacySystem = GenerateDataUtil.generateLecagySystemSitAssessment();
        StabilityToolkit stabilityToolkit = GenerateDataUtil.generateStabilityToolkit();
        ShmAlarm shmAlarm = GenerateDataUtil.generateShmAlarm();
        Weather weather = GenerateDataUtil.generateWeather();

        kafkaService.saveToKafkaGeneric(LEGACY_TOPIC, legacySystem);
        kafkaService.saveToKafkaGeneric(STABILITY_TOOLKIT_TOPIC, stabilityToolkit);
        kafkaService.saveToKafkaGeneric(SHM_ALARM_TOPIC, shmAlarm);
        kafkaService.saveToKafkaGeneric(WEATHER_TOPIC, weather);
    }

    @PostMapping("/srapDataFull")
    public void srapDataFull(){
        emergencyRecon();
        situationAssessment();
        musteringAssessmentFull();
        musteringUpdate();
        incidentManagementFull();
        preAbandonmentAssessment();
    }

}
