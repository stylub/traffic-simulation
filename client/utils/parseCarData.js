export function parseCarData(jsonObject, setCarDirections) {
  let carDirections = {};

  jsonObject.commands.forEach((element) => {
    if (element.type === "addVehicle") {
      carDirections[
        element.vehicleId
      ] = `${element.startRoad}-${element.endRoad}`;
    }
  });

  setCarDirections(carDirections);
}
