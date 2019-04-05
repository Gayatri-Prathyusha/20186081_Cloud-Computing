#!/usr/bin/env python

import sys
import json
words = []
result = {}
for line in sys.stdin:
	words = line.strip().split(" ",1)
	
	if (words[0] in result):
		result[words[0]].append(words[1])
	else:
		result[words[0]] = []
		result[words[0]].append(words[1])

jenc = json.JSONEncoder()

for item in result:
	print(item), (result[item])
