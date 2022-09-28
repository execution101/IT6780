REM Comments ('remarks') in batch files start with REM.  Everything after REM is ignored.
REM You may need to change the path of the JRE_LOC variable on your system

set JRE_LOC="C:\Program Files\Java\jdk-17.0.1\bin"

%JRE_LOC%\java HW4 encrypt .\cleartext.txt .\data.encrypted
%JRE_LOC%\java HW4 decrypt .\data.encrypted .\cleartext2.txt

REM %JRE_LOC%\java HW4 encrypt .\smile.png .\picture.encrypted
REM %JRE_LOC%\java HW4 decrypt .\picture.encrypted .\smile2.png

