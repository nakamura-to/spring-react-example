module.exports = {
  context: __dirname + '/src/main/javascript',
  entry: './commentBox.jsx',
  output: {
    path: __dirname + '/build/resources/main/static',
    filename: 'bundle.js', //this is the default name, so you can skip it
    //at this directory our bundle file will be available
    //make sure port 9090 is used when launching webpack-dev-server
    publicPath: 'http://localhost:9090/assets/'
  },
  module: {
    loaders: [
      {
      //tell webpack to use jsx-loader for all *.jsx files
      test: /\.jsx$/,
      loader: 'jsx-loader?insertPragma=React.DOM&harmony'
    }
    ]
  },
  resolve: {
    extensions: ['', '.js', '.jsx']
  }
};
