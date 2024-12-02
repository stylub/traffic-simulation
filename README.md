## Traffic-Simulation
![image](https://github.com/user-attachments/assets/67f53011-69ec-4ec9-9d02-3640a1c5b47f)

### Features
* Upload JSON: Upload a JSON file to configure the simulation.
  Json should be in given format
  ```json
  {
  
    "commands": [
      {
  
        "type": "addVehicle", <- add vehicle to simulation
  
        "vehicleId": "vehicle1",
  
        "startRoad": "south",
  
        "endRoad": "north"
  
      },
      {
  
        "type": "step" <- make one simulation step
  
      }
  
    ]
  
  }
  ```
  
* Start Simulation: Starts the animated traffic simulation.
  All animations were created by me using Rive. [Check out my Rive project here](https://rive.app/community/files/14955-28181-crossroad-with-traffic-lights-animation/)!
* Download Simulation Result: Download the results of the simulation in Json format.
  
* Choose Algorithm: Select the traffic algorithm:
Simple,
Round-robin,
WRR (Weighted Round Robin)

### Installation


To get started with the backend, you need to have Maven installed. Follow these steps:

* Clone the repository:
```
git clone https://github.com/stylub/traffic-simulation.git
cd traffic-simulation
cd backend
```
* Install the dependencies:
```
mvn clean install
```
* Usage
To run the backend, use the following command:
```
mvn spring-boot:run
```
To run the backend with specific input and output files:
```
mvn spring-boot:run -DrunSpringBoot='false' -DinputFile='input.json' -DoutputFile='output.json'
```

To run frontend, go to `/client` directory and run:
```
npm install
npm run dev
```
Then it should be available at `http://localhost:5173`.
