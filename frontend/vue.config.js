module.exports = {
    devServer: {
        proxy: "http://localhost:11872/"
    },
    publicPath: process.env.NODE_ENV ==='production' ? "~s282736/lab4/" : "/"
};