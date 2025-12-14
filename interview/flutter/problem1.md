# Задача:

Есть форма редактирования профиля с 5 полями и превью аватара. 
При вводе в любое поле происходит лагание интерфейса. Нужно оптимизировать.

```
class ProfileFormBad extends StatefulWidget {
  @override
  _ProfileFormBadState createState() => _ProfileFormBadState();
}

class _ProfileFormBadState extends State<ProfileFormBad> {
  String _name = '';
  String _email = '';
  String _phone = '';
  String _bio = '';
  String _position = '';

  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      child: Column(
        children: [
          TextFormField(
            decoration: InputDecoration(labelText: 'Имя'),
            onChanged: (value) => setState(() => _name = value),
          ),
          TextFormField(
            decoration: InputDecoration(labelText: 'Email'),
            onChanged: (value) => setState(() => _email = value),
          ),
          TextFormField(
            decoration: InputDecoration(labelText: 'Телефон'),
            onChanged: (value) => setState(() => _phone = value),
          ),
          TextFormField(
            decoration: InputDecoration(labelText: 'Биография'),
            maxLines: 3,
            onChanged: (value) => setState(() => _bio = value),
          ),
          TextFormField(
            decoration: InputDecoration(labelText: 'Должность'),
            onChanged: (value) => setState(() => _position = value),
          ),
          
          _buildAvatarPreview(),
        ],
      ),
    );
  }

  Widget _buildAvatarPreview() {
    return Container(
      height: 150,
      padding: EdgeInsets.all(16),
      child: Stack(
        children: [
          CircleAvatar(
            radius: 60,
            backgroundImage: NetworkImage('https://example.com/avatar.jpg'),
          ),
          Positioned(
            bottom: 0,
            child: Text(
              _name.isEmpty ? 'Без имени' : _name,
              style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold),
            ),
          ),
        ],
      ),
    );
  }
}
```

