
const navEl = document.querySelector('.navbar');
const brandEl = document.querySelector('.brand');
window.addEventListener('scroll',() =>{
	if(window.scrollY >= 56){
		navEl.classList.remove('navbar-normal');
		navEl.classList.add('navbar-scrolled');

		brandEl.classList.remove('brand-normal');
		brandEl.classList.add('brand-scrolled');
	}
	if(window.scrollY==0){
		navEl.classList.remove('navbar-scrolled');
		navEl.classList.add('navbar-normal');

		brandEl.classList.remove('brand-scrolled');
		brandEl.classList.add('brand-normal');
	}
});

