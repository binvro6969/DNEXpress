


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
        if (window.innerWidth < 576) {
            e.preventDefault();
            searchForm.classList.toggle('show');
            if (searchForm.classList.contains('show')) {
                searchButtonIcon.classList.replace('bx-search', 'bx-x');
            } else {
                searchButtonIcon.classList.replace('bx-x', 'bx-search');
            }
        }
    })





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
    })



    const switchMode = document.getElementById('switch-mode');

    switchMode.addEventListener('change', function () {
        if (this.checked) {
            document.body.classList.add('dark');
        } else {
            document.body.classList.remove('dark');
        }
    })

    //Size description
    const radioButtons = document.querySelectorAll('input[name="select-size"]');
    const sizeDescription = document.getElementById('size-description');

    radioButtons.forEach((radioButton) => {
        radioButton.addEventListener('change', () => {
            if (radioButton.checked) {
                var radioValue = radioButton.value;
                var radioDes;

                switch (radioValue) {
                    case "1":
                        radioDes = "(L) 25cm x (W) 20cm x (H) 20cm";
                        break;
                    case "2":
                        radioDes = "(L) 50cm x (W) 50cm x (H) 40cm";
                        break;
                    case "3":
                        radioDes = "(L) 70cm x (W) 60cm x (H) 60cm";
                        break;
                    case "4":
                        radioDes = "(L) 170cm x (W) 120cm x (H) 100cm";
                        break;
                    default:
                        radioDes = "";
                }
                sizeDescription.textContent = radioDes;
            }

        });
    });

    //Warranty description
    const warrantyDiv = document.getElementById('select-warranty');
    const warrantyDescription = document.getElementById('warranty-description');
    warrantyDiv.addEventListener('change', function () {
        var warrantyDes;

        switch (warrantyDiv.value) {
            case "1":
                warrantyDes = "Warranty 20% of package's value";
                break;
            case "2":
                warrantyDes = "Warranty 50% of package's value";
                break;
            case "3":
                warrantyDes = "Warranty 80% of package's value";
                break;
            default:
                warrantyDes = "";
        }

        warrantyDescription.textContent = warrantyDes;
    });

    //Date time picker



    const config = {
        enableTime: true,
        defaultDate: getCurrentDateTime(),
        minDate: "today",
        minTime: getCurrentTime(),
        onChange: function (selectedDates, dateStr, instance) {
            const now = new Date();
            const selectedDate = selectedDates[0];

            if (selectedDate && selectedDate.toDateString() === now.toDateString()) {
                instance.set('minTime', getCurrentTime());
            } else {
                instance.set('minTime', '00:00');
            }
        }
    };

    const configPlus = {
        enableTime: true,
        defaultDate: getCurrentDateTimePlus(),
        minDate: "today",
        minTime: getCurrentTime(),
        onChange: function (selectedDates, dateStr, instance) {
            const now = new Date();
            const selectedDate = selectedDates[0];

            if (selectedDate && selectedDate.toDateString() === now.toDateString()) {
                instance.set('minTime', getCurrentTimePlus());
            } else {
                instance.set('minTime', '00:00');
            }
        }
    };



    flatpickr("#req-date", config);
    flatpickr("#rec-date", configPlus);
    flatpickr("#created-date", config);


    const specificDate = document.getElementById('type-shipment');
    const specificDiv = document.getElementById('specific-type');
    specificDate.addEventListener('change', function () {
        if (specificDate.value === '4') {
            specificDiv.hidden = false;
        } else {
            specificDiv.hidden = true;
        }
    });



    //Later time
    const timeDropdown = document.getElementById('select-date');
    const dateDiv = document.getElementById('req-date');

    timeDropdown.addEventListener('change', function () {
        if (timeDropdown.value === '2') {
            dateDiv.hidden = false;
        } else {
            dateDiv.hidden = true;
        }
    });




    deliveryOptions.addEventListener('change', () => calculateTotal(distance));
    typeOptions.addEventListener('change', () => calculateTotal(distance));
    warrantyOptions.addEventListener('change', () => calculateTotal(distance));
    document.querySelectorAll('input[name="select-size"]').forEach(radio => radio.addEventListener('change', () => calculateTotal(distance)));
    document.getElementById('true-value').addEventListener('input', () => calculateTotal(distance));
    document.getElementById('weight').addEventListener('input', () => calculateTotal(distance));
    calculateTotal(distance);



});


const totalMoneyLabel = document.getElementById('total-money');
const deliveryOptions = document.getElementById('type-shipment');
const sizeOptions = document.querySelectorAll('input[name="select-size"]');
const typeOptions = document.getElementById('type-package');
const warrantyOptions = document.getElementById('select-warranty');
const trueValueInput = document.getElementById('true-value');
const weightInput = document.getElementById('weight');

