var path = require('path')
const VueLoaderPlugin = require('vue-loader/lib/plugin'); // плагин для загрузки кода Vue

module.exports = {
    entry: path.join(__dirname, 'src', 'main', 'webapp', 'vuejs', 'home', 'home.js'),
    output: {
        path: path.resolve(__dirname, './dist'),
        publicPath: '/',
        filename: 'home.js',
    },
    devServer: {
        port: 8081
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
    ]
}