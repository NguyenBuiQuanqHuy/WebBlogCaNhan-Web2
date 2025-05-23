document.addEventListener('DOMContentLoaded', function() {
    tinymce.init({
        // cấu hình...
    });
});


	tinymce.init({
	        selector: 'textarea.form-content',
	        language: 'vi',
	        height: 800,
	        plugins: 'advlist autolink lists link image charmap preview anchor pagebreak code fullscreen table emoticons autosave',
	        toolbar: 'undo redo | styles | bold italic underline strikethrough | forecolor backcolor | fontselect fontsizeselect | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image table | emoticons | fullscreen preview code',
	        menubar: 'file edit view insert format tools table',
	        image_title: true,
	        automatic_uploads: true,
	        file_picker_types: 'image',
	        file_picker_callback: function (cb, value, meta) {
	            const input = document.createElement('input');
	            input.setAttribute('type', 'file');
	            input.setAttribute('accept', 'image/*');
	            input.onchange = function () {
	                const file = this.files[0];
	                const reader = new FileReader();
	                reader.onload = function () {
	                    const id = 'blobid' + (new Date()).getTime();
	                    const blobCache = tinymce.activeEditor.editorUpload.blobCache;
	                    const base64 = reader.result.split(',')[1];
	                    const blobInfo = blobCache.create(id, file, base64);
	                    blobCache.add(blobInfo);
	                    cb(blobInfo.blobUri(), { title: file.name });
	                };
	                reader.readAsDataURL(file);
	            };
	            input.click();
	        },
	        autosave_interval: '30s',
	        autosave_retention: '5m',
	        content_style: "body { font-family:Arial,sans-serif; font-size:14px }"
	    });