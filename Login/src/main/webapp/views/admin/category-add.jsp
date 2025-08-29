<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Thêm danh mục</title>
  <style>
    :root{--bg:#f7f7fb;--card:#fff;--primary:#2563eb}
    body{margin:0;background:var(--bg);font-family:system-ui,-apple-system,Segoe UI,Roboto,Arial,sans-serif}
    .wrap{min-height:100vh;display:grid;place-items:center;padding:24px}
    .card{width:100%;max-width:640px;background:#fff;border-radius:16px;box-shadow:0 12px 26px rgba(0,0,0,.08);padding:24px}
    label{display:block;font-weight:600;margin:12px 0 6px}
    input{width:100%;padding:12px 14px;border:1px solid #e5e7eb;border-radius:10px}
    .row{display:flex;gap:10px;margin-top:16px}
    .btn{padding:12px 16px;border:0;border-radius:10px;background:var(--primary);color:#fff;font-weight:700;cursor:pointer}
    a.link{display:inline-block;padding:12px 16px;border-radius:10px;background:#e5e7eb;text-decoration:none;color:#111}
    .err{color:#b91c1c;margin:8px 0}
  </style>
</head>
<body>
<%@ include file="/views/_header.jsp" %>
<div class="wrap">
  <div class="card">
    <h1>Thêm danh mục</h1>
    <c:if test="${not empty error}">
      <div class="err">${error}</div>
    </c:if>

    <form method="post" action="<c:url value='/admin/category/add'/>">
      <label for="name">Tên danh mục *</label>
      <input id="name" name="name" required />

      <label for="icon">Icon (text/đường dẫn)</label>
      <input id="icon" name="icon" />

      <div class="row">
        <button class="btn" type="submit">Lưu</button>
        <a class="link" href="<c:url value='/admin/category/list'/>">Hủy</a>
      </div>
    </form>
  </div>
</div>
</body>
</html>