let distance = 0;


function calculateTotal(distance) {
    const deliveryPrice = parseInt(deliveryOptions.selectedOptions[0].getAttribute('data-price'));
    const typePrice = parseInt(typeOptions.selectedOptions[0].getAttribute('data-price'));
    const warrantyPrice = parseFloat(warrantyOptions.selectedOptions[0].getAttribute('data-price'));

    const selectedSize = document.querySelector('input[name="select-size"]:checked');
    const sizePrice = selectedSize ? parseInt(selectedSize.getAttribute('data-price')) : 0;

    const trueValue = parseFloat(trueValueInput.value.replace(/,/g, '')) || 0;
    var weightValue = parseFloat(weightInput.value);

    if (isNaN(weightValue)) {
        weightValue = 0;
    }
    console.log(weightValue);


    var distanceCost = 0;
    var pricePerKm = 0;
    var additionalWeightCost = Math.ceil(weightValue / 0.5) * 5000;


    if (distance > 100) {
        pricePerKm = 30;
        distanceCost = distance * pricePerKm + additionalWeightCost;
    } else {
        pricePerKm = 3000;
        distanceCost = distance * pricePerKm;
    }

    let total = deliveryPrice + sizePrice + typePrice + distanceCost + trueValue * (warrantyPrice / 100);

    console.log("Delivery: " + deliveryPrice + " Size: " + sizePrice + " Type: " + typePrice + " Warranty: " + warrantyPrice + " Distance: " + distanceCost + " True Value: " + trueValue);

    totalMoneyLabel.textContent = new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(total);

    const totalValueInput = document.getElementById('totalValue');
    totalValueInput.value = total;

    const serviceFee = total - distanceCost;
    const serviceFeeInput = document.getElementById('serviceFee');
    serviceFeeInput.value = serviceFee;

    console.log("Service: " + serviceFee);
}

function getCurrentDateTime() {
    const now = new Date();
    if (now.getSeconds() === 0) {
        now.setMinutes(now.getMinutes() + 1);
    }

    const year = now.getFullYear();
    const month = String(now.getMonth() + 1).padStart(2, '0');
    const day = String(now.getDate()).padStart(2, '0');
    const hours = String(now.getHours()).padStart(2, '0');
    const minutes = String(now.getMinutes()).padStart(2, '0');

    return `${year}-${month}-${day} ${hours}:${minutes}`;
}

function getCurrentTime() {
    const now = new Date();
    const hours = String(now.getHours()).padStart(2, '0');
    const minutes = String(now.getMinutes()).padStart(2, '0');
    return `${hours}:${minutes}`;
}

function getCurrentDateTimePlus() {
    const now = new Date();
    now.setMinutes(now.getMinutes() + 15);  // Add 15 minutes to the current time

    const year = now.getFullYear();
    const month = String(now.getMonth() + 1).padStart(2, '0');
    const day = String(now.getDate()).padStart(2, '0');
    const hours = String(now.getHours()).padStart(2, '0');
    const minutes = String(now.getMinutes()).padStart(2, '0');

    return `${year}-${month}-${day}T${hours}:${minutes}`;
}

function getCurrentTimePlus() {
    const now = new Date();
    now.setMinutes(now.getMinutes() + 15);
    const hours = String(now.getHours()).padStart(2, '0');
    const minutes = String(now.getMinutes()).padStart(2, '0');
    return `${hours}:${minutes}`;
}


let startAutocomplete, endAutocomplete;
let place1, place2;
function initAutocomplete() {
    startAutocomplete = new google.maps.places.Autocomplete(
            document.getElementById('startAutocomplete'),
            {
                componentRestrictions: {'country': ['VN']},
                fields: ['address_components', 'geometry', 'name']
            }
    );

    endAutocomplete = new google.maps.places.Autocomplete(
            document.getElementById('endAutocomplete'),
            {
                componentRestrictions: {'country': ['VN']},
                fields: ['address_components', 'geometry', 'name']
            }
    );

    startAutocomplete.addListener('place_changed', () => onPlaceChanged(startAutocomplete, 1));
    endAutocomplete.addListener('place_changed', () => onPlaceChanged(endAutocomplete, 2));
}

function formatAddress(addressComponents) {
    const address = {
        streetNumber: '',
        streetName: '',
        premise: '',
        ward: '',
        district: '',
        city: '',
        country: ''
    };

    addressComponents.forEach(component => {
        const types = component.types;
        console.log('Component types:', types);
        if (types.includes('street_number')) {
            address.streetNumber = component.long_name;
        } else if (types.includes('route')) {
            address.streetName = component.long_name;
        } else if (types.includes('neighborhood') || types.includes('sublocality')) {
            address.ward += component.long_name + ', ';
        } else if (types.includes('premise ')) {
            address.premise += component.long_name + ', ';
        } else if (types.includes('administrative_area_level_2')) {
            address.district = component.long_name;
        } else if (types.includes('administrative_area_level_1')) {
            address.city = component.long_name;
        } else if (types.includes('country')) {
            address.country = component.long_name;
        }
    });

    // Remove trailing comma and space from ward
    address.ward = address.ward.replace(/,\s*$/, '');

    return address;
}

