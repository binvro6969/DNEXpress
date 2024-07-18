document.addEventListener('DOMContentLoaded', (event) => {
    const viewBtns = document.querySelectorAll('.view-btn');
    const editBtns = document.querySelectorAll('.edit-btn');
    const deleteBtns = document.querySelectorAll('.delete-btn');
    const overlay = document.getElementById('overlay');
    const darkBg = document.querySelector('.dark_bg');
    const popupForm = document.querySelector('.popup');
    const crossBtn = document.querySelector('.closeBtn');
    const submitBtn = document.querySelector('.submitBtn');
    const modalTitle = document.querySelector('.modalTitle');
    const deletePopup = document.getElementById('deletePopup');
    const confirmDeleteBtn = document.getElementById('confirmDeleteBtn');
    const cancelDeleteBtn = document.getElementById('cancelDeleteBtn');
    const content = document.querySelector('.content');

    // Sidebar functionality
    const allSideMenu = document.querySelectorAll('#sidebar .side-menu.top li a');
    allSideMenu.forEach(item => {
        const li = item.parentElement;
        item.addEventListener('click', function () {
            allSideMenu.forEach(i => {
                i.parentElement.classList.remove('active');
            });
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
    const showPopup = (title, showSubmit = false) => {
        modalTitle.textContent = title;
        submitBtn.style.display = showSubmit ? 'block' : 'none';
        darkBg.classList.add('active');
        popupForm.classList.add('active');
    };

    viewBtns.forEach(btn => {
        btn.addEventListener('click', () => {
            showPopup('View Staff');
        });
    });

    editBtns.forEach(btn => {
        btn.addEventListener('click', () => {
            showPopup('Edit Staff', true);
        });
    });

    deleteBtns.forEach(btn => {
        btn.addEventListener('click', () => {
            deletePopup.classList.add('active');
            overlay.classList.add('active');
            content.style.filter = 'blur(5px)';
        });
    });

    crossBtn.addEventListener('click', () => {
        darkBg.classList.remove('active');
        popupForm.classList.remove('active');
        form.reset();
    });

    confirmDeleteBtn.addEventListener('click', function () {
        alert('Staff has been deleted');
        deletePopup.classList.remove('active');
        overlay.classList.remove('active');
        content.style.filter = 'none';
    });

    cancelDeleteBtn.addEventListener('click', function () {
        deletePopup.classList.remove('active');
        overlay.classList.remove('active');
        content.style.filter = 'none';
    });

    overlay.addEventListener('click', function () {
        deletePopup.classList.remove('active');
        overlay.classList.remove('active');
        content.style.filter = 'none';
    });
});
