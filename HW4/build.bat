REM Comments ('remarks') in batch files start with REM.  Everything after REM is ignored.
REM You may need to change the path of the COMPILER_LOC variable on your system

set COMPILER_LOC="C:\Program Files\Java\jdk-17.0.1\bin"

del HW4.class

%COMPILER_LOC%\javac HW4.java

