//MASIH BELUM DIGUNAKAN. INI MASUKAN DARI MENTOR KAK ANTHONIUS
function getLoginInput() {
    var bodyFormData=new FormData();
    bodyFormData.set('username','admin');
    bodyFormData.set('password','tes');

    axios({
        method:'post',
        url:'localhost:8080/auth/login',
        data:bodyFormData,
        config:{headers:{'Content-Type':'application/json'}}
    }).then(function (response) { console.log(response)})
        .catch(function (response) { console.log((response)) })
}