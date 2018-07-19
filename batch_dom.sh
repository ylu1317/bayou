#!/bin/bash
if [ "$#" -lt 1 ]; then
    echo "Usage: batch_dom.sh [dir path]"
    echo ""
    echo "Takes a directory containing Java programs and use batch dom driver on them"
	exit 1
fi 


echo "Merging..."
find $1 -type f -name "*.java.json" > "$1_json.txt"
if [ ! -f "$1_json.txt" ]; then
	echo "Failed to merge."
	exit 1
fi 
python src/main/python/scripts/merge.py --output_file "$1.json" "$1_json.txt"
exit 1

if [ ! -d "$1" ]; then
	echo "directory does not exist: $1"
	exit 1
fi 

echo "Generating file list..."
find $1 -type f -name "*.java" > "$1.txt"
if [ ! -f "$1.txt" ]; then
	echo "Failed to generate file list..."
	exit 1
fi 

echo "Generating config file..."
python prepare_conf.py $1
if [ ! -f "$1.conf" ]; then
	echo "Failed to generate config file..."
	exit 1
fi 

echo "Running dom driver..."
java -jar tool_files/maven_3_3_9/batch_dom_driver/target/batch_dom_driver-1.0-jar-with-dependencies.jar "$1.txt" "$1.conf"
