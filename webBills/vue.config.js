module.exports = {
  transpileDependencies: [
    'vuetify'
  ],
  devServer: {
    historyApiFallback: true,
    port: 8081,   // you can change the port there
    noInfo: true,
    overlay: true
    },
}
