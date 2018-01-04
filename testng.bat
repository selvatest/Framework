set projectlocation=D:\M.selva\Outside-Project-master
cd %projectlocation%
java -cp %projectlocation%\bin;%projectlocation%\lib* org.testng.TestNG testng.xml
pause