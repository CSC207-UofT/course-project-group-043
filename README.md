## Some Notes

1. If the program isn't running when you open it in IntelliJ, try restarting IntelliJ. The way we set up Gradle will sometimes cause errors that can be fixed with a restart.
2. Included here is a link to our design document as a Google doc, should you find it more helpful to navigate the document with the benefit of ctrl + f and the "Show document outline" button: https://docs.google.com/document/d/19hK5dvGza2-pZYIuf9BmuhunkO_JWbDyyCrBTPE2U-Y/edit?usp=sharing
3. The above link to our Google doc may also be useful for clicking through the links we have included to a video and pull requests. However, we have also uploaded the video here for convenience. It is named "zoom_0.mp4".

In the interest of full disclosure, these notes have been added after the end of Wednesday December 8th, and we do not expect them to add to our mark. We wish only to make going through our design document slightly more convenient where we can. No content in the document has been changed since our proper submission at midnight. Note also that while the video was uploaded to GitHub after midnight, the link to the video has always been in the design document, which was submitted on time.

## Specification

Our program is a scheduling and social application targeted towards University of Toronto students. Students can create an account for themself, which has a unique associated username and password. Once logged in, they are able to input their own schedule and add friends. They can add events to their own schedule, specifying whether an event is for a class, for a social event, a fitness event, or other academic events. This schedule gets saved so that a user can later log in on a different device and still access their schedule.

The program will show the user’s schedule on a calendar on the app's main page. Users can send friend requests to other users, and can accept or decline incoming friend requests from others. Once friends with someone, their schedules can be “shared” with each other. By sharing their schedules, users can see what times others are not available, in order to help them more easily figure out when they can meet with each other. The compare schedules screen shows only colour blocks showing unavailable times.



