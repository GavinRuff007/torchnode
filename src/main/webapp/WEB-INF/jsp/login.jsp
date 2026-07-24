<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>صفحه ورود به سیستم</title>
    <!-- لینک به استایل CSS که در پوشه static تعریف کردیم -->
    <link rel="stylesheet" type="text/css" href="/css/style.css">
    <!-- بارگذاری فونت زیبای وزیر برای متون فارسی -->
    <link href="https://fonts.googleapis.com/css2?family=Vazirmatn:wght@300;400;700&display=swap" rel="stylesheet">
</head>
<body>

<div class="login-container">
    <div class="login-card">
        <div class="login-header">
            <h2>خوش آمدید</h2>
            <p>لطفاً اطلاعات کاربری خود را وارد کنید</p>
        </div>

        <form action="/login" method="POST">
            <div class="form-group">
                <label for="username">نام کاربری</label>
                <input type="text" id="username" name="username" placeholder="نام کاربری خود را بنویسید" required autocomplete="off">
            </div>

            <div class="form-group">
                <label for="password">گذرواژه</label>
                <input type="password" id="password" name="password" placeholder="گذرواژه خود را بنویسید" required>
            </div>

            <div class="form-actions">
                <label class="remember-me">
                    <input type="checkbox" name="rememberMe">
                    <span>مرا به خاطر بسپار</span>
                </label>
                <a href="#" class="forgot-password">فراموشی رمز عبور؟</a>
            </div>

            <button type="submit" class="login-btn">ورود به سیستم</button>
        </form>

        <div class="login-footer">
            <p>کاربر جدید هستید؟ <a href="#">ثبت‌نام کنید</a></p>
        </div>
    </div>
</div>

</body>
</html>
