# ITMO-Practice
6-19 Feb 2017

##Task 2

###Task2-Provider
- Бандл, предоставляющий сервис
- Не имеет сторонних зависимостей

###Task2-Consumer
- Бандл, потребляющий сервис
- Требует наличие бандла Task2-Provider


##Task 3

###Task3-Service
- Бандл, предоставляющий сервис
- Не имеет сторонних зависимостей
- Для корректного запуска небходимо наличие бандла org.apache.felix.scr

###Task3-Consumer
- Бандл, потребляющий сервис
- Требует наличие бандла Task3-Service
- Для корректного запуска небходимо наличие бандла org.apache.felix.scr


##Task 4


###Task4
- Бандл, регистрирующийся в качестве команды
- Не имеет сторонних зависимостей
- Для корректного запуска небходимо наличие бандла org.apache.felix.scr


##Task 5

###Command
- Бандл, регистрирующийся в качестве команды
- Требует наличие бандла stats-api
- Для корректного запуска небходимо наличие бандла org.apache.felix.scr

###lenta-news
- Бандл, предоставляющий сервис получения заголовков новостей из источника Lenta.ru
- Имеет встроенную стороннюю зависимость json-simple
- Требует наличие бандла stats-api
- Для корректного запуска небходимо наличие бандла org.apache.felix.scr

###aif-news
- Бандл, предоставляющий сервис получения заголовков новостей из источника RSS АиФ
- Не имеет сторонних зависимостей
- Требует наличие бандла stats-api
- Для корректного запуска небходимо наличие бандла org.apache.felix.scr

###stats-api
- Бандл, предоставляющий интерфейс
- Не имеет сторонних зависимостей
