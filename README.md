# Rail_IL
Backend Java project on an internal server.
The program looks for travel based on data entered by the user: point of departure, destination, and time of departure. She is looking for a suitable trip in the train schedule- reading the data from a text file. The program looks for travel within +-30 minutes of the given time.


The connection to the server was though Flask (Python web framework).
The ride.dat file contain a short list of trips that the main classes loads from him, and the RailProgram.class saves the new trips that will be added to this file. 

The app.py file contain the flask web server code, adjusted to this rail program.
