# Automated Fare and Expense Management System
This is an integrated project to automate and manage the travel expenses for public mode of transportation. This project has three components - `Fare Management Server`, `Frontend Application` and `Hardware Integration using Raspberry PI`.

## Working
The Raspberry PI with integrated RFID chips will be installed in every public vehicle. The user can load money to their card through `Frontend Application` and can directly use the same card to pay their fare expenses. The RFID cards will be allocated to all the travellers each having `unique id` embedded for a particular card.When a user swipes a card to the RFID machine the request goes to `Fare Management Server` and it automatically logs the user transaction details, vehicle details, user route details, fare details and the desired fare gets transacted autmatically from the card. All these details are visible to the user and can be seen by logging into their personal account through the `Frontend Application` provided to them. This frontend application interacts with the `Fare Management Server` to get all the user details based upon the request and generating analytical reports. The user can see his/her travel history, expenses, frequency of travel, average spent on fares etc using this application.
## Backend Server
This repository contains the backend server code for automated fare and expense management system. The backend server contains all the RESTful API's to create users, log the fare details, fetch travel history, create travel routes, register rfid cards, tag cards and generate predictive analytical reports relating to their fare expenses.

### Requried Project Dependencies
1. JDK 1.8
2. Maven 3.x
3. A working Google Account supporting firebase and analytics services.

`Note: Maven will automatically fetch all the required application dependencies to build the project.`
### Running the Application
1. Clone the repository.
2. Setup the Google analytics and firebase properties in the following application configuration files: `src/main/resources/frts-63acc-firebase-adminsdk-x16ek-ef89532dd2.json` and `src/main/resources/application.properties`.
3. After setting the properties, run the following command to build the project: `mvn clean package`.
4. After building the project, run the following command to run the server: `java -jar ./target/<built-jar-name>.war`.
