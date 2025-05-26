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
 
   window.addEventListener("DOMContentLoaded", function () {
     const anchor = window.location.hash;
     if (anchor && anchor.startsWith("#post-")) {
       const postElement = document.querySelector(anchor);
       if (postElement) {
         const commentBox = postElement.querySelector(".comment-box");
         if (commentBox) {
           commentBox.style.display = "block";
         }
       }
     }
   });
