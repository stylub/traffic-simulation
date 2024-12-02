## Traffic-Simulation
![image](https://github.com/user-attachments/assets/67f53011-69ec-4ec9-9d02-3640a1c5b47f)


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
