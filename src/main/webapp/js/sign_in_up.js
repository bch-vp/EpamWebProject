const app = new Vue({
    vuetify: new Vuetify(),
    el: '#app',
    created(){
        if(signInErrorMessage) {
            this.isSignIn = true
        }
    },
    data(){
        return {
            signInErrorMessage: signInErrorMessage,
            isHome: false,
            isSignIn: false,
            isSignUp: false,
            signInData: {
                value: String,
                valid: false,
                login: '',
                password: '',
            },
            signUpData: {
                value: String,
                valid: false,
                login: '',
                password: '',
                name: '',
                surname: '',
                telephoneNumber: '',
                email: '',
            },
            signInRules: {
                login: [
                    v => !!v || 'Login is required',
                    v => /.{3,}/.test(v) || 'Minimum three in length',
                    v => (v && v.length <= 10) || 'Login must be less than 10 characters',
                    v => /^\S*$/.test(v) || 'Spaces are prohibited',
                ],
                password: [
                    v => !!v || 'Password is required',
                    v => /.{5,}/.test(v) || 'Minimum five in length',
                    v => (v && v.length <= 10) || 'Password must be less than 10 characters',
                    v => /^\S*$/.test(v) || 'Spaces are prohibited',
                    v => /(?=.*?[a-z])/.test(v) || 'At least one lower case English letter',
                    v => /(?=.*?[A-Z])/.test(v) || 'At least one upper case English letter',
                    v => /(?=.*?[0-9])/.test(v) || 'At least one digit',
                ],
            },
            signUpRules: {
                login: [
                    v => !!v || 'Login is required',
                    v => /.{3,}/.test(v) || 'Minimum three in length',
                    v => (v && v.length <= 10) || 'Login must be less than 10 characters',
                    v => /^\S*$/.test(v) || 'Spaces are prohibited',
                ],
                password: [
                    v => !!v || 'Password is required',
                    v => /.{5,}/.test(v) || 'Minimum five in length',
                    v => (v && v.length <= 10) || 'Password must be less than 10 characters',
                    v => /^\S*$/.test(v) || 'Spaces are prohibited',
                    v => /(?=.*?[a-z])/.test(v) || 'At least one lower case English letter',
                    v => /(?=.*?[A-Z])/.test(v) || 'At least one upper case English letter',
                    v => /(?=.*?[0-9])/.test(v) || 'At least one digit',
                ],
                name: [
                    v => !!v || 'Name is required',
                    v => /^[a-zA-Z]{2,10}$/.test(v) || 'Name is incorrect',
                ],
                surname: [
                    v => !!v || 'Surname is required',
                    v => /^[a-zA-Z]{2,10}$/.test(v) || 'Surname is incorrect',
                ],
                telephoneNumber: [
                    v => !!v || 'Telephone number is required',
                    v => /^(\+375\([\d]{2}\)[\d]{3}\-[\d]{2}\-[\d]{2})$/.test(v) || 'Telephone number must be valid: +XXX(XX)XXX-XX-XX',
                ],
                email: [
                    v => !!v || 'E-mail is required',
                    v => (v && v.length <= 35) || 'E-mail must be less than 35 characters',
                    v => /^[a-zA-z0-9_.-]{1,35}@[a-zA-z0-9_-]{2,15}\.[a-z]{2,5}$/.test(v) || 'E-mail must be valid: +@+.+',
                ]
            }
        }
    },
    methods: {
        submitSignIn: function (event) {
            if (this.$refs.formSignIn.validate()) {
                this.$refs.formSignIn.submit()
                event.preventDefault()
            }
        },
        resetSignIn: function () {
            this.$refs.formSignIn.reset()
        },
        submitSignUp: function () {
            if (this.$refs.formSignUp.validate()) {
                this.$refs.formSignUp.submit()
            }
        },
        resetSignUp: function () {
            this.$refs.formSignUp.reset()
        },
        clearAllComponents() {
            if (this.$refs.formSignUp) {
                this.$refs.formSignUp.reset()
            }
            if (this.$refs.formSignIn) {
                this.$refs.formSignIn.reset()
            }
            this.isSignIn = false
            this.isSignUp = false
            this.isHome = false
            this.signInErrorMessage = undefined
        },
        showHome() {
            this.clearAllComponents()
            this.isHome = true
        },
        showSignUp() {
            this.clearAllComponents()
            this.isSignUp = true
        },
        showSignIn() {
            this.clearAllComponents()
            this.isSignIn = true
        }
    }
})