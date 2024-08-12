document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('signIn');
    const usernameInput = document.getElementById('username');
    const usernameRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[~`!@#$%^&*()-_+={}|\;:"<>,./?])[A-Za-z\d~`!@#$%^&*()-_+={}|\;:"<>,./?]{4,8}$/;

    const passwordInput = document.getElementById('password');
    const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[~`!@#$%^&*()-_+={}|\;:"<>,./?])[A-Za-z\d~`!@#$%^&*()-_+={}|\;:"<>,./?]{6,12}$/;

    const emailInput = document.getElementById('email');
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    function validateInput(input, regex) {
        input.classList.remove('is-valid', 'is-invalid')
        if (regex.test(input.value)) {
            input.classList.add('is-valid');
        } else {
            input.classList.add('is-invalid');
        }
    }

    emailInput.addEventListener('input', function(){
        validateInput(emailInput, emailRegex);
    })
    usernameInput.addEventListener('input', function() {
        validateInput(usernameInput, usernameRegex);
    });

    passwordInput.addEventListener('input', function() {
        validateInput(passwordInput, passwordRegex);
    });

    form.addEventListener('submit', function(event) {
        event.preventDefault();

        // validateInput(usernameInput, usernameRegex);
        // validateInput(passwordInput, passwordRegex);

        const isUsernameValid = usernameRegex.test(usernameInput.value);
        const isPasswordValid = passwordRegex.test(passwordInput.value);
        const isEmailValid = emailRegex.test(emailInput.value);

        if (isUsernameValid && isPasswordValid && isEmailValid) {
            const actionUrl = form.getAttribute('action'); // Get the action URL
            window.location.href = actionUrl;
        } 
        
    });



    //Toggle_down
    function toggleDown(button, menu, allMenus) {
        button.addEventListener('click', function(event) {
            event.preventDefault();
            event.stopPropagation();
    
            // Close all other menus
            allMenus.forEach(function(otherMenu) {
                if (otherMenu !== menu && !otherMenu.classList.contains('d-none')) {
                    otherMenu.classList.add('d-none');
                    otherMenu.classList.remove('d-block');
                }
            });
    
            // Toggle the visibility of the current menu
            if (menu.classList.contains('d-none')) {
                menu.classList.remove('d-none');
                menu.classList.add('d-block');
            } else {
                menu.classList.add('d-none');
                menu.classList.remove('d-block');
            }
        });
    }

    
    
    // Initialize dropdowns
    const userButton = document.getElementById('user');
    const userMenu = document.querySelector('.userDetail');
    const notifyButton = document.getElementById('notify');
    const notifyMenu = document.querySelector('.notifyDetail');
    const cartButton = document.getElementById('cart');
    const cartMenu = document.querySelector('.cartDetail');

    const allMenus=[userMenu, notifyMenu, cartMenu];
    
    // Pass an array of other menus to the toggleDown function
    toggleDown(userButton, userMenu, allMenus);
    toggleDown(notifyButton, notifyMenu, allMenus);
    toggleDown(cartButton, cartMenu, allMenus);
    
    // Close the menu if clicking outside of it
    document.addEventListener('click', function(event) {
        if (allMenus.forEach(function(menu){!menu.contains(event.target) && !button.contains(event.target)})){
            allMenus.forEach(function(menu) {
                menu.classList.add('d-none');
                menu.classList.remove('d-block');
            });
        }
    });
    // document.addEventListener('click', function(event) {
    //     if (!userMenu.contains(event.target) && !notifyMenu.contains(event.target) && !userButton.contains(event.target) && !notifyButton.contains(event.target)) {
    //         userMenu.classList.add('d-none');
    //         userMenu.classList.remove('d-block');
    //         notifyMenu.classList.add('d-none');
    //         notifyMenu.classList.remove('d-block');
    //     }
    // });
    
});

document.getElementById('login').addEventListener('submit', function(event) {
    event.preventDefault();

    const username = document.getElementById('username').value;

    fetch('/api/check-username', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username: username })
    })
    .then(response => response.json())
    .then(data => {
        if (data.exists) {
            // Username exists, proceed with login
            window.location.href = '/dashboard';
        } else {
            // Username does not exist, show an error message
            document.getElementById('error-message').style.display = 'block';
        }
    })
    .catch(error => {
        console.error('Error:', error);
    });
});


const box = document.querySelector('.moving-box');
        let position = 0;

        function moveBox() {
            position += 1;
            box.style.left = position + 'px';

            if (position < 200) {
                requestAnimationFrame(moveBox); // Animate until 200px
            }
        }

        moveBox(); 





