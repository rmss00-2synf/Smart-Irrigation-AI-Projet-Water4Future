# WaterGuardians: Smart Irrigation System

This project implements an intelligent irrigation system designed to optimize water usage in agriculture. It combines a Spring Boot backend with an Angular frontend to provide a user-friendly interface for managing plots and irrigation schedules.

**Inspired by the need for efficient and sustainable agriculture in developing countries like Guinea, this project aims to provide a practical solution for farmers to improve their yields while conserving precious water resources.**

## Key Features:

*   **Plot Management:** Add, edit, and view details of individual plots, including location, area, owner, and crop type.
*   **Irrigation Scheduling:** Define irrigation rates and water amounts based on crop type.
*   **Simulated Sensor Data:** Provides a simulated environment for testing irrigation logic without the need for physical sensors.
*   **Automated Irrigation:** Simulates automatic irrigation cycles based on a basic threshold.
*   **Basic Irrigation History:** Track irrigation transactions (status, trials, date).
*   **Alerts:** Receive alerts when irrigation fails after a set number of trials.
*   **User-Friendly Interface:** An intuitive Angular frontend for easy navigation and management.

## Technology Stack:

*   **Backend:** Java, Spring Boot
*   **Frontend:** Angular
*   **Database:** Postgres

## Getting Started:

## Clone the project:

**Clone**
 ```bash
    git clone https://github.com/rmss00-2synf/Smart-Irrigation-AI-Projet-Water4Future.git
    cd Smart-Irrigation-AI-Projet-Water4Future
```

### Backend:

1.  **Prerequisites:**

    *   JDK 17
    *   Maven 4
2.  **Build:**

    ```bash
    cd Automatic-Irrigation-System
    mvn clean install
    ```
3.  **Run:**

    *   Using the command line: `java -jar target/automatic-irrigation-system-0.0.1-SNAPSHOT.jar`
    *   Alternatively, execute the `main` method in `com.bankmisr.AutomaticIrrigationSystemApplication` class from your IDE.
    *   Or use the Spring Boot Maven plugin: `mvn spring-boot:run`
4.  **Data Seeding:**

    *   The application is initiated with mock data. `com/bankmisr/data/dataseed/IrrigationDataLoader.java` will run at server initialization to seed the data for basic use cases.
### Front-end:

1.  **Prerequisites:**
    *   Node.js
    *   npm
2.  **Clone:**
      ```bash
      cd irrigation-system-portal
      npm install
      ```
3.  **Serve:**

    ```bash
    ng serve
    ```

    Navigate to `http://localhost:4200/`.

## Project Structure:

### Backend:

*   **`com.bankmisr.controller`:** REST controllers.
*   **`com.bankmisr.service`:** Business logic and schedulers.
*   **`com.bankmisr.repository`:** Spring Data repositories.
*   **`com/bankmisr/data/dataseed/IrrigationDataLoader.java`:**  Mock data initialization.

### Frontend:

*   This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 13.1.2.

## Assumptions:

*   Each plot has a simulated sensor for availability.
*   Irrigation cycle runs every 5 minutes (for testing).
*   Irrigation rates can be a minimum of every 5 minutes.

## Data Model:

![Data Model](./Automatic-Irrigation-System//src/main/resources/static/AIS_datamodel.PNG?raw=true "Data Model")

## How It Works:

1.  The system checks every 5 minutes for irrigation transactions that should happen now or in the last 5 minutes.
2.  If the sensor is available, irrigation is executed, the status is updated to success, and the next irrigation date is updated.
3.  If the sensor is unavailable, irrigation is not executed, the status is updated to failed, and the irrigation trial count is incremented.
4.  The system retries failed transactions (up to 3 trials).
5.  Alerts are triggered when irrigation fails for 3 consecutive times.

## Use Cases:

*   **Test Case 1:** A plot planted with tomatoes is successfully irrigated every 5 minutes when the sensor is available.
*   **Test Case 2:** A plot planted with sugarcane fails to irrigate because the sensor is unavailable, and an alert is triggered after multiple failed attempts.
*   **Test Case 3:** After failing due to an unavailable sensor, a plot planted with wheat is successfully irrigated once the sensor becomes available, and future irrigation is scheduled normally.

## Future Enhancements:

*   **Integrate Real Sensor Data:** Connect the system to real-world soil moisture and temperature sensors.
*   **Implement AI-Powered Irrigation:** Use machine learning to predict water needs based on environmental conditions and crop types.
*   **Develop a Robust User Interface:** Enhance the Angular frontend with more features and better styling.
*   **Optimize Water Consumption:** Implement algorithms to minimize water waste.
