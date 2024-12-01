package com.intelligent.trafficlights.simulation.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(classes = com.trafficlights.simulation.TrafficSimulatorApplication.class)
@AutoConfigureMockMvc
public class FileUploadControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Test
    public void testUploadJsonTypeAddVehicle() throws Exception {
        String jsonContent = """
                {
                     "commands": [
                        {
                          "type": "addVehicle",
                          "vehicleId": "vehicle1",
                          "startRoad": "south",
                          "endRoad": "north"
                        }
                        ]
                }
                """;

        MockMultipartFile file = new MockMultipartFile(
                "file",
                "commands.json",
                MediaType.APPLICATION_JSON_VALUE,
                jsonContent.getBytes()
        );

        mockMvc.perform(multipart("/upload")
                .file(file)
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray()) // No $.commands, just root array
                .andExpect(jsonPath("$[0].type").value("addVehicle"))
                .andExpect(jsonPath("$[0].vehicleId").value("vehicle1"))
                .andExpect(jsonPath("$[0].startRoad").value("south"))
                .andExpect(jsonPath("$[0].endRoad").value("north"));
    }

    @Test
    public void testUploadJsonTypeStep() throws Exception {
        String jsonContent = """
                {
                     "commands": [
                        {
                            "type": "step"
                        }
                        ]
                }
                """;

        MockMultipartFile file = new MockMultipartFile(
                "file",
                "commands.json",
                MediaType.APPLICATION_JSON_VALUE,
                jsonContent.getBytes()
        );

        mockMvc.perform(multipart("/upload")
                .file(file)
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].type").value("step"));
    }
    @Test
    public void testUploadJsonMultipleCommands() throws Exception {
        String jsonContent = """
            {
                "commands": [
                    {
                        "type": "addVehicle",
                        "vehicleId": "vehicle1",
                        "startRoad": "south",
                        "endRoad": "north"
                    },
                    {
                        "type": "step"
                    },
                    {
                        "type": "addVehicle",
                        "vehicleId": "vehicle2",
                        "startRoad": "east",
                        "endRoad": "west"
                    }
                ]
            }
            """;

        MockMultipartFile file = new MockMultipartFile(
                "file",
                "commands.json",
                MediaType.APPLICATION_JSON_VALUE,
                jsonContent.getBytes()
        );

        mockMvc.perform(multipart("/upload")
                        .file(file)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(3));
    }
}