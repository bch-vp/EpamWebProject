<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/@mdi/font@4.x/css/materialdesignicons.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/vuetify@2.x/dist/vuetify.min.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, minimal-ui">
    <style>
        .hero-image {
            background-image: url("${pageContext.request.contextPath}/img/background.jpg");
            background-color: #cccccc;
            height: 100%;
            background-position: center;
            background-repeat: no-repeat;
            background-size: cover;
            position: relative;
        }
    </style>
    <script>
        let signInErrorMessage = "${signInErrorMessage}"
    </script>
</head>
<body>
<div id="app">
    <v-app>

        <v-app-bar dark app>
            <v-toolbar-title class="font-weight-medium" style="margin-left: 2%">HotelWeb</v-toolbar-title>
            <v-btn v-on:click="showHome" outlined text rounded
                   style="margin-left: 2%">
                <v-icon>home</v-icon>
            </v-btn>
            <v-spacer></v-spacer>
            <v-btn v-on:click="showSignIn" outlined text rounded
                   style="margin-right: 1%">Sign in
            </v-btn>
            <v-btn v-on:click="showSignUp" outlined text rounded
                   style="margin-right: 2%">Sign up
            </v-btn>
        </v-app-bar>
        <v-main>
            <div class="hero-image">
                <div v-if="isSignUp">
                    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/signUp.jsp"/>
                </div>
                <div v-if="isSignIn">
                    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/signIn.jsp" />
                </div>
            </div>
        </v-main>
    </v-app>

</div>
<script src="https://cdn.jsdelivr.net/npm/vue@2.x/dist/vue.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vuetify@2.x/dist/vuetify.js"></script>
<script src="${pageContext.request.contextPath}/js/sign_in_up.js"></script>
</body>
</html>
