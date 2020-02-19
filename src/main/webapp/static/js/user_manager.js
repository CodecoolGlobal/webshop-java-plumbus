import {post_fetch} from "./post_fetch.js";


export let userManager = {
    main :function () {
        function _logIn() {
            async function check_form() {
                let form = new FormData(document.getElementById('login_form'));
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
                let form_data = await check_form();
                if (form_data['password_legit']) {
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
            let loginForm = document.getElementById('login_form');
            let loginButton = document.getElementById('login_button');
            let loginSubmit = document.getElementById('login_submit');
            let loginError = document.getElementById('login_error');
            let close = document.getElementById('close_modal');
            let user = document.getElementById('user');
            let registerButton = document.getElementById('register_button');
            loginSubmit.addEventListener('click', submit);
            loginButton.addEventListener('click', show);
            close.addEventListener('click', hide);
        }
        function _register() {
            async function check_registration() {
                let regForm = new FormData(document.getElementById('register_form'));
                return await post_fetch.fetchIt('/registration', Object.fromEntries(regForm.entries()));
            }
            async function submit() {
                let form_data = await check_registration();
                console.log(form_data);
                if (form_data['registration_legit']) {
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

            let registerForm = document.getElementById('register_form');
            let registerButton = document.getElementById('register_button');
            let registerSubmit = document.getElementById('register_submit');
            let registerError = document.getElementById('register_error');
            let close = document.getElementById('close_modal');
            close.addEventListener('click', hide);
            registerButton.addEventListener('click', show);
            registerSubmit.addEventListener('click', submit)

        }
        _logIn();
        _register();
    }


};