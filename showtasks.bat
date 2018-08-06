call runcrud.bat
if "%ERRORLEVEL%" == "0" goto openbrowser
echo.
echo Error during compilation
goto end

:openbrowser
start chrome "http://localhost:8080/crud/v1/task/getTasks"
if "%ERRORLEVEL%" == "0" goto end
echo.
echo Can't open browser

:end
echo.
echo Done.