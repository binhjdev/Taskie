# **Taskie**

### **Purpose**
To show possibilities and good practices using Kotlin language.

### **Description**
Application connects to Taskie Rest API to get response data:

* Register user
* Login user
* Create task
* Get List task
* Delete task
* Get User Info

Click register button to create user info, send request to server

Click button login with the user info registed, send request to server

Click button create task, send request to server

Use swipe-down gesture to refresh get data.

Click on top-right button to get user info

Click long item to delete a task to list then send request to server

### **Libraries/concepts used**

* MVP architecture
* Retrofit - for networking
* Gson converter - for JSON parsing
* Room - database
* Koin - for service locator pattern implementation
* Android Architecture Components (ViewModel classes)
* Kotlin coroutines - to manage threads gracefully
* Kotlin View Binding - to ease connection with XML code
