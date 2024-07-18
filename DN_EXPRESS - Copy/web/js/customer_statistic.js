
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




// TOGGLE SIDEBAR
const menuBar = document.querySelector('#content nav .bx.bx-menu');
const sidebar = document.getElementById('sidebar');

menuBar.addEventListener('click', function () {
	sidebar.classList.toggle('hide');
})







const searchButton = document.querySelector('#content nav form .form-input button');
const searchButtonIcon = document.querySelector('#content nav form .form-input button .bx');
const searchForm = document.querySelector('#content nav form');

searchButton.addEventListener('click', function (e) {
	if(window.innerWidth < 576) {
		e.preventDefault();
		searchForm.classList.toggle('show');
		if(searchForm.classList.contains('show')) {
			searchButtonIcon.classList.replace('bx-search', 'bx-x');
		} else {
			searchButtonIcon.classList.replace('bx-x', 'bx-search');
		}
	}
})





if(window.innerWidth < 768) {
	sidebar.classList.add('hide');
} else if(window.innerWidth > 576) {
	searchButtonIcon.classList.replace('bx-x', 'bx-search');
	searchForm.classList.remove('show');
}


window.addEventListener('resize', function () {
	if(this.innerWidth > 576) {
		searchButtonIcon.classList.replace('bx-x', 'bx-search');
		searchForm.classList.remove('show');
	}
})



const switchMode = document.getElementById('switch-mode');

switchMode.addEventListener('change', function () {
	if(this.checked) {
		document.body.classList.add('dark');
	} else {
		document.body.classList.remove('dark');
	}
})

const viewButtons = document.querySelectorAll('.viewInforBtn');
viewButtons.forEach(button => {
	button.addEventListener('click', redirectToPage);
});

function redirectToPage() {
    window.location.href = "result.html"; 
}

// Drawing growing dash board

const growingData = {
	labels: [
		'Service 1',
		'Service 2',
		'Service 3',
		'Service 4',
		'Service 5'
	],
	datasets: [{
		label: 'Growing of month',
		data: [11, 16, 7, 3, 14],
		backgroundColor: [
			'rgb(255, 99, 132)',
			'rgb(75, 192, 192)',
			'rgb(255, 205, 86)',
			'rgb(201, 203, 207)',
			'rgb(54, 162, 235)'
		]
	}]
};

const growingConfig = {
	type: 'polarArea',
	data: growingData,
	options: {
		responsive: true
	}
};

const growingChart = new Chart(
	document.getElementById('myGrowingChart'),
	growingConfig
);

});



document.addEventListener('DOMContentLoaded', function () {
    const yearSelect = document.getElementById('yearSelect');
    const myChartCanvas = document.getElementById('myChart');
    let myChart = null;


    function updateGraph(year) {
        fetch(`/DN_EXPRESS/Customer_Statistic_Json_Servlet?year=${year}`)
                .then(response => response.json())
                .then(data => {
//                    console.log(data);

                    const {overallStats, monthlyStats} = data;

                    // Update order progress circle
                    const orderCircle = document.querySelector('.orders .progress svg circle');
                    orderCircle.style.strokeDasharray = overallStats.strokeDasharray;
                    orderCircle.style.strokeDashoffset = overallStats.orderStrokeDashoffset;
                    document.querySelector('.orders .progress .number p').textContent = Math.round(overallStats.orderPercentage) + '%';

                    // Update ratings progress circle
                    const ratingCircle = document.querySelector('.ratings .progress svg circle');
                    ratingCircle.style.strokeDasharray = overallStats.strokeDasharray;
                    ratingCircle.style.strokeDashoffset = overallStats.ratingStrokeDashoffset;
                    document.querySelector('.ratings .progress .number p').textContent = Math.round(overallStats.ratingPercentage) + '%';

                    // Assuming there's an income progress circle to update as well
//             const incomeCircle = document.querySelector('.income .progress svg circle');
//             incomeCircle.style.strokeDasharray = overallStats.strokeDasharray;
//             incomeCircle.style.strokeDashoffset = overallStats.incomeStrokeDashoffset;
//             document.querySelector('.income .progress .number p').textContent = Math.round(overallStats.incomePercentage) + '%';


                })
                .catch(error => console.error('Error fetching data:', error));
    }

    // Function to fetch data and update chart
    function updateChart(year) {
        fetch(`/DN_EXPRESS/Customer_Statistic_Json_Servlet?year=${year}`)
                .then(response => response.json())
                .then(data => {
                console.log(data);
                    const {overallStats, monthlyStats} = data;

                    //Drawing dashboard statistics
                    const labels = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
                    const graph = {
                        labels: labels,
                        datasets: [{
                                label: 'Order/Month',
                                data: monthlyStats.map(item => item.orderCount),
                                borderColor: '#3ac5ce',
                                borderWidth: 2,
                                backgroundColor: 'white'
                            }, {
                                label: 'Rating/Month',
                                data: monthlyStats.map(item => item.avgRating),
                                borderColor: '#ffce26',
                                borderWidth: 2,
                                backgroundColor: 'white'
                            }, {
                                label: 'Expense/Month',
                                data: monthlyStats.map(item => item.totalAmount),
                                borderColor: '#fd7238',
                                borderWidth: 2,
                                backgroundColor: 'white'
                            }]
                    };

                    const config = {
                        type: 'line',
                        data: graph,
                        options: {
                            responsive: true
                        }
                    };

                    // Destroy the previous chart instance if it exists
                    if (myChart) {
                        myChart.destroy();
                    }

                    // Create new Chart.js instance
                    myChart = new Chart(myChartCanvas, config);

                })
                .catch(error => console.error('Error fetching data:', error));
    }

    // Update chart for the default selected year
    const defaultYear = yearSelect.value;
    updateChart(defaultYear);
    updateGraph(defaultYear);

    // Update chart on change
    yearSelect.addEventListener('change', function () {
        const selectedYear = yearSelect.value;
        updateChart(selectedYear);
        
    });

});