document.querySelectorAll('.post-card').forEach(card => {
   const toggleBtn = card.querySelector('.toggle-comment-btn');
   const commentBox = card.querySelector('.comment-box');
   const closeBtn = card.querySelector('.close-comment-btn');

   toggleBtn.addEventListener('click', () => {
     commentBox.style.display = 'block';
     toggleBtn.style.display = 'none';
   });

   closeBtn.addEventListener('click', () => {
     commentBox.style.display = 'none';
     toggleBtn.style.display = 'inline-block';
   });
 });
 
 function startEdit(commentId) {
   document.getElementById('content-' + commentId).style.display = 'none';
   document.getElementById('edit-form-' + commentId).style.display = 'block';

   // Lấy nội dung cũ đưa vào textarea
   const content = document.getElementById('content-' + commentId).innerText;
   document.getElementById('edit-textarea-' + commentId).value = content;
 }

 function cancelEdit(commentId) {
   document.getElementById('edit-form-' + commentId).style.display = 'none';
   document.getElementById('content-' + commentId).style.display = 'block';
 }

 function saveComment(commentId) {
   const textarea = document.getElementById('edit-textarea-' + commentId);
   const newContent = textarea.value.trim();

   if (newContent.length === 0) {
     alert('Nội dung bình luận không được để trống!');
     return;
   }

   fetch('/blog/comment/edit/' + commentId, {
     method: 'POST',
     headers: {
       'Content-Type': 'application/x-www-form-urlencoded',
       // Nếu dùng CSRF, thêm header token ở đây (xem phần lưu ý bên dưới)
     },
     body: 'content=' + encodeURIComponent(newContent)
   })
   .then(response => {
     if (response.ok) {
       document.getElementById('content-' + commentId).innerText = newContent;
       cancelEdit(commentId);
     } else {
       alert('Có lỗi xảy ra khi cập nhật bình luận.');
     }
   })
   .catch(error => {
     alert('Lỗi kết nối server.');
     console.error(error);
   });
 }
