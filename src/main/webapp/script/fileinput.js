const actualBtn = document.getElementById('fileInput');
const fileChosen = document.getElementById('file-upload-label');

actualBtn.addEventListener('change', function () {
    fileChosen.innerText = this.files[0].name;
})