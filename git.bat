@echo off & setLocal EnableExtensions EnableDelayedExpansion

set "GCCVersion=1.0.0"
set "IniFileName=git.ini"

title = Git Control Command [v%GCCVersion%]

@cd %~dp0

@echo off

echo *********************************************************************
echo * Powered by [M.A Riazati] * Git Control Command * Version: [%GCCVersion%] * 
echo *********************************************************************
echo *
echo * Detecting [%IniFileName%] file ...

if not exist "%IniFileName%" (
  goto iniNotDetected
) else (
  goto iniDetected
)
	
:: -----------------------------------------------------------------------------------------------------------

:iniNotDetected
	echo * 	Config File detecting failed.
	goto :storeAuthConfig
	
:: -----------------------------------------------------------------------------------------------------------

:iniDetected
	echo * 	Config File detected successfully.
	goto :initAuthenticatedUser
	
:: -----------------------------------------------------------------------------------------------------------

:storeAuthConfig
	(
	  echo [Authenticated]
	  echo name=
	  echo email=
	  echo password=
	) > git.ini
	echo * 	Config File stored successfully.
	goto :initAuthenticatedUser
	
:: -----------------------------------------------------------------------------------------------------------

:initAuthenticatedUser
	echo *
	echo * User information detected from config file:
	:: ****************************************************************************
	set "name="
	set "nameStatus=1"
	for /f "delims=" %%a in ('findstr /i "^name=" %IniFileName%') do set "%%a"
	if not defined name (
		set "nameStatus=-1"
		echo *	Name    : [Identification error]
	) else if name == "" (
		set "nameStatus=0"
		echo *	Name    : [No content]
	) else (
		echo *	Name    : %name%
	)
	:: ****************************************************************************
	set "email="
	set "emailStatus=1"
	for /f "delims=" %%a in ('findstr /i "^email=" %IniFileName%') do set "%%a"
	if not defined email (
		set "emailStatus=-1"
		echo *	Email   : [Identification error]
	) else if email == "" (
		set "emailStatus=0"
		echo *	Email   : [No content]
	) else (
		echo *	Email   : %email%
	)
	:: ****************************************************************************
	set "password="
	set "passwordStatus=1"
	for /f "delims=" %%a in ('findstr /i "^password=" %IniFileName%') do set "%%a"
	if not defined password (
		set "passwordStatus=-1"
		echo *	Password: [Identification error]
	) else if password == "" (
		set "passwordStatus=0"
		echo *	Password: [No content]
	) else (
		echo *	Password: %password%
	)
	echo *
	:: ****************************************************************************
	set "isNeed2Change=0"
	:: ****************************************************************************
	if "%nameStatus%" == "-1" (
		set "isNeed2Change=1"
		call :getUserName
	) else if "%nameStatus%" == "0" (
		set "isNeed2Change=1"
		call :getUserName
	)
	:: ****************************************************************************
	if "%emailStatus%" == "-1" (
		set "isNeed2Change=1"
		call :getUserEmail
	) else if "%emailStatus%" == "0" (
		set "isNeed2Change=1"
		call :getUserEmail
	)
	:: ****************************************************************************
	if "%passwordStatus%" == "-1" (
		set "isNeed2Change=1"
		call :getUserPassword
	) else if "%passwordStatus%" == "0" (
		set "isNeed2Change=1"
		call :getUserPassword 
	)
	:: ****************************************************************************
	if "%isNeed2Change%" == "1" (
		(
		  echo [Authenticated]
		  echo name=%name%
		  echo email=%email%
		  echo password=%password%
		) > git.ini
		echo * 	Config File stored successfully with new information.
		goto :initAuthenticatedUser
	)
	:: ****************************************************************************
	echo * Is the user information above correct about you?
	echo *	[1]: Yes, Continue
	echo *	[-]: Send anything to exit.
	echo *
	set /p isCorrect="* Your answer: " || set "isCorrect=0"
	if not "%isCorrect%"=="1" (
		goto :notCorrectUser
	) else (
		goto :correctUser
	)
	
:: -----------------------------------------------------------------------------------------------------------

:getUserName
	echo * To continue, you must enter your name:
	set /p name="* Your name: " || set "name=-1"
	echo *
	if /I "%name%"=="-1" (
		goto :getUserName
	) else if /I "%name%"=="" (
		goto :getUserName
	)
	goto :eof
	
:: -----------------------------------------------------------------------------------------------------------

:getUserEmail
	echo * To continue, you must enter your email:
	set /p email="* Your email: " || set "email=-1"
	echo *
	if /I "%email%"=="-1" (
		goto :getUserEmail
	) else if /I "%email%"=="" (
		goto :getUserEmail
	)
	goto :eof
	
:: -----------------------------------------------------------------------------------------------------------

:getUserPassword
	echo * To continue, you must enter your password:
	set /p password="* Your password: " || set "password=-1"
	echo *
	if /I "%password%"=="-1" (
		goto :getUserPassword
	) else if /I "%password%"=="" (
		goto :getUserPassword
	)
	goto :eof
	
:: -----------------------------------------------------------------------------------------------------------

:notCorrectUser
	echo *
	echo * Ops, I will see you again later. I hope :)
	echo *
	echo * MMMMM, If you want, I can delete the configuration file...
	echo *	[1]: Sure, Delete it
	echo *	[-]: Send anything to exit.
	echo *
	set /p isDeleteConfig="* I delete it?: " || set "isDeleteConfig=0"
	echo *
	if "%isDeleteConfig%"=="1" (
		del %IniFileName%
		echo * The deletion of the config file has been completed.
		echo *
	) else (
		echo * The config file is kept for reuse.
		echo *
	)
	pause
	exit
	
:: -----------------------------------------------------------------------------------------------------------

:correctUser
	echo *
	echo * OK, Setting the user in the git...
	echo *
	echo *********************************************************************
	::start cmd /c "git status"
	::git status
	echo *********************************************************************
	echo *
	echo * Setup completed.
	echo *
	pause
	exit
	
:: -----------------------------------------------------------------------------------------------------------
	
	
	
	
	
	
	
	
	
:: -----------------------------------------------------------------------------------------------------------

:gitStatus
	echo * gitStatus successfully.
	echo *
	pause
	exit

:gitPull
	echo * gitPull successfully.
	echo *
	pause
	goto :eof

:gitPush
	echo * gitPush successfully.
	echo *
	pause
	goto :eof
	

echo *
echo * What are you going to do?

pause