#!/bin/bash

PROJECT=$1

if [[ -d $PROJECT ]]; then
  echo running $PROJECT...
  javac $PROJECT/src/*.java
  java -cp $PROJECT/src App
else
    echo "Directory does not exist."
fi
