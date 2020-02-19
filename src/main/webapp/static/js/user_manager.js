import {post_fetch} from "./post_fetch.js";


export let userManager = {
    main :function () {
        function _logIn() {
            async function checkForm() {
                let form = new FormData(document.getElementById('loginForm'));
                return await post_fetch.fetchIt('/login', Object.fromEntries(form.entries()));
            }
            function logOut() {
                loginButton.removeEventListener('click', logOut);
                loginButton.addEventListener('click', show);
                loginButton.innerText = 'Log in';
                registerButton.setAttribute('class', '');
                user.innerText = ''
            }
            async function submit() {
                let form_data = await checkForm();
                if (form_data['passwordLegit']) {
                    loginButton.removeEventListener('click', show);
                    loginButton.addEventListener('click', logOut);
                    loginButton.innerText = 'Log out';
                    user.innerText = 'Welcome ' + form_data['name'];
                    hide();
                    registerButton.setAttribute('class', 'hide')
                } else {
                    loginError.innerText = 'Invalid username or password'
                }
            }
            function show() {
                loginForm.setAttribute('class', '');
                $('.modal').modal('show');

            }
            function hide() {
                loginForm.reset();
                loginError.innerText = '';
                $('.modal').modal('hide');
                loginForm.setAttribute('class', 'hide');
            }
            let loginForm = document.getElementById('loginForm');
            let loginButton = document.getElementById('loginButton');
            let loginSubmit = document.getElementById('loginSubmit');
            let loginError = document.getElementById('loginError');
            let close = document.getElementById('close_modal');
            let user = document.getElementById('user');
            let registerButton = document.getElementById('registerButton');
            loginSubmit.addEventListener('click', submit);
            loginButton.addEventListener('click', show);
            close.addEventListener('click', hide);
        }
        function _register() {
            async function checkRegistration() {
                let regForm = new FormData(document.getElementById('registerForm'));
                return await post_fetch.fetchIt('/registration', Object.fromEntries(regForm.entries()));
            }
            async function submit() {
                let form_data = await checkRegistration();
                if (form_data['registrationLegit']) {
                    hide();
                registerForm.setAttribute('class', 'hide')
                } else {
                    registerError.innerText = 'Username already in use'
                }
            }
            function show() {
                registerForm.setAttribute('class', '');
                $('.modal').modal('show')
            }
            function hide() {
                registerForm.reset();
                registerForm.setAttribute('class', 'hide');
                registerError.innerText = '';
                $('.modal').modal('hide');
            }

            let registerForm = document.getElementById('registerForm');
            let registerButton = document.getElementById('registerButton');
            let registerSubmit = document.getElementById('registerSubmit');
            let registerError = document.getElementById('registerError');
            let close = document.getElementById('close_modal');
            close.addEventListener('click', hide);
            registerButton.addEventListener('click', show);
            registerSubmit.addEventListener('click', submit)

        }
        _logIn();
        _register();
    }


};