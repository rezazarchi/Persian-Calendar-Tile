# Persian-Calendar-Tile
این پروژه جهت نمایش تایل تقویم هجری شمسی در ساعت های هوشمند مجهز به سیستم عامل Wear OS ساخته شده است.
### تصاویر
![Screenshot_20231029_210553](https://github.com/rezazarchi/Persian-Calendar-Tile/assets/8654398/095c234a-fdf2-42fd-a81f-bba38c09a180)
![Screenshot_20231029_210350](https://github.com/rezazarchi/Persian-Calendar-Tile/assets/8654398/31e21c76-1a6f-45cf-b6fa-cb2467b2cc6c)
### نسخه سیستم عامل قابل پشتیبانی
Wear OS 4
### دستگاه‌های قابل پشتیبانی
- Google Pixel Watch
- Google Pixel Watch 2
- Samsung Galaxy Watch 4
- Samsung Galaxy Watch 4 Classic
- Samsung Galaxy Watch 5
- Samsung	Galaxy Watch 5 Pro
- Samsung	Galaxy Watch 6
- Samsung Galaxy Watch 6 Classic
- Xiaomi Watch 2 Pro
- And other Wear OS 3.5+ smart watches

### شیوه نصب

پس از بیلد فایل APK، شما می‌توانید از روش‌های side loading برای Wear os برای نصب این برنامه روی ساعت خودتان استفاده کنید.
- اطمینان حاصل کنید که ساعت هوشمند و تلفن هوشمند شما به یک اتصال Wi-Fi مشترک، وصل هستند.
- در ساعت هوشمند خود، وارد تنظیمات شوید. سپس About watch، سپس وارد قسمت Software info شوید.
- روی Software version چهار بار کلیک کنید تا Developer options فعال شود.
- اکنون باید در صفحه تنظیمات، گزینه Developer options را مشاهده کنید. وارد آن شوید.
- گزینه‌های ADB Debugging، Debug over Wi-Fi و Wireless debugging را فعال کنید.
- اجازه‌ی دیباگ را در ساعت خود صادر کنید.
- روی Pair new device کلیک کنید.
- توجه داشته باشید که آدرس آی پی در بخش Wireless debugging قابل مشاهده است.
- در اندروید استادیو، در قسمت Device manager یک دیوایس جدید جهت Pair اضافه کنید.
- وارد تب pair using code شوید. صبر کنید تا با اسکن، دستگاه شما پیدا شود.
- سپس روی Pair کلیک کنید و کد تایید مشاهده شده روی ساعت را وارد نمایید.
- اکنون می‌توانید با استفاده از ADB یا اندروید استادیو، برنامه را در ساعت خود نصب نمایید.
- از آنجایی که این پروژه شامل لانچر اپلیکیشن نمی‌باشد و فقط یک تایل جهت نمایش دارد، این اپلیکیشن را در لیست اپلیکیشن‌ها مشاهده نخواهید کرد. جهت استفاده، به روشی که در ویدیوی زیر نمایش داده شده، آن را به تایل‌های ساعت خود اضافه نمایید.

https://github-production-user-asset-6210df.s3.amazonaws.com/8654398/278888734-88f63795-75d6-47e1-b782-984245c27e93.mp4



### ساختار برنچ‌ها
در حال حاضر دو برنچ داریم. برنچ master که برنچ اصلی ماست و کدهای آن شامل نسخه پایدار پروژه می‌باشد که به وسیله Protolayout توسعه داده شده است. برنچ بعدی، [compose-implementation](https://github.com/rezazarchi/Persian-Calendar-Tile/tree/compose-implementation) می‌باشد که شامل پیاده‌سازی بازنویسی شده به وسیله Glance بوده که کد یو آی Tile بر پایه Compose می‌باشد. از آنجایی که کتابخانه Glance همچنان در فاز آلفا بوده و ضمناً این برنچ در توسعه بوده و ویژگی‌های دیگری همچون DI از طریق Hilt و تست یو آی برای کامپوز و ... به مرور به آن افزوده خواهد شد، هنوز پایدار نبوده و فعلا در برنچ اصلی مرج نخواهد شد اما می‌توان به طور جداگانه آن را مشاهده کنید و در صورت تمایل روی آن Contribution انجام دهید.

###  تشکر و قدردانی

- جهت تبدیل تاریخ از [لایبرری Persian calendar](https://github.com/persian-calendar/calendar) استفاده شده است.
- جهت نمایش رویدادها، از فایل جی‌سان رویدادهای پروژه متن‌باز [تقویم فارسی](https://github.com/persian-calendar/persian-calendar) استفاده شده است.
- این پروژه هنوز در ابتدای راه است و به کمک شما دوستان عزیز جهت ارسال Issue و Pull request نیاز داریم و پذیرای هر نوع Contribution از شما عزیزان هستم.



  ### مجوز شرایط استفاده از کد برنامه
  با توجه به اینکه پروژه‌های اشاره شده در بالا، با مجوز GPL 3 منتشر شده‌اند، بنابراین این پروژه نیز با همین مجوز منتشر می‌شود که شرایط آن در قسمت License قابل مطالعه است.
