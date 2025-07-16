# WDIO Demo App Automation Framework

## 🎥 Demo Videos
### Test Execution on Different Devices
- **Samsung S10+ (Passed)**: 


https://github.com/user-attachments/assets/5b9a2e61-43a0-4cdd-b871-246fb0f3849e


- **8-inch Tablet (Failed)**:
  

https://github.com/user-attachments/assets/bfbb5690-8358-496d-9ad6-55a523967025



***Note** The swipe test fails on the tablet due to a known issue with the card as it is trimed and it's content is not desplayed, however i can get the card by its icon xPath and its index <(//*[contains(@resource-id,'icon')])[3]> but it's not right to do so*

## 📝 Overview
This project automates test scenarios for the WDIO demo Android app using **Appium with Java** and follows **Page Object Model (POM)** design pattern. The framework is designed to run on both 8-inch emulators and real devices.

## 🚀 Features
- **Page Object Model** design for maintainability
- **Data-driven** approach (no hard-coded values)
- **Reusable components** and utility classes
- **Allure Reporting** with screenshots and videos
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
- Real Device Samsung S10+ (for full compatibility)
- 8-inch Android Emulator (API 30+ recommended)
- Allure Commandline (for report generation)

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
   platformVersion=14
   ```

2. Place your APK file in `src/test/resources/`

## 🧪 Running Tests
###  Run all tests
```bash
mvn clean test
```

### Generate Allure Report
```bash
mvn allure:report
```

### View Report Locally
```bash
mvn allure:serve
```

## 🔧 Key Components
### Page Classes
- `LoginPage.java` - Handles login screen interactions
- `SignUpPage.java` - Manages user registration
- `FormsPage.java` - Covers form field validations
- `SwipePage.java` - Implements swipe gestures (with device-specific handling)

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
   - *Note: Known rendering issue on 8-inch tablets*


## 📌 Important Notes

### Device Compatibility:
- ✅ **All tests pass on Samsung S10+**
- ⚠️ **Swipe test fails on 8-inch tablet** due to card rendering issue
- 📂 **Test Data**: Stored in `src/test/resources/testData.json`

### Version Control
- 🔀 **Git Integration**: Project uses Git for version control

### Reporting:
- 📊 **Allure reports** include screenshots on failure
- 🎥 Videos of test execution available in [Demo Section]
- 📦 **Dependencies**: All bundled in the project

## Known Issues

### Tablet Swipe Test Failure:
```text
The "SUPPORT VIDEOS" card doesn't render properly on 8-inch tablets
