document.addEventListener('DOMContentLoaded', (event) => {
  
    // Sidebar and form functionality
    const allSideMenu = document.querySelectorAll('#sidebar .side-menu.top li a');
    const newMemberAddBtn = document.querySelector('.addMemberBtn');
    const editInforBtn = document.querySelector('.editinforBtn');
    const viewInforBtn = document.querySelector('.viewInforBtn');
    const darkBg = document.querySelector('.dark_bg');
    const popupForm = document.querySelector('.popup');
    const crossBtn = document.querySelector('.closeBtn');
    const submitBtn = document.querySelector('.submitBtn');
    const modalTitle = document.querySelector('.modalTitle');
    const popupFooter = document.querySelector('.popupFooter');
    const imgInput = document.querySelector('.img');
    const imgHolder = document.querySelector('.imgholder');
    const form = document.querySelector('form');
    const formInputFields = document.querySelectorAll('form input');

	const deletePopup = document.getElementById('deletePopup');
    const confirmDeleteBtn = document.getElementById('confirmDeleteBtn');
    const cancelDeleteBtn = document.getElementById('cancelDeleteBtn');
    const deleteInforBtn = document.querySelector('.deleteInforBtn');
    const overlay = document.getElementById('overlay');
    const content = document.querySelector('.content');

    allSideMenu.forEach(item => {
        const li = item.parentElement;

        item.addEventListener('click', function () {
            allSideMenu.forEach(i => {
                i.parentElement.classList.remove('active');
            })
            li.classList.add('active');
        });
    });

    // Toggle sidebar
    const menuBar = document.querySelector('#content nav .bx.bx-menu');
    const sidebar = document.getElementById('sidebar');

    menuBar.addEventListener('click', function () {
        sidebar.classList.toggle('hide');
    });

    // Search button functionality
    const searchButton = document.querySelector('#content nav form .form-input button');
    const searchButtonIcon = document.querySelector('#content nav form .form-input button .bx');
    const searchForm = document.querySelector('#content nav form');

    searchButton.addEventListener('click', function (e) {
        if (window.innerWidth < 576) {
            e.preventDefault();
            searchForm.classList.toggle('show');
            if (searchForm.classList.contains('show')) {
                searchButtonIcon.classList.replace('bx-search', 'bx-x');
            } else {
                searchButtonIcon.classList.replace('bx-x', 'bx-search');
            }
        }
    });

    if (window.innerWidth < 768) {
        sidebar.classList.add('hide');
    } else if (window.innerWidth > 576) {
        searchButtonIcon.classList.replace('bx-x', 'bx-search');
        searchForm.classList.remove('show');
    }

    window.addEventListener('resize', function () {
        if (this.innerWidth > 576) {
            searchButtonIcon.classList.replace('bx-x', 'bx-search');
            searchForm.classList.remove('show');
        }
    });

    // Form popup functionality
    newMemberAddBtn.addEventListener('click', () => {
        isEdit = false;
        darkBg.classList.add('active');
        popupForm.classList.add('active');
    });

    editInforBtn.addEventListener('click', () => {
        isEdit = false;
        darkBg.classList.add('active');
        popupForm.classList.add('active');
    });

    crossBtn.addEventListener('click', () => {
        darkBg.classList.remove('active');
        popupForm.classList.remove('active');
        form.reset();
    });

    viewInforBtn.addEventListener('click', () => {
        isEdit = false;
        darkBg.classList.add('active');
        popupForm.classList.add('active');
    });


	deleteInforBtn.addEventListener('click', function() {
        deletePopup.classList.add('active');
        overlay.classList.add('active');
        content.style.filter = 'blur(5px)';
    });

    confirmDeleteBtn.addEventListener('click', function() {
        alert('Staff has deleted');
        deletePopup.classList.remove('active');
        overlay.classList.remove('active');
        content.style.filter = 'none';
    });

    cancelDeleteBtn.addEventListener('click', function() {
        deletePopup.classList.remove('active');
        overlay.classList.remove('active');
        content.style.filter = 'none';
    });

    overlay.addEventListener('click', function() {
        deletePopup.classList.remove('active');
        overlay.classList.remove('active');
        content.style.filter = 'none';
    });
});







