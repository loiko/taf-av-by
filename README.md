# Autotests for a website av.by

* The project is developed to cover the site with UI and API autotests
* The UI tests are designed according to the Page Object Model (ROM) and PageFactory patterns to easily support the tests in case of changes in the site code
* Tests can be run locally or using Jenkins

## Technologies used

<a href="https://www.java.com/"><img src="/img/icons/java.svg" height="40"></a>
<a href="https://maven.apache.org/"><img src="/img/icons/maven.svg" height="40"></a>
<a href="https://www.jetbrains.com/idea/"><img src="/img/icons/intellij-idea.svg" height="40"></a>
<a href="https://www.selenium.dev/"><img src="/img/icons/selenium.svg" height="40"></a>
<a href="https://junit.org/"><img src="/img/icons/junit5.svg" height="40"></a>
<a href="https://www.jenkins.io/"><img src="/img/icons/jenkins.svg" height="40"></a>
<a href="https://docs.qameta.io/allure/"><img src="/img/icons/allure.svg" height="40"></a>
<a href="https://rest-assured.io/"><img src="/img/icons/rest-assured.png" height="40"></a>

## Deploying Jenkins
To deploy Jenkins locally in Docker:
1. Download and install [Docker Desktop](https://www.docker.com/products/docker-desktop/)
2. Build a custom Jenkins image from the directory where the Dockerfile is located using the docker build command
```bash
docker build . -t myjenkins
```
3. Run image
```bash
docker run -d --name jenkins -p 8080:8080 -p 50000:50000 -v jenkins_home:/var/jenkins_home myjenkins
```
4. Make sure that the container named "jenkins" is running using the docker command 
```bash
docker ps
```
5. You can get information about the container using the command 
```bash
docker inspect jenkins
```
6. Jenkins is available at 
```bash
http://localhost:8080/
```
7. To use the admin/password from the initialAdminPassword file for the first login, which can be viewed using the docker command 
```bash
docker exec cat var/jenkins_home/secrets/initialAdminPassword
```

## Configuring Jenkins
Installation of the Allure Jenkins Plugin is required

To install the plugin:
1. On the main page, in the left menu, select Manage Jenkins
2. On the page that opens, in the System Configuration block, click Manage Plugins
3. On the Plugin Manager page, select Available, enter the name of the plugin in the search bar and install a checkbox next to it
4. After selecting all the necessary plugins, click on Install without restart


## Configuring the build
1. On the main page, in the left menu, click New Item
2. Enter the name of the project, select the Freestyle project and click OK
3. On the Configuration page, configure the project settings. The project uses the following configuration:
4. Source Code Management -> **Git** (setting up a repository with source code):
   - Repository URL = https://github.com/loiko/taf-av-by.git
   - Credentials → jenkins/******
   - Branches to build = */main
5. Build Steps → **Invoke top-level Maven targets**:
   - Maven Version → Maven395
   - Goals = tests
6. Post-build Actions → **Allure Report**:
   - Path = target/allure-results
7. Click **Save**

## To run autotests in Jenkins:
1. Open a project
2. Click **Build now**
3. The result of running the build can be viewed in the Allure report

![This is an image](/img/screenshots/jenkins.jpg)

## Local launch of autotests
To run the project locally, install [Maven](https://maven.apache.org/download.cgi) and [Java](https://www.java.com/en/download/) to the local machine, enter the following commands in the console:

```bash
mvn clean test
```

```bash
allure:install
```
```bash
mvn allure:report
``````
```bash
mvn allure:serve
``````

## Sample report
After completing the autotests, the following report will open in the browser:

![This is an image](/img/screenshots/allure-report.jpg)
