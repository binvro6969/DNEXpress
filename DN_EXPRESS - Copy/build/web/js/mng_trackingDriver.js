document.addEventListener('DOMContentLoaded', (event) => {

    // Sidebar and form functionality
    const currentPath = window.location.pathname.split('/').pop();

    // Lấy tất cả các thẻ a trong menu
    const allSideMenu = document.querySelectorAll('#sidebar .side-menu.top li a');

    // Duyệt qua các thẻ a và kiểm tra href
    allSideMenu.forEach(item => {
        const li = item.parentElement;
        const itemPath = item.getAttribute('href').split('/').pop();

        if (itemPath === currentPath) {
            li.classList.add('active');
        }

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

    // Attach event listener to the button
    const viewButtons = document.querySelectorAll('.viewInforBtn');
    viewButtons.forEach(button => {
        button.addEventListener('click', redirectToPage);
    });
});

// Define the function globally
function redirectToPage() {
    window.location.href = "result.html"; 
}









