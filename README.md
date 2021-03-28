# Rail_IL
Backend Java project on an internal server.
The program looks for travel from a starting point to a destination that the user enters, the trips received are within the range of hours that the user searched for and if there are suitable ride in the train schedule.
The connection to the server was though Flask (Python web framework).
The ride.dat file contain a short list of trips that the main classes loads from him, and the RailProgram.class saves the new trips that will be added to this file. 

The app.py file contain the flask web server code, adjusted to this rail program.
