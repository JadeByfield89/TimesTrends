# TimesTrends
App that shows the most popular NYT articles. Uses MVVM, Coroutines, Flow, Hilt, Compose, Coil


# Overview
I really enjoyed working on this project! I decided to go with the New York Times API because it was very easy to work with and I thought it would be cool if the app could 
also be informative and practical. Like a little newspaper in your pocket.

# Architecture
I chose MVVM here because I'm a big fan of it, and it's well known and supported across the industry. Whenever I start a new project I always try to think of the future, and in
particular two scenarios:

1) Will I understand this code 6 months from now?
2) If someone else joins the team, how easy or difficult will it be to get them up to speed on how the app works and is structured?

These questions help me to keep things practical, adhere to best practices, and avoid unncessary complexity wherever possible.

It is common to see another layer of abstraction between the ViewModel and data layer, such as a Repository. I've worked with projects that had such a structure but I thought that
for this particular use case, it wasn't really neccessary. 

# Compose

When working in a client services environment, it can be difficult for engineers to stay abreast of all the latest and greatest tools while also ensuring that high quality, performant 
apps are delivered and that deadlines are met. That being said, I jumped into Compose last year and haven't looked back since. I absolutely love it and believe that it is now at a stable 
enough place to be the defacto UI standard on Android. Looking forward to continue working with it and improving in this area in particular.

<p align="center">
<img width="466" alt="Screen Shot 2023-06-08 at 12 15 32 AM" src="https://github.com/JadeByfield89/TimesTrends/assets/7025946/e75e1624-0547-4c17-acca-16682181b6ba">
<img width="465" alt="Screen Shot 2023-06-08 at 12 15 08 AM" src="https://github.com/JadeByfield89/TimesTrends/assets/7025946/2d4ea29b-3ec3-4c03-992f-d9c7778435d6">
</p>

# Testing

Unit and UI tests can be found in the appropriate packages. I kept things simple here to demonstrate the main cases that I would be looking to cover in a simple app of this kind. We want to 
make sure that the data that we fetch is returned in a predictable an expected way. We want to ensure that in the case of an error, we are handling things in a controlled and seamless manner as well.

# API KEY
I have shared the Times API key via email. The project is configured to read this field from the 'gradle.properties' file in the root directory of the project. Please add the api key to that file
in the format:

API_KEY=123w5oj345353233234

Sync the gradle project files you should be all set. Please reach out to me if there are any questions, comments, concerns and especially issues with setting things up. Happy to help!