function onPlaceChanged(autocomplete, index) {
    const place = autocomplete.getPlace();

    if (!place.geometry) {
        document.getElementById(`autocomplete${index}`).placeholder = 'Enter a place';
        return;
    }



    console.log(place.address_components);
    const formattedAddress = formatAddress(place.address_components);
    const completeAddress = `${formattedAddress.streetNumber} ${formattedAddress.streetName}, ${formattedAddress.ward}, ${formattedAddress.district}, ${formattedAddress.city}, ${formattedAddress.country}`.replace(/(^\s*,)|(,\s*$)/g, '');


    const form = document.getElementById('frmCreateOrder');


    // Remove existing hidden inputs for this address
    const existingInputs = form.querySelectorAll(`.hidden-address-input-${index}`);
    existingInputs.forEach(input => input.remove());

    // Create and append hidden inputs for this address
    Object.keys(formattedAddress).forEach(key => {
        const input = document.createElement('input');
        input.type = 'hidden';
        input.name = `${key}-${index}`;
        input.value = formattedAddress[key];
        input.classList.add(`hidden-address-input-${index}`);
        form.appendChild(input);
    });

    if (index === 1) {
        place1 = {...place, formattedAddress, completeAddress};
        console.log('Place 1:', place1);
    } else {
        place2 = {...place, formattedAddress, completeAddress};
        console.log('Place 2:', place2);
    }

    if (place1 && place2) {
        calculateDistance(place1, place2);
    }
}



function calculateDistance(originPlace, destinationPlace) {
    const origin = `${originPlace.geometry.location.lat()},${originPlace.geometry.location.lng()}`;
    const destination = `${destinationPlace.geometry.location.lat()},${destinationPlace.geometry.location.lng()}`;

    fetch(`Customer_CreateOrder_GetDistance_Servlet?origins=${origin}&destinations=${destination}`)
            .then(response => response.json())
            .then(data => {
                // Check if the response is valid and contains distances
                if (data.rows[0].elements[0].status === "OK") {
                    const distanceText = data.rows[0].elements[0].distance.text;
                    distance = parseFloat(data.rows[0].elements[0].distance.value) / 1000;
                    document.getElementById('distance').innerHTML = `Distance: ${distanceText}`;

                    let inProvince = false;
                    const originCity = originPlace.address_components.find(comp => comp.types.includes('administrative_area_level_1')).long_name;
                    const destinationCity = destinationPlace.address_components.find(comp => comp.types.includes('administrative_area_level_1')).long_name;

                    if (distance <= 50 || originCity === destinationCity) {
                        inProvince = true;
                    }

                    // Set the value of inProvince in your form or pass it to your backend function
                    document.getElementById('inProvince').value = inProvince;
                    console.log(inProvince);

                    calculateTotal(distance);
                } else {
                    document.getElementById('distance').innerHTML = 'Distance: Not available';
                }
            })
            .catch(error => console.error('Error fetching directions:', error));
}

function showImage(event) {
    const input = event.target;
    const file = input.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = function (e) {
            const dropArea = document.getElementById('drop-area');
            const imgView = document.getElementById('img-view');
            const img = document.getElementById('pic');

            img.src = e.target.result;
            img.hidden = false;

            // Remove all other children of imgView except the image
            while (imgView.firstChild) {
                imgView.removeChild(imgView.firstChild);
            }

            // Append the image to imgView
            imgView.appendChild(img);
        };
        reader.readAsDataURL(file);
    }
}

//Handle error inputs
const nameInput = document.querySelectorAll('.name-input');
const nameError = document.querySelectorAll('.name-error');

const phoneInput = document.querySelectorAll('.phone-input');
const phoneError = document.querySelectorAll('.phone-error');

const weightError = document.getElementById("weight-error");
const valueError = document.getElementById("value-error");

const confirmButton = document.getElementById('submit'); // Replace with your actual button ID

// Function to validate name input
function validateNameInput(input, errorElement) {
    const value = input.value.trim();
    if (value === '') {
        errorElement.hidden = false;
        errorElement.innerHTML = 'Name must not be empty';
        return false;
    } else if (!/^[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơưẠ-ỹ ]+$/.test(value)) {
        errorElement.hidden = false;
        errorElement.innerHTML = 'Name must not contain numbers and special characters';
        return false;
    } else {
        errorElement.hidden = true;
        errorElement.innerHTML = '';
        return true;
    }
}

