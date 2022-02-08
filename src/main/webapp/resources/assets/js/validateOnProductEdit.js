let form = document.querySelector('#form');
const error = document.querySelector('#error');
const name = document.querySelector('#name');
const body = document.querySelector('#body');
const fret = document.querySelector('#fret');
const scale = document.querySelector('#scale');
const fretAmount = document.querySelector('#fretAmount');
const price = document.querySelector('#price');
const formInputs = document.querySelectorAll('.to-validate');

form.onsubmit = function () {
    const emptyInputs = Array.from(formInputs).filter(input => input.value === '');
    formInputs.forEach(function (input) {
        if (input.value === '') {
            input.classList.add('is-invalid');
        } else {
            input.classList.remove('is-invalid');
        }
    })

    if (emptyInputs.length > 0) {
        return false;
    }

    valid(name, error, '');
    valid(body, error, '');
    valid(fret, error, '');

    if (!validateNumber(scale)) {
        notValid(scale, error, 'Scale must be a number');
        return false;
    } else {
        valid(scale, error, '');
    }

    if (!validateNumber(fretAmount)) {
        notValid(fretAmount, error, 'Fret amount must be a number');
        return false;
    } else {
        valid(fretAmount, error, '');
    }

    if (!validateFloat(price)) {
        notValid(price, error, 'Price must be a number (x.xx or xxx)');
        return false;
    } else {
        valid(price, error, '');
    }

    return true;
}

function notValid(inp, el, mess) {
    inp.classList.add('is-invalid');
    el.innerHTML = mess;
}

function valid (inp, el, mess) {
    inp.classList.remove('is-invalid');
    inp.classList.add('is-valid');
    el.innerHTML = mess;
}

function validateNumber(inp) {
    return /^\d+$/.test(inp.value);
}

function validateFloat(inp) {
    return /^\d+\.\d+$/.test(inp.value) || /^\d+$/.test(inp.value);
}
