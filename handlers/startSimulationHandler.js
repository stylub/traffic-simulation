import { parseCarData } from "../utils/parseCarData.js";
import { startCar } from "../utils/carUtils.js";

export async function handleStartSimulation(
  jsonObject,
  inputs,
  selectedAlgorithm,
  setSimulationResult
) {
  if (!jsonObject) {
    console.error("No JSON object loaded.");
    window.alert("No JSON object loaded.");
    return;
  }

  let carDirections;

  parseCarData(jsonObject, (obj) => (carDirections = obj));

  try {
    const response = await fetch(
      `http://localhost:8080/api/run-commands/${selectedAlgorithm}`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(jsonObject),
      }
    );
    if (!response.ok) {
      throw new Error("Network response was not ok");
    }

    const data = await response.json();

    setSimulationResult(data);

    const stepStatuses = data.stepStatuses;
    for (const step of stepStatuses) {
      for (const vehicle of step.leftVehicles) {
        startCar(inputs, carDirections[vehicle]);
      }
      if (step.leftVehicles.length > 0) await delay(2500);
    }
  } catch (error) {
    console.error("Error:", error);
  }
}

function waitForAnimationEnd(riveInstance) {
  return new Promise((resolve) => {
    const onStateChange = (event) => {
      if (event.data.some((str) => str.startsWith("stop"))) {
        riveInstance.off("statechange", onStateChange);
        resolve();
      }
    };
    riveInstance.on("statechange", onStateChange);
  });
}

function delay(ms) {
  return new Promise((resolve) => setTimeout(resolve, ms));
}
