# historycodeTA



## config

create src/test/resources/log4j2.properties format:
```properties
appender.console.type = Console
appender.console.name = console
appender.console.layout.type = PatternLayout
appender.console.layout.pattern = %d [%t] %-5p %c - %m%n

appender.file.type = File
appender.file.name = file
appender.file.fileName = target/logs/test.log
appender.file.layout.type = PatternLayout
appender.file.layout.pattern = %d [%t] %-5p %c - %m%n

rootLogger.level = debug
rootLogger.appenderRef.console.ref = console
rootLogger.appenderRef.file.ref = file
```

## before run
create file `src/test/resources/config.properties`
```properties
base.ui.url=https://frontend.historycode.online
implicitlyWait=10

admin.email=admin.email@gmail.com
admin.pass=admin.pass

AccessToken=...
RefreshToken=...
```


run allure serve
```shell
allure serve .\target\allure-results\
```
