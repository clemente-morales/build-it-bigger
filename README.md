# build-it-bigger
 Application with multiple flavors that uses multiple libraries and Google Cloud Endpoints.

##Include the following features
* **Banner**
* **Java library to provide jokes**
* **Android library to display the jokes**
* **Google Cloud Endpoint backend module to expose the jokes remotely**
* **Functional tests and local unit tests**
* **2 Flavors, paid and free**. Paid flavor allows the user to select the category to retrieve a random joke.


## Deploy the joke service module to App engine
This project requires a project id in the **_app/src/main/assets/configuration.properties_** file.

This id is generated once we create a project in the developers console to deploy the backend server
to App Engine. [Read more](https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints).
