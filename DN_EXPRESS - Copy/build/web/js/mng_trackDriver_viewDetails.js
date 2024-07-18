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

    // Get the latitude and longitude from the JSP
    const latitude = parseFloat(document.getElementById("map").getAttribute("data-latitude"));
    const longitude = parseFloat(document.getElementById("map").getAttribute("data-longitude"));

    // Initialize the map
    const mapOptions = {
        center: { lat: latitude, lng: longitude },
        zoom: 18
    };
    const map = new google.maps.Map(document.getElementById("map"), mapOptions);

    // Add a marker at the driver's location
    const marker = new google.maps.Marker({
        position: { lat: latitude, lng: longitude },
        map: map,
        title: "Driver's Location"
    });

    // Nút Back
    const backBtn = document.getElementById('back_btn');
    backBtn.addEventListener('click', function() {
        window.history.back();
    });

    // Nút contact
    const contactBtn = document.getElementById('contact_btn');
    contactBtn.addEventListener('click', function() {
        alert("Contact button clicked!");
    });

    // New code integration
    document.getElementById('shipment_list_btn').addEventListener('click', function() {
        document.getElementById('driver_info').style.display = 'none';
        document.getElementById('shipment_list').style.display = 'block';
    });

    document.getElementById('back_shipment_btn').addEventListener('click', function() {
        document.getElementById('shipment_list').style.display = 'none';
        document.getElementById('driver_info').style.display = 'block';
    });

    document.querySelectorAll('.view_packageDetail_btn').forEach(function(card) {
        card.addEventListener('click', function() {
            var orderId = card.getAttribute('data-order-id');
            console.log('Clicked on Order ID:', orderId);
            sessionStorage.setItem('selectedOrderId', orderId);

            // Tạo yêu cầu Ajax để gửi orderId lên server
            var xhr = new XMLHttpRequest();
            xhr.open('POST', 'mng_trackDriver_viewDetails', true);
            xhr.setRequestHeader('Content-Type', 'application/json');

            // Dữ liệu gửi lên server
            var data = {
                orderId: orderId
            };

            // Chuyển đổi dữ liệu thành JSON và gửi yêu cầu
            xhr.send(JSON.stringify(data));

            // Xử lý phản hồi từ server (nếu cần)
            xhr.onload = function() {
                console.log('xhr.status:', xhr.status);
                if (xhr.status === 200) {
                    var responseData = JSON.parse(xhr.responseText);
                    var htmlContent = responseData.htmlContent;
                    document.getElementById('shipment_detail_content').innerHTML = htmlContent;
                    console.log('Server response:', htmlContent);
                    document.getElementById('shipment_list').style.display = 'none';
                    document.getElementById('shipment_info').style.display = 'block';
                } else {
                    console.error('Error updating selectedOrderId:', xhr.status);
                    // Xử lý lỗi nếu có
                }
            };
        });
    });

    document.getElementById('back_shipment__list_btn').addEventListener('click', function() {          
        document.getElementById('shipment_list').style.display = 'block';
        document.getElementById('shipment_info').style.display = 'none';
    });
});

// Define the function globally
function redirectToPage() {
    window.location.href = "result.html"; 
}
