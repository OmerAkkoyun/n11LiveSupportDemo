# Real-Time Live Support Application - Demo

Bu proje, **WebSocket** kullanarak gerçek zamanlı canlı destek sağlayan bir sohbet uygulamasıdır. Uygulama, dinamik bir arayüz üzerinden kullanıcılarla etkileşime geçer ve sağlanan JSON yapısına göre butonlar ve mesajları oluşturur. Uygulama, modern Android geliştirme teknolojileri ile **MVVM Clean Architecture** yapısını takip eder.

## 🚀 Özellikler

- **Gerçek Zamanlı Sohbet**: **WebSocket** üzerinden sunucu ve istemci arasında iki yönlü, gerçek zamanlı iletişim.
- JSON yapısına göre dinamik UI bileşenleri (buton, metin ve resim).
- Asenkron işlemler için **Coroutines** ve **LiveData/Flow** kullanımı.
- **Hilt** ile bağımlılık enjeksiyonu.
- **MVVM Clean Architecture** ile temiz ve sürdürülebilir kod yapısı.
- **Room** kullanılarak yerel veri depolama.
- **ViewBinding** ile UI bileşenlerine güvenli erişim.

## 🛠️ Kullanılan Teknolojiler

- **MVVM Clean Architecture**: Uygulamanın farklı katmanlar arasında net bir ayrım sağlayan, ölçeklenebilir ve sürdürülebilir bir mimari yapıdır.
- **ViewBinding**: UI bileşenlerine güvenli bir şekilde erişim sağlar ve `findViewById` kullanımını ortadan kaldırır.
- **Hilt**: Android’de bağımlılık enjeksiyonunu kolaylaştırmak için kullanılan bir DI kütüphanesidir.
- **Coroutines & LiveData/Flow**: Asenkron işlemleri yönetmek için kullanılır. Verilerin UI ile etkileşimli bir şekilde güncellenmesini sağlar.
- **Room**: SQLite üzerinde çalışan, veritabanı işlemlerini basit hale getiren bir ORM kütüphanesidir.
- **WebSocket**: Gerçek zamanlı, çift yönlü iletişim sağlar.

## 📐 Mimarisi

Bu proje, **MVVM Clean Architecture** mimarisini takip eder. Bu mimari, uygulamanın farklı katmanlar arasında net bir ayrım sağlayarak daha test edilebilir ve sürdürülebilir bir yapı sunar.

### Katmanlar:
- **Presentation Layer**: UI ile doğrudan iletişim kurar. `ViewModel`, UI verilerini yönetir ve günceller.
- **Domain Layer**: Uygulamanın iş kurallarını içerir. `UseCase`'ler iş mantığını kapsar.
- **Data Layer**: Veri kaynaklarını yönetir. WebSocket ve Room gibi veri sağlayıcıları burada bulunur.

## 📷🤩 Ekran Görüntüleri


![Screenshot_1728661011](https://github.com/user-attachments/assets/f8957e36-683b-4e74-ada5-ee6751af16f7)
![Screenshot_1728577950](https://github.com/user-attachments/assets/536128e2-2ee3-43ff-8c56-e36e62ea1587)
![Screenshot_1728661804](https://github.com/user-attachments/assets/e1ed7ea7-ea5f-47f7-a98f-91ef4420c77e)
![Screenshot_1728577883](https://github.com/user-attachments/assets/f0660900-3906-463e-80d3-e818832f7dd2)
![Screenshot_1728577868](https://github.com/user-attachments/assets/73d4087c-1612-46d4-a607-9711f340f0be)
![Screenshot_1728578045](https://github.com/user-attachments/assets/24f9446e-26d4-48e9-a304-56e430948daf)




