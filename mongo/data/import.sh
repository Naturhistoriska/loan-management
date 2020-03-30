#! /bin/bash

mongoimport --host mongo --port 27017 --db loans --mode upsert --type json --file /data/counters.json  --jsonArray
mongoimport --host mongo --port 27017 --db loans --mode upsert --type json --file /data/loan.json  --jsonArray
mongoimport --host mongo --port 27017 --db loans --mode upsert --type json --file /data/collection.json  --jsonArray