// Function to validate phone input
function validatePhoneInput(input, errorElement) {
    const value = input.value.trim();
    if (value === '') {
        errorElement.hidden = false;
        errorElement.innerHTML = 'Phone must not be empty';
        return false;
    } else if (!/^[0-9]+$/.test(value)) {
        errorElement.hidden = false;
        errorElement.innerHTML = 'Phone must contain only numbers';
        return false;
    } else if (!/^0/.test(value)) {
        errorElement.hidden = false;
        errorElement.innerHTML = 'Phone must start with 0';
        return false;
    } else if (!/^[0-9]{10}$/.test(value)) {
        errorElement.hidden = false;
        errorElement.innerHTML = 'Phone must be 10 digits';
        return false;
    } else {
        errorElement.hidden = true;
        errorElement.innerHTML = '';
        return true;
    }
}

// Event listeners for name and phone inputs
nameInput.forEach((input, index) => {
    input.addEventListener('input', () => {
        validateNameInput(input, nameError[index]);
        updateConfirmButtonState(); // Update button state after input change
    });
});

phoneInput.forEach((input, index) => {
    input.addEventListener('input', () => {
        validatePhoneInput(input, phoneError[index]);
        updateConfirmButtonState(); // Update button state after input change
    });
});

// Event listener for weight input
weightInput.addEventListener('input', function () {
    const numericValue = parseFloat(this.value);

    if (numericValue < 0) {
        weightError.hidden = false;
        weightError.innerHTML = 'Weight cannot be negative';
    } else if (numericValue === 0) {
        weightError.hidden = false;
        weightError.innerHTML = 'Weight cannot be 0';
    } else if (numericValue >= 1000) {
        weightError.hidden = false;
        weightError.innerHTML = 'Weight cannot exceed 1000kg';
    } else {
        weightError.hidden = true;
        weightError.innerHTML = '';
    }

    updateConfirmButtonState(); // Update button state after input change
});

// Event listener for true value input
trueValueInput.addEventListener('input', function () {
    const valueString = this.value.trim().replace(/,/g, ''); // Remove spaces and commas
    const value = parseFloat(valueString);

    if (valueString === '' || isNaN(value)) {
        valueError.hidden = false;
        valueError.innerHTML = 'Value must be numbers';
    } else {
        if (value < 0) {
            valueError.hidden = false;
            valueError.innerHTML = 'Value cannot be negative';
        } else if (value >= 1000000000) {
            valueError.hidden = false;
            valueError.innerHTML = 'Value cannot be too high';
        } else {
            valueError.hidden = true;
            valueError.innerHTML = '';
        }
    }

    updateConfirmButtonState(); // Update button state after input change
});

// Function to update confirm button state based on input validity
function updateConfirmButtonState() {
    const isNameValid = Array.from(nameInput).every(input => validateNameInput(input, nameError));
    const isPhoneValid = Array.from(phoneInput).every(input => validatePhoneInput(input, phoneError));
    const isWeightValid = validateWeightInput();
    const isValueValid = validateTrueValueInput();

    // Enable the confirm button only if all validations pass
    confirmButton.disabled = !(isNameValid && isPhoneValid && isWeightValid && isValueValid);
}

// Function to validate weight input
function validateWeightInput() {
    const numericValue = parseFloat(weightInput.value);
    if (numericValue < 0) {
        weightError.hidden = false;
        weightError.innerHTML = 'Weight cannot be negative';
        return false;
    } else if (numericValue === 0) {
        weightError.hidden = false;
        weightError.innerHTML = 'Weight cannot be 0';
        return false;
    } else if (numericValue >= 1000) {
        weightError.hidden = false;
        weightError.innerHTML = 'Weight cannot exceed 1000kg';
        return false;
    } else {
        weightError.hidden = true;
        weightError.innerHTML = '';
        return true;
    }
}

// Function to validate true value input
function validateTrueValueInput() {
    const valueString = trueValueInput.value.trim().replace(/,/g, ''); // Remove spaces and commas
    const value = parseFloat(valueString);

    if (valueString === '' || isNaN(value)) {
        valueError.hidden = false;
        valueError.innerHTML = 'Value must be numbers';
        return false;
    } else {
        if (value < 0) {
            valueError.hidden = false;
            valueError.innerHTML = 'Value cannot be negative';
            return false;
        } else if (value >= 1000000000) {
            valueError.hidden = false;
            valueError.innerHTML = 'Value cannot be too high';
            return false;
        } else {
            valueError.hidden = true;
            valueError.innerHTML = '';
            return true;
        }
    }
}

