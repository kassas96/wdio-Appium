# wdio-Appium

## 🎥 Demo
https://github.com/user-attachments/assets/09e891ea-c147-4584-b4c1-170bee42c06e
# WDIO Demo App Automation Framework

## 📝 Overview
This project automates test scenarios for the WDIO demo Android app using **Appium with Java** and follows **Page Object Model (POM)** design pattern. The framework is designed to run on an 8-inch emulator in portrait mode.

## 🚀 Features
- **Page Object Model** design for maintainability
- **Data-driven** approach (no hard-coded values)
- **Reusable components** and utility classes
- **Comprehensive test coverage** for:
  - User registration
  - Login flow
  - Forms interaction
  - Swipe gestures

## 📁 Project Structure
```
Foodics_Appium_Task/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── pages/          # Page classes
│   │   │   └──  utils/         # Utility classes (Actions and DataReaders)
│   │   │  
│   │   └── resources/         # Configuration files
│   └── test/
│       ├── java/              # Test classes and Base test class
│       └── resources/         # Test data and APK
├── testng.xml                 # TestNG configuration
└── pom.xml                    # Maven dependencies
```

## ⚙️ Prerequisites
- Java 21
- Maven 3.6.0+
- Appium Server 1.22.0+
- Android SDK
- 8-inch Android Emulator (API 30+ recommended)

## 🛠️ Setup

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/Foodics_Appium_Task.git
cd Foodics_Appium_Task
```

### 2. Install Dependencies
```bash
mvn clean install
```

### 3. Configure Environment
1. Update `src/main/resources/config.properties` with your device details:
   ```properties
   deviceName=8-inch-Tablet
   appiumURL=http://127.0.0.1:4723/wd/hub
   appPath=/src/test/resources/android.wdio.native.app.v1.0.8.apk
   ```

2. Place your APK file in `src/test/resources/`

## 🧪 Running Tests
### Option 1: Run all tests
```bash
mvn test
```

### Option 2: Run specific test suite
```bash
mvn test -DsuiteXmlFile=testng.xml
```

## 🔧 Key Components
### Page Classes
- `LoginPage.java` - Handles login screen interactions
- `SignUpPage.java` - Manages user registration
- `FormsPage.java` - Covers form field validations
- `SwipePage.java` - Implements swipe gestures

### Utilities
- `MobileActions.java` - Reusable mobile interactions
- `WaitHelper.java` - Custom wait implementations
- `DataReader.java` - Handles test data from JSON

## 📊 Test Scenarios Covered
1. **User Registration Flow**
   - Navigate to sign-up
   - Fill registration form
   - Verify successful sign-up

2. **Login Validation**
   - Enter credentials
   - Verify successful login

3. **Forms Interaction**
   - Text input validation
   - Switch toggle test
   - Dropdown selection
   - Active button verification

4. **Swipe Gestures**
   - Horizontal swipe to locate "SUPPORT VIDEOS" card

## 📌 Important Notes
- Runs on **8-inch emulator** in **portrait mode**
- Test data is stored in `src/test/resources/testData.json`
- All dependencies are bundled in the project

