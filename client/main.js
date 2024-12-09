import * as rive from "@rive-app/canvas";
import { handleJsonUpload } from "./handlers/jsonUploadHandler.js";
import { handleStartSimulation } from "./handlers/startSimulationHandler.js";
import { resizeCanvas } from "./utils/canvasUtils.js";
import { handleDownload } from "./handlers/downloadHandler.js";

const jsonFile = document.getElementById("file");
const startSimulation = document.getElementById("start-btn");
const fileLabel = document.getElementById("file-label");
const download = document.getElementById("download-btn");

let jsonObject;
let inputs;
let fileName;

jsonFile.addEventListener("change", (e) => {
  handleJsonUpload(jsonFile, (obj) => (jsonObject = obj))(e);
  fileName = e.target.files[0].name;
  fileLabel.textContent = fileName;
});

const canvas = document.getElementById("rive-canvas");
const body = document.querySelector(".content");

function getSelectedOption() {
  const selectedOption = document.querySelector(".option-box li.selected");
  return selectedOption ? selectedOption.textContent : null;
}

document.querySelectorAll(".option-box li").forEach((item) => {
  item.addEventListener("click", () => {
    document
      .querySelectorAll(".option-box li")
      .forEach((li) => li.classList.remove("selected"));
    item.classList.add("selected");
  });
});

let simulationResult;

const riveInstance = new rive.Rive({
  src: "client/public/traffic0.riv",
  canvas: canvas,
  autoplay: true,
  artboard: "Artboard",
  stateMachines: "State Machine 1",
  onLoad: () => {
    riveInstance.resizeDrawingSurfaceToCanvas();
    inputs = riveInstance.stateMachineInputs("State Machine 1");

    window.addEventListener("resize", () =>
      resizeCanvas(canvas, body, riveInstance)
    );

    startSimulation.onclick = async (e) => {
      e.preventDefault();
      const selectedAlgorithm = getSelectedOption();
      await handleStartSimulation(
        jsonObject,
        inputs,
        selectedAlgorithm,
        (data) => (simulationResult = data)
      );
    };
  },
});

download.onclick = async (e) => {
  if (typeof simulationResult !== "undefined" && simulationResult !== null) {
    const fileNameWithoutExtension = fileName.split(".")[0];
    handleDownload(simulationResult, `${fileNameWithoutExtension}-output.json`);
  } else {
    window.alert("No simulation result to download");
  }
};
