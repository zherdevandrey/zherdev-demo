# Задача: Параллельная обработка данных и обновление UI
Сценарий:
Приложение должно загружать и обрабатывать три типа данных одновременно:
- Профиль пользователя (сеть)
- Список уведомлений (сеть)
- Локальные настройки (база данных)

Требования:
- Загрузка должна происходить параллельно
- UI должен обновляться по мере получения данных
- Обработка ошибок для каждого типа данных
- Возможность отмены операций
- Общее время загрузки ≈ времени самой медленной операции

```
class DataLoaderBad extends StatefulWidget {
  @override
  _DataLoaderBadState createState() => _DataLoaderBadState();
}

class _DataLoaderBadState extends State<DataLoaderBad> {
  UserProfile? userProfile;
  List<Notification>? notifications;
  AppSettings? settings;
  bool isLoading = true;

  @override
  void initState() {
    super.initState();
    _loadDataSequentially();
  }

  // ПЛОХО: последовательная загрузка
  Future<void> _loadDataSequentially() async {
    try {
      setState(() => isLoading = true);
      
      userProfile = await UserApi.getProfile(); 
      notifications = await NotificationApi.getList(); 
      settings = await DatabaseService.getSettings();
      
    } catch (e) {
      print('Error: $e');
    } finally {
      setState(() => isLoading = false);
    }
  }

  @override
  Widget build(BuildContext context) {
    if (isLoading) return CircularProgressIndicator();
    
    return Column(
      children: [
        if (userProfile != null) UserCard(userProfile!),
        if (notifications != null) NotificationsList(notifications!),
        if (settings != null) SettingsWidget(settings!),
      ],
    );
  }
}
```
