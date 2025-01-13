# historycodeTA



## before run
create file `src/test/resources/config.properties`
```properties
base.ui.url=base_frontend_url
implicitlyWait=10
```


run allure serve
```shell
allure serve .\target\allure-results\
```



How to use TestRunnerAdminWithUserProfile
1. Create new profile in chrome
2. Add path to the directory with user profiles and path to the created profile to config.properties:
```properties
userDataDir=C:/Users/Viktor/AppData/Local/Google/Chrome/User Data/
profileDir=Profile 3
```
3. Log in to the admin panel using created profile
4. Run SmokeTestAdminPanelWithBrowserProfile tests. At second test you will get Chrome version parameters.
If you get SessionNotCreatedException - close all instances of Chrome and rerun the tests
5. Check the path to your profile. It should be like this:
```
C:\Users\Viktor\AppData\Local\Google\Chrome\User Data\Profile 3```
```
If your path ends with \Default and you are not using the default profile - check path to the profile and profiles directory
