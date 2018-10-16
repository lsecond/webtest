import requests

import json

response = requests.get("http://api.thescore.com/meta/leagues");
jsonResponse = json.loads(response.text);
print jsonResponse
print jsonResponse["leagues"]["more"]



# read from request and put in a file.
with open("league.json", 'w') as  write_file:
    write_file.write(response.content)




# read from file and put in a object
with open("league.json" , "r") as read_file:
    jsonFromFile = json.load(read_file)

print(jsonFromFile)

print jsonFromFile["leagues"]["show"]

for league in jsonFromFile["leagues"]["show"]:
    print league["subscription_count"]






# from json string to python object( )
x =  '{ "name":"John", "age":30, "city":"New York"}'
y = json.loads(x)
print (y["age"])


x = {
    "name": "John",
    "age": 30,
    "city": "New York"
}

# convert into JSON:
y = json.dumps(x)
y = json.dumps(x, indent=4)
# the result is a JSON string:
print(y)