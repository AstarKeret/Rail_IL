import subprocess

from flask import Flask, request

app = Flask(__name__)

@app.route("/rail")
def rail_schedule():

    if 'outformat' in request.args:
        outformat = request.args.get('outformat')
    else:
        outformat = "html"

    if 'hour' in request.args:
        hour = request.args.get('hour')
    else:
        hour = "06"

    if 'minute' in request.args:
        minute = request.args.get('minute')
    else:
        minute = "00"

    if 'destination' in request.args:
        destination = request.args.get('destination')

    if 'departure' in request.args:
        departure = request.args.get('departure')

    return subprocess.check_output(["java", "-classpath", "/home/afeka/eclipse-workspace/Rail_IL/bin",
                                    "rail_il.UserSearch", departure, hour, minute, destination, outformat])

if __name__ == '__main__':
    app.run()

