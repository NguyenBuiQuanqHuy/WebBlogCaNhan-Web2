document.addEventListener("DOMContentLoaded", function () {
   const posts = document.querySelectorAll(".post-content");

   posts.forEach(post => {
     const preview = post.querySelector(".content-preview");
     const btn = post.querySelector(".see-more-btn");

     // Kiểm tra nếu nội dung dài hơn maxHeight thì hiển thị nút
     if (preview.scrollHeight <= 150) {
       btn.style.display = "none";
     }

     btn.addEventListener("click", function () {
       preview.classList.toggle("expanded");
       btn.textContent = preview.classList.contains("expanded") ? "Thu gọn" : "Xem thêm...";
     });
   });
 });