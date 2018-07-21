#!/bin/bash
if [ "$#" -lt 1 ]; then
    echo "Usage: start_server.sh [benchmark dir]"
    echo ""
    echo "Takes a benchmark directory and start a bayou server"
	exit 1
fi 

if [ ! -d "$1" ]; then
	echo "directory does not exist: $1"
	exit 1
fi 

if [ ! -d "$1/model" ]; then
	echo "model directory does not exist"
	exit 1
fi 

tool_files/build_scripts/out/start_bayou.sh $1
