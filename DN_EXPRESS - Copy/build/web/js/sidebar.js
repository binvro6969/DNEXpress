document.addEventListener('DOMContentLoaded', (event) => {
	//--------------------------------------------------------------------------------//
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
});

