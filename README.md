### __Approach:__

- Requirement analysis of the assignment. For this, I did some whiteboarding to brainstorm around with following questions in mind:
    - __What am I supposed to do?__
    - __What architecure to follow?__
    - __Which libraries provided by Android can be used?__
    - __What would be the UI of the app__

- After the requirement analysis, based on my previous experince I decided to go with __MVVM architecture__. After this choice, I started thinking about the flow of the app in my mind and tried to map my thoughts on the white board
#
### __Implementation steps:__
1. Added the necessary libraries, plugins and classpath in the gradle files
2. Setup the MVVM architecture with Dependency Injection
3. Created the UI screen, using fragment and navigation graphs
4. Fetched the Cars from the API using Retrofit
5. Implemented channels for listening to error events 
6. Implemented swipe referesh
7. Implemented data persistence using room
8. Refactoring
#
### __Libraries__
1. Room
2. ConstraintLayout
3. Navigation Fragment
5. Retrofit 2
6. Hilt
7. Glide
8. Koltinx-Coroutines
9. Google material
#
### __References__
1. Architecture - https://developer.android.com/topic/libraries/architecture/
2. Converting the mileage value as per UI - https://stackoverflow.com/a/57488950
#
### __Comments__
- Choosing navigation fragment instead of a new activity for car details view to provide homogenous navigation to the user
- Opening the phone screen on click of "call dealer" instead of initiating a call. Because, as per google guidelines they recommend to use pre-existing apps for already available features. So, if we add the call initiation we would have to use the "CALL_PHONE" permission.

