var path = require('path')
const VueLoaderPlugin = require('vue-loader/lib/plugin'); // плагин для загрузки кода Vue


const webpack = require("webpack");
/* ---------------
 * Main config
 * We will place here all the common settings
 * ---------------*/
var config = {
    watch: true,
    devServer: {
        port: 8081,
    },
    module: {
        rules: [
            {
                test: /\.s(c|a)ss$/,
                use: [
                    'vue-style-loader',
                    'css-loader',
                    {
                        loader: 'sass-loader',
                        // Requires sass-loader@^7.0.0
                        options: {
                            implementation: require('sass'),
                            indentedSyntax: true // optional
                        },
                        // Requires sass-loader@^8.0.0
                        options: {
                            implementation: require('sass'),
                            sassOptions: {
                                indentedSyntax: true // optional
                            },
                        },
                    },
                ],
            },
            {
                test: /\.vue$/,
                loader: 'vue-loader'
            }, {
                test: /\.css$/,
                use: [
                    'vue-style-loader',
                    'css-loader'
                ]
            }
        ]
    },
    resolve: {
        modules: [
            path.join(__dirname, 'src', 'main', 'webapp'),
            path.join(__dirname, 'node_modules'),
        ],
    },
    plugins: [
        new VueLoaderPlugin()
    ],
    mode: "development"
};

var guestConfiq = Object.assign({}, config, {
    name: "guest",
    entry: path.join(__dirname, 'src', 'main', 'webapp', 'vuejs', 'guest', 'guest.js'),
    output: {
        path: __dirname + "/build",
        publicPath: "/",
        filename: "guest.js"
    },
});


var errorConfiq = Object.assign({}, config, {
    name: "error",
    entry: path.join(__dirname, 'src', 'main', 'webapp', 'vuejs', 'error', 'error.js'),
    output: {
        path: __dirname + "/build",
        publicPath: "/",
        filename: "error.js"
    },
});

// Return Array of Configurations
module.exports = [guestConfiq, errorConfiq];