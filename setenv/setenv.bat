REM # This script/batch file sets all the envrironment variables required by other batch files and 
REM # scripts inorder to run the demos. 
REM # This script/batch file will be invoked by every other script in the demos directory so that
REM # envrionment variables are properly set before running an application. This ensures that users
REM # have to change the envrironment settings in only one location.


REM # SET PATH FOR Native Libraries
set PATH=%PATH%;lib\;external_lib\

REM # set the Java home directory
rem set JAVA_HOME=C:\Program Files\Java\jdk1.7.0_80
set JAVA_HOME=D:\envir\java

set JAVA_CMD="%JAVA_HOME%\bin\java.exe"

set JAVAC_CMD="%JAVA_HOME%\bin\javac.exe"


REM #Set the home directory of the GSP library
set gspDemoHome=.

REM # set classpath to the GSP library Jar files and the database JDBC drivers.
set CLASSPATH=%gspDemoHome%\build;%gspDemoHome%\lib;%gspDemoHome%\lib\*;%gspDemoHome%\external_lib;%gspDemoHome%\external_lib\simple-xml-2.6.2.jar;%gspDemoHome%\external_lib\expr4j.jar;%gspDemoHome%\external_lib\commons-logging-1.1.3.jar;%gspDemoHome%\external_lib\jarLoader.jar;%gspDemoHome%\external_lib\jdk1.5\junit-4.5.jar;%gspDemoHome%\external_lib\junrar-0.7.jar;%gspDemoHome%\external_lib\fastjson-1.2.41.jar;.;
