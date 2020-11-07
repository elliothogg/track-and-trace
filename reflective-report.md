# Reflective Report

## Brief explanation of the system

The system is presented in the form of a terminal menu. Events and establishments can be added. Before adding the system checks for duplicates. Establishment duplicates are determined by name and postcode. Events are by hashcode. When adding an event and entering the establishment, the system will only add the establishment if no duplicate is found. Results of "DB transactions" are printed in the terminal, e.g. "Sucess! Event & Establishment added to DB."

All input is thoroughly validated:
- Emails must contain an "@" followed by at least 1 ".". 
- PhoneNumbers must be 11 ints long and start with "07". 
- All ints are validated.
- Positive int validation.
- Menu input validation.

Before printing lists of Events/Establishments system checks if arrays are populated. System also prevents filters from being accessed if Event array is empty. When filtering, a "no matches" message is printed if needed.

## My thoughts/feelings before starting the coursework but after reading the specification

After reading the brief a bunch of times and understanding what was required, I had fairly mixed feelings about writing the program. I felt confident in building the event, user, and establishments classes. I also felt confident about writing the menu. One of the things I was anxious about was understanding/correctly implementing the relationship between Controller and IO. I also worried about doing things "the right way", and whether a "right way" actually existed.

## Description of what went well and what went wrong

As mentioned previously, building the event, user, and establishment classes went smoothly. I did get stumped by the String.format method for a little while, as I struggled to get multiple "format" and "args" (until revisiting the lecture!). I also struggled to parse and pass around the CSV file; this required some perseverance. Building the menu went well, however splitting it into smaller methods was kind of stressful. I originally put all my menu validation in the Controller class, however I eventually realised it should be in the IO class. I also spent a fair amount of time making regex for my validations. 

## What I could have done better in hindsight
  
The one thing I didn't do, which in hindsight cost me a lot of time, was to individually test methods. I ended up testing every change via the terminal menu, which was very time consuming. I was also manually adding an event or establishment every time I re-ran the program. I think I was anxious to constantly check the menu as I knew that’s ultimately where the program would be represented. But as stated previously, it would have been much smarter to just test individual methods!

## What I would have done differently (second time around)

If I were to make another track-and-trace, I would probably want to implement the menu as a web-based GUI. As we discussed, Java probably wouldn't be a smart way to do this, so I guess second time round I'd also use a different language. I'd also like to make the program have a front and backend, although I am fairly unsure about the details of that - it would be a pretty big challenge. 

In terms of functionality, I would add the ability to remove events and establishments. I would get more user and establishment data so that I could do more complex filtering to get more insightful data.


