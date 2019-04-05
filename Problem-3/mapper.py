#!/usr/bin/env python
import sys
import json

def mapper(record):
	print record[0], record[1]

for line in sys.stdin:
	record = json.loads(line)
	mapper(record)
