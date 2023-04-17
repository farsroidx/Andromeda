@echo off & setLocal EnableExtensions EnableDelayedExpansion

set "GCCVersion=1.0.0"
set "IniFileName=git.ini"
set "GitCommand="
set "GitExtraCommand="%~1\""
set "GitCurrentBranch=main"

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
	  echo location=
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
		::echo *	Password: %password%
		echo *	Password: **********
	)
	:: ****************************************************************************
	set "location="
	set "locationStatus=1"
	for /f "delims=" %%a in ('findstr /i "^location=" %IniFileName%') do set "%%a"
	if not defined location (
		set "locationStatus=-1"
		echo *	Location: [Identification error]
	) else if location == "" (
		set "locationStatus=0"
		echo *	Location: [No content]
	) else (
		echo *	Location: %location%
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
	if "%locationStatus%" == "-1" (
		set "isNeed2Change=1"
		call :getGitLocation
	) else if "%locationStatus%" == "0" (
		set "isNeed2Change=1"
		call :getGitLocation 
	)
	:: ****************************************************************************
	if "%isNeed2Change%" == "1" (
		(
		  echo [Authenticated]
		  echo name=%name%
		  echo email=%email%
		  echo password=%password%
		  echo location=%location%
		) > git.ini
		echo * 	Config File stored successfully with new information.
		goto :initAuthenticatedUser
	)
	:: ****************************************************************************
	set "GitCommand="%location%\bin\sh.exe" --login -i -c"
	:: ****************************************************************************
	echo * Is the user information above correct about you?
	echo *	[1]: Yes, Continue
	echo *	[ ]: Send anything to exit.
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

:getGitLocation
	echo * To continue, you must enter your git location:
	set /p location="* Your location: " || set "location=-1"
	echo *
	if /I "%location%"=="-1" (
		goto :getGitLocation
	) else if /I "%location%"=="" (
		goto :getGitLocation
	)
	goto :eof
	
:: -----------------------------------------------------------------------------------------------------------

:notCorrectUser
	echo *
	echo * Ops, I will see you again later. I hope :)
	echo *
	echo * MMMMM, If you want, I can delete the configuration file...
	echo *	[1]: Sure, Delete it
	echo *	[ ]: Send anything to exit.
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
	echo *********************************************************************
	echo *
	echo * OK, Setting the user in the git...
	echo *
	echo *********************************************************************
	echo *
	%GitCommand% "git config --global user.name \"%name%\"" %GitExtraCommand%
	echo * The name settings have been updated again.
	%GitCommand% "git config --global user.email \"%email%\"" %GitExtraCommand%
	echo * The email settings have been updated again.
	%GitCommand% "git config --global user.password \"%password%\"" %GitExtraCommand%
	echo * The password settings have been updated again.
	echo *
	echo *********************************************************************
	echo *
	echo * Setup completed.
	echo *
	echo *********************************************************************
	echo *
	goto :gitMenu

:: -----------------------------------------------------------------------------------------------------------

:gitMenu
	echo * What are you going to do?
	echo *	[1]: Reflog --- Display the latest project status.
	echo *	[2]: Status --- Display the latest project status.
	echo *	[3]: Pull ----- Display the latest project status.
	echo *	[4]: Push ----- Display the latest project status.
	echo *
	set /p need="* Your need: " || set "need=0"
	echo *
	if /I "%need%"=="1" (
		goto :gitReflog
	) else if /I "%need%"=="2" (
		goto :gitStatus
	) else if /I "%need%"=="3" (
		goto :gitPull
	) else if /I "%need%"=="4" (
		goto :gitPush
	) else (
		goto :gitMenu
	)
	echo *
	pause
	exit
	
:: -----------------------------------------------------------------------------------------------------------

:gitReflog
	echo * Git Reflog is running...
	echo *
	%GitCommand% "git reflog" %GitExtraCommand%
	echo *
	goto :gitNeedMore
	
:: -----------------------------------------------------------------------------------------------------------

:gitStatus
	echo * Git Status is running...
	echo *
	%GitCommand% "git status" %GitExtraCommand%
	echo *
	goto :gitNeedMore
	
:: -----------------------------------------------------------------------------------------------------------

:gitPull
	echo * Which branch do you want to receive data from?
	echo *
	%GitCommand% "git branch -a" %GitExtraCommand%
	echo *
	set /p needBranch="* Your branch (%GitCurrentBranch%): " || set "needBranch=%GitCurrentBranch%"
	echo *
	echo * Git Pull is running...
	echo *
	%GitCommand% "git pull origin %needBranch%" %GitExtraCommand%
	echo *
	goto :gitNeedMore
	
:: -----------------------------------------------------------------------------------------------------------

:gitPush
	echo * Which branch do you want to send the data to?
	echo *
	%GitCommand% "git branch -a" %GitExtraCommand%
	echo *
	set /p needBranch="* Your branch (%GitCurrentBranch%): " || set "needBranch=%GitCurrentBranch%"
	echo *
	%GitCommand% "git status" %GitExtraCommand%
	echo *
	echo * Which changes do you want to add?
	echo *
	set /p needAdd="* Which change (all): " || set "needAdd=."
	echo *
	%GitCommand% "git add %needAdd%" %GitExtraCommand%
	echo *
	echo * Enter commit message:
	set /p commitMessage="Message (Initial commit): " || set "commitMessage=Initial commit"
	echo *
	%GitCommand% "git commit -m \"%commitMessage%\"" %GitExtraCommand%
	echo *
	%GitCommand% "git config --global push.autoSetupRemote true" %GitExtraCommand%
	echo *
	echo * Git Push is running...
	echo *
	%GitCommand% "git push origin %needBranch%" %GitExtraCommand%
	echo *
	goto :gitNeedMore
	
:: -----------------------------------------------------------------------------------------------------------

:gitNeedMore
	echo * Do you need more operations?
	echo *	[1]: Return to git menu.
	echo *	[ ]: Send anything to exit.
	echo *
	set /p isMore="* Need: " || set "isMore=0"
	echo *
	if /I "%isMore%"=="1" (
		goto :gitMenu
	) else (
		echo * I'm happy to see you!
		echo *
		pause
		exit
	)