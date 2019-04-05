#!/usr/bin/env python
import sys
 
result = {}
for line in sys.stdin:
	value = line.strip().split()
	if (value[0] in result): 
		result[value[0]].append(value[1])
	else:
		result[value[0]] = [value[1]]

for keys in result:
	print keys, len(result[keys])

