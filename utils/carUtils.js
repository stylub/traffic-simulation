export function startCar(inputs, direction) {
  let car = inputs.find((input) => input.name === direction);
  car.fire();
}
