<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="title font-weight-regular" style="margin-left: 33%; padding-top: 3%; color: white">Sign up...</div>
    <v-layout row>
        <v-flex xs4 md2 sm4 offset-xs4 offset-md5 offset-sm4>

            <v-form
                    @submit="submitSignUp"
                    action="HotelWeb?command=sign_up"
                    method="post"
                    ref="formSignUp"
                    v-model="signUpData.valid"
            >
                <v-text-field
                        dark
                        name="login"
                        v-model="signUpData.login"
                        :counter="10"
                        :rules="signUpRules.login"
                        label="Login"
                        required
                ></v-text-field>

                <v-text-field
                        dark
                        name="password"
                        v-model="signUpData.password"
                        :counter="10"
                        :rules="signUpRules.password"
                        :append-icon="signUpData.value ? 'visibility' : 'visibility_off'"
                        @click:append="() => (signUpData.value = !signUpData.value)"
                        :type="signUpData.value ? 'password' : 'text'"
                        label="Password"
                        required
                ></v-text-field>

                <v-text-field
                        dark
                        name="name"
                        v-model="signUpData.name"
                        :counter="10"
                        :rules="signUpRules.name"
                        label="Name"
                        required
                ></v-text-field>

                <v-text-field
                        dark
                        name="surname"
                        v-model="signUpData.surname"
                        :counter="10"
                        :rules="signUpRules.surname"
                        label="Surname"
                        required
                ></v-text-field>

                <v-text-field
                        dark
                        name="phone"
                        v-model="signUpData.telephoneNumber"
                        :counter="17"
                        :rules="signUpRules.telephoneNumber"
                        label="Telephone number"
                        required
                ></v-text-field>

                <v-text-field
                        dark
                        name="email"
                        v-model="signUpData.email"
                        :rules="signUpRules.email"
                        :counter="35"
                        label="E-mail"
                        required
                ></v-text-field>

                <div align="center">
                    <v-btn type="submit" :disabled="!signUpData.valid" dark small text rounded color="indigo">submit
                    </v-btn>
                    <v-btn @click="resetSignUp" outlined small fab color="indigo">
                        <v-icon>autorenew</v-icon>
                    </v-btn>
                </div>
            </v-form>
        </v-flex>
    </v-layout>
</v-container>