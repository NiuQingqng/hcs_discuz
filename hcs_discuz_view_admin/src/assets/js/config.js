const host = "http://localhost:8088";
const userPage = "http://localhost:8080"
const adminPage = "http://localhost:8081"
const authPage = "http://localhost:8083"

// const host = "线上地址";
// const userPage = "/"
// const adminPage = "/admin"
// const authPage = "/auth"

const config = {
  host: host,
  wapperUrl: host+"/img/img/wapper.jpg",
  bannerUrls: [
    host+'/img/img/banner1.jpg',
    host+'/img/img/banner2.jpg',
    host+'/img/img/banner3.jpg',
  ],
  loginPage: authPage,
  authPage: authPage,
  userPage: userPage,
  adminPage: adminPage,
  resetPasswordPage: authPage+"/#/resetpassword"
};
export default config;