#!/usr/bin/env python
import sys
import ast

order = {}
line_item = {}
result = {}
for line in sys.stdin:
	# print(line)
	value = line.strip().split(" ", 1)

	record = eval(value[1])

	if (record[0] == "order"):
		order[value[0]] = record
	elif (record[0] == "line_item"):
		if (value[0] in line_item):
			line_item[value[0]].append(record)
		else:
			line_item[value[0]] = [record]

i = 1
for key in line_item:
	for value in line_item[key]:
		result[i] = order[key] + value
		i += 1
	
for keys in result:
 	print result[keys]