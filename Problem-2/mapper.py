#!/usr/bin/env python
import sys
import json

intermediate = {}
def mapper(record):

	order_id = record[1]
	intermediate.setdefault(order_id, [])
	intermediate[order_id] = record
	print order_id, intermediate[order_id]

for line in sys.stdin:
	record = json.loads(line)
	mapper(record)