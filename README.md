# Checkout
## A Simple Credit Card Validation App

This project is designed to reduce strain on an external payment system by taking a user's credit card information and validating it before sending it to any Payment API for processing.
It is divided into two parts: Frontend and Backend. The Frontend takes in the credit card information and displays an error for incorrect entries detected by the Backend. The Backend does the validation and returns reponse to the Frontend.

The Backend is built using Springboot Web Framework and Thymeleaf which renders the Frontend with HTML and CSS for styling and jQuery for making the API request.

## Basic Steps to run and test the Project
### 1. Download or Clone the Project from Github [here](https://github.com/princejohnnie/checkout)
- Inside any folder or directory in your computer, download and unzip the git file containing the project. You can also clone the project from Github by running the following command `git clone https://github.com/princejohnnie/checkout.git` in any terminal on your computer.
### 2. Run the Application
- Open your terminal from the directory where you downloadeded or cloned the project and enter the folder named `checkout`
  
- To build the application, run the following command: `mvn clean install`.
  **NOTE:** You must have Maven and Java 11 installed in your computer to run this command.
  - If you do not have Maven installed on your computer, you can download Maven from the Apache Maven Page [here](https://maven.apache.org/download.cgi). Download the latest stable release, extract the contents of the zip file into a directory in your computer. Add the `bin` folder in the diractory to your systems's PATH variables. You can check if Maven was installed properly by running the follwing command `mvn -version` to see the version that was installed.
  - If you do not have Java 11 installed on your computer, you can download Java 11 from the Oracle JDK Page [here](https://www.oracle.com/ng/java/technologies/javase/jdk11-archive-downloads.html). Download the latest stable release for your Operating System, extract the contents of the zip file into a directory in your computer. Add the `bin` folder in the diractory to your systems's PATH variables. You can check if Java 11 was installed properly by running the follwing command `java -version` to ensure Java 11 was installed.
 
**NOTE:** Please ensure you are connected to the internet when running the command to enable Maven download the necessary dependencies.

- After the application builds successfully, run the following command `mvn spring-boot:run` to start the application. The application should start with the backend service listening on `localhost:8080`.

### 3. Test the Application
- Open your web browser and enter the URL `http://localhost:8080`. You should see the Checkout Page where you can enter your card details and click on the Proceed button to check if the details entered is correct.

Alternatively, you can clone the code from Github using any code editor that supports Java (e.g IntelliJ IDEA, VS Code), and run the file named `CheckoutApplication.java`. Then proceed with Step 3 above.

## Thank you
