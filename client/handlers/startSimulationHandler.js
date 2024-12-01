import { parseCarData } from "../utils/parseCarData.js";
import { startCar } from "../utils/carUtils.js";

export async function handleStartSimulation(jsonObject, inputs, riveInstance) {
  if (!jsonObject) {
    console.error("No JSON object loaded.");
    window.alert("No JSON object loaded.");
    return;
  }

  let carDirections;

  parseCarData(jsonObject, (obj) => (carDirections = obj));

  console.log(carDirections);

  try {
    const response = await fetch("http://localhost:8080/api/run-commands", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(jsonObject),
    });

    const data = await response.json();
    console.log(data);

    for (let vehicleList of data.leftVehicles) {
      for (let vehicle of vehicleList) {
        startCar(inputs, carDirections[vehicle]);
        console.log(`${vehicle} is going ${carDirections[vehicle]}`);
      }
      await delay(2500);
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
