(() => {
  'use strict'
  const forms = document.querySelectorAll('.needs-validation');

  // Prevent submission
  Array.from(forms).forEach(form => {
    form.addEventListener('submit', event => {
      if (!form.checkValidity()) {
        event.preventDefault()
        event.stopPropagation()
      }
    }, false);
  })

  const registerBtn = document.getElementById('register');
  registerBtn.addEventListener('click', () => {
    Array.from(forms).forEach(form => {
      form.classList.add('was-validated')
    })
    printToConsole();
  })

  function printToConsole() {
    console.log("Application submitted");
    console.warn("Connection is slow");

    throw new Error("Deliberate error");
  }

  const inputs = document.getElementsByClassName('form-control');
  const clearBtn = document.getElementById('clear');
  clearBtn.addEventListener('click', () => {
    if (confirm('This will clear all inputs. Continue?')) {
      Array.from(inputs).forEach(input => input.value = '');
    }

  });

  const saveBtn = document.getElementById('save');
  saveBtn.addEventListener('click', () => {
    Array.from(inputs).forEach(input => {
      sessionStorage.setItem(input.getAttribute('id'), input.value);
    });
  });

  document.addEventListener("DOMContentLoaded", function () {

    Array.from(inputs).forEach(input => {
      let storedValue = sessionStorage.getItem(input.getAttribute('id'));
      if (storedValue) {
        input.value = storedValue;
      }
    });

    // can be removed now
    // let first = document.getElementById('firstName');
    // let last = document.getElementById('lastName');
    // let email = document.getElementById('email');
    // let dob = document.getElementById('dob');
    // if(first) {first.value = sessionStorage.getItem('firstName');}
    // if(last) {last.value = sessionStorage.getItem('lastName');}
    // if(email) {email.value = sessionStorage.getItem('email');}
    // if(dob) {dob.value = sessionStorage.getItem('dob');}
  });



})()



$(document).ready(function () {

  $('#heard-about').click(function () {
    if ($("#textarea").attr("disabled")) {
      $('#textarea').removeAttr("disabled");
    } else {
      $('#textarea').prop('disabled', true);
      $('#textarea').val("");
    }
  });
});