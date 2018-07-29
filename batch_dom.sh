#!/bin/bash
if [ "$#" -lt 1 ]; then
    echo "Usage: batch_dom.sh [dir path]"
    echo ""
    echo "Takes a directory containing Java programs and use batch dom driver on them"
	exit 1
fi 

echo "Training..."
python3 src/main/python/bayou/models/low_level_evidences/train.py --config train.conf --save "$1/model" "$1/$1.train" > "$1/train.out"
exit 1

if [ ! -d "$1" ]; then
	echo "directory does not exist: $1"
	exit 1
fi 

echo "Generating file list..."
find $1 -type f -name "*.java" > "$1/$1.txt"
if [ ! -f "$1/$1.txt" ]; then
	echo "Failed to generate file list..."
	exit 1
fi 

echo "Generating config file..."
python3 prepare_conf.py $1
if [ ! -f "$1/$1.conf" ]; then
	echo "Failed to generate config file..."
	exit 1
fi 

echo "Running dom driver..."
java -jar tool_files/maven_3_3_9/batch_dom_driver/target/batch_dom_driver-1.0-jar-with-dependencies.jar "$1/$1.txt" "$1/$1.conf"

echo "Merging..."
find $1 -type f -name "*.java.json" > "$1/$1_json.txt"
if [ ! -f "$1/$1_json.txt" ]; then
	echo "Failed to find json files."
	exit 1
fi 
python3 src/main/python/scripts/merge.py --output_file "$1/$1.json" "$1/$1_json.txt"
if [ ! -f "$1/$1.json" ]; then
	echo "Failed to merge."
	exit 1
fi 

echo "Extracting evidence..."
python3 src/main/python/scripts/evidence_extractor.py "$1/$1.json" "$1/$1.train"
if [ ! -f "$1/$1.train" ]; then
	echo "Failed to train."
	exit 1
fi 

