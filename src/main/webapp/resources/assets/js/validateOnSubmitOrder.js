let form = document.querySelector('#form');
let address = document.querySelector('#address');
let error = document.querySelector('#error');

form.onsubmit = function () {
    if (address.value === '') {
        address.classList.add('is-invalid');
        error.innerHTML = 'This information should be filled';
        return false;
    } else {
        address.classList.remove('is-invalid');
        error.innerHTML = '';
    }
}