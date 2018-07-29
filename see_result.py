import sys;
import json;

if len(sys.argv) < 2:
    sys.exit(
    """
    Please provide a filename containing bayou synthesis result JSON.
    This program will print the programs out.
    """);

filename = sys.argv[1];
data = json.loads(open(filename).read());
for p in data:
    print p;
