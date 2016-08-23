# Locations

That program can calculate the distance between two locations (defined by coordinates-lon+lat-) (one given by user + a lot which can be read from a CSV file).
It's based on the haversine formula.
https://en.wikipedia.org/wiki/Haversine_formula

Program can generate geohash of these locations.
Every location (including the one which given by user) has a radius too.
It's used to know the user-given location is including the ones from the file or not(including these radius of course).

All data is calculated from a user input and a CSV file.
Now you can add your own csv file. Input form is the next:

Lat, lon, radius

If you choose a non-existing or an illegal file, default csv will be used.

Every data will be sent out into HTML tables (trough JSP) which can be accessible at localhost:8080/geohash

I'm a beginner Java-dev, it's just a training code.
