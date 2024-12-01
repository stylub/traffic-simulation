import * as rive from "@rive-app/canvas";
import { handleJsonUpload } from "./handlers/jsonUploadHandler.js";
import { handleStartSimulation } from "./handlers/startSimulationHandler.js";
import { resizeCanvas } from "./utils/canvasUtils.js";

const jsonFile = document.getElementById("file");
const startSimulation = document.getElementById("start-btn");
const fileLabel = document.getElementById("file-label");

let jsonObject;
let inputs;

jsonFile.addEventListener("change", (e) => {
  handleJsonUpload(jsonFile, (obj) => (jsonObject = obj))(e);
  const fileName = e.target.files[0].name;
  fileLabel.textContent = fileName;
});

const canvas = document.getElementById("rive-canvas");
const body = document.querySelector(".content");

const riveInstance = new rive.Rive({
  src: "traffic0.riv",
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
      await handleStartSimulation(jsonObject, inputs, riveInstance);
    };
  },
  onStateChange: (event) => {
    console.log(event.data);
  },
});
