📱 passManager (Android)
Менеджер паролей для Android с открытым исходным кодом. Приложение обеспечивает безопасное хранение учетных данных на устройстве с использованием современных практик Android-разработки.

🔑 Основные функции
🛡️ Шифрование данных через Android Keystore
📁 Локальное хранение в SQLite с Room Database
📋 Удобный интерфейс с RecyclerView и Material Design
🔄 Синхронизация между устройствами (опционально)
🚀 Экспорт/импорт зашифрованных резервных копий

📥 Установка

Клонируйте репозиторий:
bash
git clone https://github.com/poguk12/passManager.git
Откройте проект в Android Studio (версия Arctic Fox+)
Соберите APK через Build > Build Bundle(s)/APK(s)
Установите на устройство или эмулятор (Android 8.0+)

🛠 Технологии

Язык: Java
Архитектура: MVVM
База данных: Room + SQLite
Шифрование: AndroidX Security Crypto
Биометрия: Biometric API
Дизайн: Material Components 1.6.0

🔒 Безопасность

Все данные шифруются AES-256 с ключом из Android Keystore
Мастер-пароль защищен PBKDF2 с 10000 итераций
Нет доступа в интернет (offline-first подход)
Рекомендуется включить биометрическую аутентификацию


🤝 Участие в разработке
Приветствуются пул-реквесты!
Форкните репозиторий
Создайте ветку для фичи:
bash
git checkout -b feature/[название-фичи]
Соблюдайте Google Java Style Guide
Тестируйте изменения на нескольких API уровнях (minSdk 26)


📮 Контакты
Автор: poguk12
Telegram: @okrodik
