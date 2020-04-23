#!/bin/bash

# Paths
SRC_PATH="src/"
RESOURCES="main/resources/" # must be in the src/ folder
OUT_PATH="out/"
DOC_PATH="doc/"

# Commands
BUILD="build"
BUILD_DESC="Build all .class files"
RUN="run"
RUN_DESC="Run the main .class file"
BUILD_JAR="build-jar"
BUILD_JAR_DESC="Build the JAR file"
DOC="doc"
DOC_DESC="Create all javadoc"
CLEAN="clean"
CLEAN_DESC="Clean the current repository from all created files"
HELP="help"
HELP_DESC="Display the help menu"

# Files list
declare -a CLASSES=(
  "main/java/fr/alexandreladriere/shortestpath/ShortestPath.java"
  "main/java/fr/alexandreladriere/shortestpath/utils/Colors.java"
  "main/java/fr/alexandreladriere/shortestpath/utils/Constants.java"
  "main/java/fr/alexandreladriere/shortestpath/utils/Strings.java"
  "main/java/fr/alexandreladriere/shortestpath/models/bfs/BreadthFirstSearch.java"
  "main/java/fr/alexandreladriere/shortestpath/models/bfs/Node.java"
  "main/java/fr/alexandreladriere/shortestpath/gui/Case.java"
  "main/java/fr/alexandreladriere/shortestpath/gui/Controller.java"
  "main/java/fr/alexandreladriere/shortestpath/gui/MouseController.java"
  "main/java/fr/alexandreladriere/shortestpath/gui/Popup.java"
  "main/java/fr/alexandreladriere/shortestpath/gui/Gui.java"
)

# Main class name (without extension)
MAIN="main/java/fr/alexandreladriere/shortestpath/ShortestPath"

CLASS_STR=""

function build() {
  # Builds all .class files which are in the $CLASSES array into the $OUT_PATH folder
  echo "Building .class files..."
  mkdir -p $OUT_PATH
	cd $SRC_PATH || exit
	echo "cd $SRC_PATH"
  for i in "${CLASSES[@]}"
  do
    echo "javac -d ../$OUT_PATH ""$i"""
    javac -d ../${OUT_PATH} "$i"
  done
  cp -avr --parent $RESOURCES ../${OUT_PATH} # copy resources to the destination folder
  echo "cd .."
  cd ..
  echo "All .class files have been built !"
}

function run() {
  # Runs the $MAIN class
  if [ ! -d "$OUT_PATH" ]; then # checks if $OUT_PATH exists
    echo "$OUT_PATH does not exist"
    build
  fi
  if [ ! -f "${OUT_PATH}${MAIN}.class" ]; then # checks if $MAIN.class file exists
    echo "${MAIN}.class does not exist"
    build
  fi
  echo "Running $MAIN..."
  cd $OUT_PATH || exit
  echo "cd $OUT_PATH"
  java $MAIN
  echo "cd .."
  cd ..
  exit
}

function make_class_string() {
  # transforms the list of all java files to a single string with .class extension for each file)
  for file in "${CLASSES[@]}"
  do
    tmp="${file%.*}.class" # Replace ".java" by ".class"
    CLASS_STR="${CLASS_STR} $tmp"
  done
}

function build_jar() {
  # Builds the .jar file
  if [ ! -d "$OUT_PATH" ]; then # checks if $OUT_PATH exists
    echo "$OUT_PATH does not exist"
    build
  fi
  if [ ! -f "${OUT_PATH}${MAIN}.class" ]; then # checks if $MAIN.class file exists
    echo "${MAIN}.class does not exist"
    build
  fi
  cd $OUT_PATH || exit
  echo "cd $OUT_PATH"
  make_class_string
  jar cfe ${MAIN}.jar $MAIN $CLASS_STR $RESOURCES
  mv -v ${MAIN}.jar ./../
  cd ..
  echo "cd .."
  echo "JAR file created"
  exit
}

function clean() {
  # Removes the $OUT_PATH folder
  echo "Cleaning folder..."
  if [ ! -d "$OUT_PATH" ]; then # checks if $OUT_PATH exists]
    echo "Nothing to clean"
  else
    rm -r $OUT_PATH
    rm -r $DOC_PATH
    echo "Folder cleaned"
    exit
  fi
  exit
}

function create_javadoc() {
  # Create all javadoc
  echo "Creating javadoc..."
  mkdir -p $DOC_PATH
  main_package=${MAIN////.}
  echo "javadoc -d ${DOC_PATH} -sourcepath $SRC_PATH -subpackages ${main_package%.*}"
  javadoc -d ${DOC_PATH} -sourcepath $SRC_PATH -subpackages ${main_package%.*}
  echo "cd .."
  cd ..
  echo "All documentation have been created !"
  exit
}

function help() {
  echo "You have to give only one arguments to this script. These are the allowed arguments:"
  echo "$BUILD         ->     $BUILD_DESC"
  echo "$RUN           ->     $RUN_DESC"
  echo "$BUILD_JAR     ->     $BUILD_JAR_DESC"
  echo "$DOC           ->     $DOC_DESC"
  echo "$CLEAN         ->     $CLEAN_DESC"
  echo "$HELP          ->     $HELP_DESC"
  exit
}

### Main body ###
if [ "$1" == $BUILD ]; then
  build
  exit
fi

if [ "$1" == $RUN ]; then
  run
fi

if [ "$1" == $BUILD_JAR ]; then
  build_jar
fi

if [ "$1" == $DOC ]; then
  create_javadoc
fi

if [ "$1" == $CLEAN ]; then
  clean
fi

if [ "$1" == $HELP ]; then
  help
fi

if [ "$1" == "" ]; then
  build_jar
fi

# if the command is not recognized (if the command is recognized, we never reach this point)
echo "Argument not recognized"
help