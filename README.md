# TestVagrant Interview Assignment

#### Setup

Clone the repository into your local machine using the below command

```
git clone https://github.com/madhank93/TestVagrant-assignment.git
```
#### Prerequisite
* [Maven](https://maven.apache.org/install.html)

#### Project Structure
```
- src/main/java
	- com.testvagrant.api
		 - APIHelper.java
	- com.testvagrant.comparator
		- IWeatherComparator.java
	- com.testvagrant.drivermanager
		- ChromeDriverManager.java
		- DriverFactory.java
		- FirefoxDriverManager.java
		- IDriver.java
		- InternetExplorerDriverManager.java // Not yet implemented
		- SafariDriverManager.java // Not yet implemented
	- com.testvagrant.model
		- VarianceModel.java
		- WeatherModel.java
	- com.testvagrant.pages
		- BasePage.java
		- NDTVHomePage.java
		- NDTVWeatherPage.java
	- com.testvagrant.utils
		- PropertyReader.java
		
- src/test/java
	- com.testvagrant.testcases
		- NDTVWeatherPageTest.java
		- OpenWeatherAPITest.java
		- WeatherComparator.java
		
- src/test/resources
	- comparator.properties
	- config.properties
```

#### To run the tests

In the project’s root directory, execute the below command. By default it will run in Chrome browser.

```
mvn clean test
```

To run the tests in Firefox browser, use the below command

```
mvn clean test -Dbrowser=firefox
```
To run the tests in headless mode, use the below command

```
mvn clean test -Dbrowser=chrome -Dheadless=true
```

#### Test Reports

After execution of the test suite navigate to assignment/target/surefire-reports and choose the index.html file