Ứng dụng Tin tức với Kotlin.
Đây là một ứng dụng đơn giản viết bằng Kotlin để hiển thị tin tức hàng ngày từ API Vietnam Top News API của newsdata.io.
Ứng dụng này cũng hỗ trợ chức năng đọc tin tức bằng cách sử dụng Text-to-Speech.

Cài đặt
1. Clone Repository: 
2. Import vào Android StudioMở Android Studio, chọn File > New > Import Project và chọn thư mục đã clone.
3. Thêm API KeyĐăng ký và nhận API Key từ Vietnam Top News API. Sau đó thêm API Key vào tệp local.properties của dự án: https://newsdata.io/news-sources/vietnam-top-news-api
news_api_key=YOUR_API_KEY_HERE
Công nghệ sử dụng
Kotlin - Ngôn ngữ lập trình chính
Retrofit - Thư viện HTTP để giao tiếp với API
RecyclerView - Hiển thị danh sách tin tức
Text-to-Speech (TTS) - Để đọc nội dung tin tức
Cách sử dụng
Ứng dụng sẽ lấy danh sách tin tức từ API khi khởi động và hiển thị chúng trong một danh sách cuộn. Người dùng có thể nhấn vào một mục tin tức để nghe nội dung tin bằng TTS.
