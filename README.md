# Автоматизация тестирования приложения Wikipedia

## :keyboard: Команда запуска тестов локально в IntelliJ в терминале

С использованием Browserstack:
```
gradle clean test -DdeviceHost=browserstack
```

С использованием эмулятора Android:
```
gradle clean test -DdeviceHost=emulation
```

С использованием реального смартфона:
```
gradle clean test -DdeviceHost=real
```

С использованием Selenoid:
```
gradle clean test -DdeviceHost=selenoid
```

Можно запустить отдельно тесты на поиск (заменить test на search) и на OnBoarding экран (заменить test на onboard)
