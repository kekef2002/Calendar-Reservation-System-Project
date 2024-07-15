# Calendar Reservation System

This project is a JavaFX application for managing calendar reservations. It allows users to sign in, create accounts, and manage appointments. The application uses Firestore as the backend database.

## Table of Contents
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Authors](#authors)

## Prerequisites

To run this project, you need the following software installed:

- JDK 21
- SceneBuilder
- NetBeans 20 or IntelliJ IDEA
- Maven

## Installation

### Step 1: Clone the repository

```sh
git clone https://github.com/yourusername/Calendar-Reservation-System.git
cd Calendar-Reservation-System
```

### Step 2: Set up Firestore

Ensure you have a Firestore project set up and download the service account key file. Place the `key.json` file in the `resources/com/mycompany/mvvmexample` directory.

### Step 3: Build the project

Navigate to the project directory and run the following Maven command to build the project:

```sh
mvn clean install
```

### Step 4: Run the application

Use the following Maven command to run the application:

```sh
mvn javafx:run
```

## Usage

### Sign In

1. Enter your username and password.
2. Click on the "Sign up with username" button to sign in.

### Create Account

1. Click on the "Create Your Account" button on the Sign In page.
2. Fill out the registration form and click on the "Register" button.

### Managing Appointments

1. Once signed in, navigate to the Calendar View to manage your appointments.
2. Use the navigation buttons to move between months.
3. Click on a date to view or add appointments.

## Project Structure

```plaintext
Calendar-Reservation-System/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/mycompany/mvvmexample/
│   │   │       ├── App.java
│   │   │       ├── SigninController.java
│   │   │       ├── CalendarActivity.java
│   │   │       ├── CalendarViewController.java
│   │   │       ├── FirestoreContext.java
│   │   │       └── RegisterController.java
│   │   ├── resources/
│   │   │   └── com/mycompany/mvvmexample/
│   │   │       ├── Signin.fxml
│   │   │       ├── CalendarView.fxml
│   │   │       ├── Register.fxml
│   │   │       └── key.json
│   └── test/
│       └── java/
├── pom.xml
└── README.md
```

## Authors

- [Afaq136](https://github.com/Afaq136)
- [itsharryb13](https://github.com/itsharryb13)
- [Jkollar116](https://github.com/Jkollar116)
- [kekef2002](https://github.com/kekef2002)
- [nav7FSC](https://github.com/nav7FSC)
