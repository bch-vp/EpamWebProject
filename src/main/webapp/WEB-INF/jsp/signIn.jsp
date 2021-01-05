<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div  style="margin-left: 33%; padding-top: 3%; color: white">
    <div class="title font-weight-regular">Sign in...</br></div>
    <span style="color:#ff340a;">{{signInErrorMessage}}</span>
</div>

<v-layout row>
    <v-flex xs4 md2 sm4 offset-xs4 offset-md5 offset-sm4>

        <v-form
                @submit="submitSignIn"
                action="HotelWeb?command=sign_in"
                method="post"
                ref="formSignIn"
                v-model="signInData.valid"
        >
            <v-text-field
                    dark
                    name="login"
                    v-model="signInData.login"
                    :counter="10"
                    :rules="signInRules.login"
                    label="Login"
                    required
            ></v-text-field>

            <v-text-field
                    dark
                    name="password"
                    v-model="signInData.password"
                    :counter="10"
                    :rules="signInRules.password"
                    :append-icon="signInData.value ? 'visibility' : 'visibility_off'"
                    @click:append="() => (signInData.value = !signInData.value)"
                    :type="signInData.value ? 'password' : 'text'"
                    label="Password"
                    required
            ></v-text-field>

            <div align="center">
                <v-btn type="submit" :disabled="!signInData.valid" dark small text rounded color="indigo">submit
                </v-btn>
                <v-btn @click="resetSignIn" outlined small fab color="indigo">
                    <v-icon>autorenew</v-icon>
                </v-btn>
            </div>
        </v-form>
    </v-flex>
</v-layout>
</v-container>